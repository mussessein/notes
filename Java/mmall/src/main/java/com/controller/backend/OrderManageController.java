package com.controller.backend;

import com.common.Constant;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.github.pagehelper.PageInfo;
import com.pojo.User;
import com.service.IOrderService;
import com.service.IUserService;
import com.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;
    /**
     * 管理员查看订单List
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session,
                                              @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        // 验证权限
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,添加产品逻辑
            return iOrderService.manageList(pageNum,pageSize);
        }
        else
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 管理员，查看某个订单信息
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> detail(HttpSession session, Long orderNo){
        // 验证权限
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,添加产品逻辑
            return iOrderService.manageDetail(orderNo);
        }
        else
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 后台search功能
     * @param session
     * @param orderNo
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, Long orderNo,
                                               @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        // 验证权限
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,添加产品逻辑
            return iOrderService.manageSearch(orderNo,pageNum,pageSize);
        }
        else
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 后台发货 接口
     * 返回 发货成功或失败
     *
     * @param orderNo
     * @return
     */
    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpSession session, Long orderNo){
        // 验证权限
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,添加产品逻辑
            return iOrderService.manageSendGoods(orderNo);
        }
        else
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }






}
