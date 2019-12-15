package com.service.Impl;

import com.common.Constant;
import com.common.ServerResponse;
import com.common.TokenCache;
import com.dao.UserMapper;
import com.pojo.User;
import com.service.IUserService;
import com.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录逻辑，需要校验用户名是否存在
     * 在数据库中查询username，返回用户id
     * 1.用户不存在
     * 2.用户存在，校验密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //todo 密码登录MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        /**
         * 到这里说明用户是存在的，username查到了
         * 但是还是会返回null，是因为密码没有匹配上。
         */
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        /**
         * 到这里，说明密码也对了。查到了user
         */
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    /**
     * 注册功能：
     * 需要校验用户名或邮箱 是否已经存在，校验方法在下方的checkValid方法
     * 1.校验username
     * 2.校验email
     * 3.可以注册之后：将用户设置为普通用户
     * 4.将用户的密码进行MD5加密 调用Util工具包中的MD5Util类
     * 5.将用户插入到数据库，根据返回结果，判断是否插入成功，插入成功即注册成功
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> register(User user) {
        // 1
        ServerResponse validResponse = this.checkValid(user.getUsername(), Constant.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        // 2
        validResponse = this.checkValid(user.getEmail(), Constant.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        // 3
        user.setRole(Constant.Role.ROLE_CUSTOMER);
        // 4.MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 因为校验功能会在很多模块中复用，所以单独封装，便于复用
     * 1.先判断type值是否为空，为空直接返回校验失败
     * 2.在判断type是用户名还是邮箱，进行分别校验str
     * 3.最后全部校验通过，返回校验成功
     *
     * 注意!!!:这里是用户已存在,查到了用户,返回ERROR
     *        没有查到用户返回SUCCESS
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        // 检验非空
        if (StringUtils.isNotBlank(type)) {
            // 开始校验
            if (Constant.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                //>0表名有返回结果
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Constant.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                //>0表名有返回结果
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("邮箱已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 通过username拿到密码提示问题 需要复用校验方法
     * 1.校验到 用户名已存在,返回Error,
     *
     * @param username
     * @return
     */
    public ServerResponse selectQuesion(String username) {
        ServerResponse validResponse = this.checkValid(username, Constant.USERNAME);
        if (validResponse.isSuccess()) {
            // 这里说明用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        // 用户存在
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码问题为空");

    }

    /**
     * 找回密码中的验证密码提示问题
     * 1.将username,question,answer在数据库中进行匹配
     * 2.返回结果>0,匹配成功,校验通过
     * 3.需要限制以下这个校验的有效期:30分钟
     * 生成UUID唯一识别码,作为value放入TokenCache中,就可以开始计时
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            // 说明问题及问题答案都和username匹配
            // 通过UUID生成不会重复的唯一识别码放入Token中
            String forgetToken = UUID.randomUUID().toString();
            // TokenCache.TOKEN_PREFIX+username作为key,Token作为value保存在缓存中
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }

    /**
     * 验证了问题之后,开始找回密码,forgetToken
     * 1.首先校验参数,
     * forgetToken,直接返回
     * username如果不存在,直接返回
     * 2.校验此用户查到的token,防止过期
     * (注意:forgetToken和token并不是一个token)
     * 3.校验forgetToken和token是否为同一个(相同说明是同一个用户,并且没有超时)
     * 开始修改密码->数据库update密码
     * 4.否则返回ERROR
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        // token不存在
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,没有找到token");
        }
        ServerResponse validResponse = this.checkValid(username, Constant.USERNAME);

        //用户不存在,这里返回SUCCESS是用户不存,没毛病
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        // 拿到缓存中当前用户为key的value(即token,也可以说是UUID)
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        // 若果token为空(可能已经过期)
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token过期无效");
        }
        // 如果forgetToken和token相同,则校验通过
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            // 数据库操作update
            int rowCount = userMapper.updatePasswordByusername(username, md5Password);
            // 如果update返回1,则修改成功
            if (rowCount > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else
            return ServerResponse.createByErrorMessage("token错误");
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * 在线修改密码
     * 修改密码的时候,需要输入旧密码,再次核对用户,才能修改新密码(防止横向越权)
     * 1.查询旧密码和用户是否匹配
     * 2.不匹配,返回
     * 3.匹配,修改新密码(使用dao层的选择性更新用户方法updateByPrimaryKeySelective)
     *
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {

        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        // 没有匹配项,密码错误
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int rowCount = userMapper.updateByPrimaryKeySelective(user);
        if (rowCount > 0)
            return ServerResponse.createBySuccessMessage("修改密码成功");
        else
            return ServerResponse.createByErrorMessage("密码更新失败");
    }

    /**
     * 登录状态更新个人信息
     * 不能修改username;
     * 1.还要保证准备修改的邮箱,没有被其他用户使用
     * 2.验证通过,开始修改信息
     * 3.数据库修改成功,返回1,返回更新成功和新的User对象到Controller
     * 4.修改失败,返回msg
     * @param user
     * @return
     */
    public ServerResponse<User> updateInformation(User user){
        /**
         * 查询email是否存在且不是当前用户的email
         * 如果返回1,说明此邮箱已经存在,被其他人占用
         * 返回0,则邮箱不存在,可以修改邮箱
         */
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("email已经存在");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount>0){
            return ServerResponse.createBySuccess("更新成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    /**
     * 显示个人信息的方法
     * 通过userid,返回除了密码之外的个人信息
     * 所以需要将查询到的user对象的密码设置为空
     * 再向controller返回user对象
     * @param userId
     * @return
     */
    public ServerResponse<User> get_information(Integer userId){

        User user = userMapper.selectByPrimaryKey(userId);
        if (user==null){
            return ServerResponse.createByErrorMessage("未找到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


    /**
     * 判断当前用户是否为管理员
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user){
        if (user!=null&&user.getRole().intValue()==Constant.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }













}
