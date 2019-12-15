package com.service.Impl;

import com.common.Constant;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.dao.CategoryMapper;
import com.dao.ProductMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.pojo.Category;
import com.pojo.Product;
import com.service.ICategoryService;
import com.service.IProductService;
import com.util.DateTimeUtil;
import com.util.PropertiesUtil;
import com.vo.ProductDetailVo;
import com.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 添加或更新产品
     * 1.product不为空,先进行图片操作
     * 2.就像咸鱼提交图片一样,会有很多子图(以,分割),可以选择一个作为主图
     *   主图会被排序为第一个子图
     *   我们只要拿到第一个图,存为主图即可
     * 3.如果有productId,即更新操作,确定是更新哪个id,
     * 4.如果没有id,即插入新产品
     * @param product
     * @return
     */
    public ServerResponse saveOrUpdateProduct(Product product){
        if (product!=null){
            if (StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                product.setMainImage(subImageArray[0]);
            }
            if (product.getId()!=null){
                // 更新
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("更新产品成功");

                }
            }else {
                // 新增
                int rowCount = productMapper.insert(product);
                if (rowCount>0){
                    return ServerResponse.createBySuccessMessage("新增产品成功");

                }
            }
        }
        return ServerResponse.createByErrorMessage("添加或更新产品失败");
    }

    /**
     * 产品上架,下架的逻辑
     * 通过设置产品的属性status来控制
     * 1.productId用来索引,然后更新设置status
     * @param productId
     * @param status
     * @return
     */
    public ServerResponse<String> setStatus(Integer productId,Integer status){

        if (productId==null||status==null){
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("修改产品状态成功");
        }else
            return ServerResponse.createByErrorMessage("修改产品状态失败");
    }

    /**
     * 返回:产品详情
     * 1.通过传入的Id,拿到对象信息(为空,返回ERROR)
     * 2.不为空则调用私有方法,装配一个新的对象返回
     * @param productId
     * @return
     */
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId){
        if (productId==null){
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.createByErrorMessage("产品已下架或者已删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);

    }

    /**
     * 将产品信息(包括Product和category两个对象的信息)放入vo对象中,来返回产品详情
     * 1.将Product的信息,放入ProductDetailVo对象中
     * 2.我们将图片放入ftp图片服务器中,通过配置文件,从ftp服务器中拿到图片url,在传入vo对象中
     * 3.将查询到的category信息,放入ProductDetailVo对象中
     * 4.最后是CreateTime和UpdateTime
     *   这两个数据,从数据库中查询出来的信息,不利于阅读,我们需要一个Utils来格式化一下时间
     * @param product
     * @return
     */
    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());
        // 通过图片服务器,拿到图片url,放入对象
        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));
        // 拿到category对象
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);//默认根节点
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }
        // 通过DateTimeUtil来格式化时间,利于阅读
        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }

    /**
     * 通过PageHelper来实现分页
     * 分页展示产品List,不需要把产品的每一个字段,都展示出来,
     * 所以我们在创建一个vo对象,创建一个private方法,填充Productvo对象
     * PageHelper使用方法:
     * 1.传入每页数量和总页数
     * 2.从数据库中,返回总共要展示的Item
     * 3.将Item的List放入PageInfo对象中,返回即可
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getProductList(int pageNum,int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectList();
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product product :productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);

    }
    /**
     * 填充ProductListVo对象逻辑
     */
    private ProductListVo assembleProductListVo(Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));
        productListVo.setMainImage(product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setStatus(product.getStatus());
        productListVo.setSubtitle(product.getSubtitle());
        return productListVo;
    }

    /**
     * 通过Name或者id，查询产品
     * 和上面的接口基本类似
     * 1.查询结果放入List
     * 2.通过私有方法，封装进vo对象，生成VoList
     * 3.将VoList分页，返回分页结果
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(productName)){
            productName=new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList=productMapper.selectByNameAndProductId(productName,productId);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product product :productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    // 下面是前台接口的service方法


    /**
     * 装配vo对象，返回对象详细信息
     * @param productId
     * @return
     */
    public ServerResponse<ProductDetailVo> getProductDetail(Integer productId){

        if (productId==null){
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product==null){
            return ServerResponse.createByErrorMessage("产品已下架或者已删除");
        }
        // 判断商品是否在线
        if (product.getStatus()!= Constant.ProductStatusEnum.ON_SALE.getCode()){
            return ServerResponse.createByErrorMessage("产品已下架或者已删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    /**
     * 返回分页后的结果,这里有一些逻辑
     * 1.两个参数都为空，返回ERROR
     * 2.通过id，查询结果category空，又没有keyword，返回空集，不报错
     *   category不为空,则递归返回所有子分类
     * 3.
     *
     * @param keyword
     * @param categoryId
     * @return
     */
    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        // 1.两个参数都没有，返回ERROR
        if (StringUtils.isBlank(keyword)&&categoryId==null){
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();
        // 2
        if(categoryId!=null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            // 这里返回一个空的PageInfo
            if (category==null&&StringUtils.isBlank(keyword)){
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccess(pageInfo);
            }
            categoryIdList = iCategoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        // keyword不为空,拼接 % 进行模糊查询
        if (StringUtils.isNotBlank(keyword)){
            keyword=new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        PageHelper.startPage(pageNum,pageSize);
        // 通過price排序
        if (StringUtils.isNotBlank(orderBy)){
            if (Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                /*
                pagehelper的排序方法就是 orderBy("price desc")
                所以要分割再拼接
                */
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Product> productList = productMapper.selectByNameAndCategoryIds(
                StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product product:productList){
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
