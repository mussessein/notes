package com.controller.backend;


import com.common.Constant;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.google.common.collect.Maps;
import com.pojo.Product;
import com.pojo.User;
import com.service.IFileService;
import com.service.IProductService;
import com.service.IUserService;
import com.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFileService iFileService;

    /**
     * 管理员添加产品
     *
     * @param session
     * @param product
     * @return
     */
    @RequestMapping(value = "save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product){

        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            // 是管理员,添加产品逻辑
            return iProductService.saveOrUpdateProduct(product);
        }
        else
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 产品上架,下架的逻辑
     * 通过设置产品的属性status来控制
     * @param session
     * @param productId
     * @param status
     * @return
     */
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setStatus(HttpSession session,Integer productId ,Integer status){
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        // 是管理员
        if (response.isSuccess()){
            return iProductService.setStatus(productId,status);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 产品详情接口
     * 后台查看,不需要关注是否上架
     *
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session,Integer productId){
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        // 是管理员
        if (response.isSuccess()){
            return iProductService.manageProductDetail(productId);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 通过PageHelper工具,实现分页
     *
     * @param session
     * @param pageNum:总页数
     * @param pageSize:每页容量
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        // 是管理员
        if (response.isSuccess()){
            return iProductService.getProductList(pageNum,pageSize);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 查询业务逻辑
     * 通过productId和productName来查询商品信息
     * 并且分页处理
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpSession session,String productName,Integer productId,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        // 是管理员
        if (response.isSuccess()){
            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 基于SpringMVC的文件上传(管理员权限)
     * 上传图片和文本
     * 通过Request上下文，拿到url地址，动态的在项目中自动创建相对路径存放上传文件
     * 1.先将文件以及目录创建在项目中
     * 2.然后再传到ftp服务器中,删除本地文件
     * 3.最终返回前端一个封装了ftp的url的Map
     * @param session
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session,@RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){

        User user  = (User)session.getAttribute(Constant.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        ServerResponse response = iUserService.checkAdminRole(user);
        // 是管理员
        if (response.isSuccess()){

            // 拿到Request的上下文，从中取得相对地址，相对路径是在WEB-INF文件下
            // getRealPath返回服务器端的绝对路径 / (也就是WEB-INF/)
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/");
            // 封装map
            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerResponse.createBySuccess(fileMap);
        }
        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    /**
     * 富文本上传(类似MarkDown)
     * simditor返回值格式要求,最终返回一个Map(成功或失败)
     * {
     *   "success": true/false,
     *   "msg": "error message", # optional
     *   "file_path": "[real file path]"
     * }
     * 1.判断权限,非管理员 -失败
     * 2.调用service层的upload方法进行上传返回targetFileName
     *   targetFileName为空 -失败
     * 3.targetFileName不为空,即上传成功
     * @param session
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map resultMap = Maps.newHashMap();
        User user = (User)session.getAttribute(Constant.CURRENT_USER);
        if(user == null){
            resultMap.put("success",false);
            resultMap.put("msg","请登录管理员");
            return resultMap;
        }
        //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回
        if(iUserService.checkAdminRole(user).isSuccess()){
            String path = request.getSession().getServletContext().getRealPath("upload");
            // targetFileName不为空,即上传成功
            String targetFileName = iFileService.upload(file,path);
            if(StringUtils.isBlank(targetFileName)){
                resultMap.put("success",false);
                resultMap.put("msg","上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
            resultMap.put("success",true);
            resultMap.put("msg","上传成功");
            resultMap.put("file_path",url);
            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
            return resultMap;
        }else{
            resultMap.put("success",false);
            resultMap.put("msg","无权限操作");
            return resultMap;
        }
    }



}
