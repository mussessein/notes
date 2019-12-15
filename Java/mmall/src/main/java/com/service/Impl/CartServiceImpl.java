package com.service.Impl;

import com.common.Constant;
import com.common.ResponseCode;
import com.common.ServerResponse;
import com.dao.CartMapper;
import com.dao.ProductMapper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.pojo.Cart;
import com.pojo.Product;
import com.service.ICartService;
import com.util.BigDecimalUtil;
import com.util.PropertiesUtil;
import com.vo.CartProductVo;
import com.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * 购物车存在三层对象
 * 添加的商品,会在数据库中生成一个cart表,单记录对象
 * 抽象对象:CartProductVo 就是购物车中的商品可能并不是一个,这个对象,集成了商品的属性和cart单条记录的属性
 * 抽象对象:CartVo:代表了整个购物车.主要是计算总价格
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 返回商品list
     *
     * @param userId
     * @return
     */
    public ServerResponse<CartVo> list(Integer userId) {
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }

    /**
     * 添加商品到购物车,
     * 1.查询数据库cart购物车表,看此商品是否已经添加过
     * cart==null,说明,没添加过,在数据库中进行记录(新建一个cart对象,插入)
     * 2.cart!=null,产品已经添加过了,只需要增加count数量,再更新记录
     * <p>
     * 3.最后我们返回的是,产品和购物车结合出来的抽象对象vo
     *
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count) {
        // 参数为空,直接返回参数错误信息
        if (productId == null || count == null) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null) {
            // 产品不在购物车中,需要新增一个此产品的记录
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(count);
            cartItem.setChecked(Constant.Cart.CHECKED);
            cartMapper.insert(cartItem);
        } else {
            // 产品已经存在购物车中,添加count,更新记录
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    /*
    此方法,进行两层对象的整合!!!!!!
    返回的是 一个购物车整体对象

     1.拿到此user下添加进购物车的所有数据库记录
     2.通过每一条记录->组装cartProductVo对象,先插入cart信息,再插入product信息
     3.判断Product库存是否足够
     4.计算价格
     */
    private CartVo getCartVoLimit(Integer userId) {

        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVoList = Lists.newArrayList();
        // 高精度运算类
        BigDecimal cartTotalPrice = new BigDecimal("0");
        // 遍历返回的购物车Item
        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Cart cartItem : cartList) {
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cartItem.getId());
                cartProductVo.setUserId(cartItem.getUserId());
                cartProductVo.setProductId(cartItem.getProductId());
                Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
                if (product != null) {
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductStock(product.getStock());
                    cartProductVo.setProductPrice(product.getPrice());
                    int buyLimitCount = 0;
                    if (product.getStock() >= cartItem.getQuantity()) {
                        // 库存足够,购买数量设置为选中的数量
                        buyLimitCount = cartItem.getQuantity();
                        cartProductVo.setLimitQuantity(Constant.Cart.LIMIT_NUM_SUCCESS);
                    } else {
                        // 库存不足,失败,那么,把购物车最大数量,设置为库存数
                        // 购买数量,设置为最大产品库存数
                        buyLimitCount = product.getStock();
                        cartProductVo.setLimitQuantity(Constant.Cart.LIMIT_NUM_FALL);
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cartItem.getId());
                        cartForQuantity.setChecked(cartItem.getChecked());
                        cartForQuantity.setProductId(cartItem.getProductId());
                        cartForQuantity.setUserId(cartItem.getUserId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKey(cartForQuantity);
                    }
                    cartProductVo.setQuantity(buyLimitCount);
                    // 计算此Product价钱:选中数量*商品单价
                    cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartProductVo.getQuantity()));
                    // 进行勾选
                    cartProductVo.setProductChecked(cartItem.getChecked());
                }
                // 全部产品,总价计算,只计算被勾选的产品
                if (cartItem.getChecked() == Constant.Cart.CHECKED) {
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }
        }
        // 设置最终价格
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId)); // 购物车对象是否全选,触发全选按钮
        cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    private Boolean getAllCheckedStatus(Integer userId) {

        if (userId == null) {
            return false;
        }
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;
    }

    /**
     * 更新购物车产品数量
     *
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) {
        // 参数为空,直接返回参数错误信息
        if (productId == null || count == null) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart != null) {
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    /**
     * 删除商品:就是删除Cart表中的记录
     *
     * @param userId
     * @param productIds
     * @return
     */
    public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds) {
        // guava:用逗号分割字符串,转化为List
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productList)) {
            return ServerResponse.createByErrorMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdProductIds(userId, productList);
        return this.list(userId);
    }

    /**
     * 全选\反选\单独选\单独反选
     * 共用此方法
     * 全选\反选:传入的productId为null即可
     *
     * @param userId
     * @param checked
     * @return
     */
    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer checked, Integer productId) {
        cartMapper.checkedOrUncheckProduct(userId, checked, productId);
        return this.list(userId);
    }


    public ServerResponse<Integer> getCartProductCount(Integer userId) {
        if (userId == null) {
            return ServerResponse.createBySuccess(0);
        }
        int count = cartMapper.getCartProductCount(userId);
        return ServerResponse.createBySuccess(count);
    }

}
