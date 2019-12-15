package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    // 检查用户名是否已存在
    int checkUsername(String username);
    // MyBatis下在传入多个参数的时候，必须用@Param注解
    User selectLogin(@Param("username") String username, @Param("password") String password);
    // 注册时，检查邮箱是否已存在
    int checkEmail(String email);
    // 查找密码提示问题
    String selectQuestionByUsername(String username);
    // 三个参数全部匹配,返回查询结果>0,才允许修改密码
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);
    // 修改密码
    int updatePasswordByusername(@Param("username")String username,@Param("passwordNew")String passwordNew);
    // 同时匹配id和密码,如果找到返回1
    int checkPassword(@Param("password")String password,@Param("userId")Integer userId);
    /**
     * 查询email是否存在且不是当前用户的email
     * 如果返回1,说明此邮箱已经存在,被其他人占用
     * 返回0,则邮箱不存在,可以修改邮箱
     */
    int checkEmailByUserId(@Param("email")String email,@Param("userId")Integer userId);
}