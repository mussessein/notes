package com.controller.backend;

import com.common.Constant;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.pojo.User;
import com.service.ICategoryService;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;
    /**
     * 添加商品分类
     * RequestParam:此注解是 获取Request中提交的参数,如果传入此接口的Request没有参数,则报错
     * 1.先从session判断是否登录
     * 2.登录之后校验是否为管理员
     * 3.是管理员,执行添加分类逻辑
     * @param session
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId){
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        // 校验管理员的方法,返回成功,则是管理员
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,执行添加分类逻辑
            return iCategoryService.addCategory(categoryName,parentId);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 修改品类名称
     *
     * @param session
     * @param categoryName
     * @return
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){

        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null) {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        // 校验管理员的方法,返回成功,则是管理员
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,开始更新
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 返回传入的Id的所有平级分类
     * 会返回一个json,含有子分类的一个List
     * @param session
     * @param parentId
     * @return
     */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0")Integer parentId){

        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null) {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员
            return iCategoryService.getParallelCategory(parentId);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 查询当前节点id,和递归子节点id,
     * 就是查询当前id下的所有子id,即所有子分类
     * @param session
     * @param categoryId
     * @return
     */
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId){

        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null) {
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }
}
