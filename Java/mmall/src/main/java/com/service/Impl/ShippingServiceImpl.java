package com.service.Impl;


import com.common.ServerResponse;
import com.dao.ShippingMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.pojo.Shipping;
import com.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 地址的service方法
 * 主要:防止越权
 * 在进行数据库操作的时候,一定要从session中拿到UserId.一起进行操作.
 * 确定,修改或删除的地址是当前用户的
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * 新增一个收货地址,
     * 1.首先从session中,拿到userId,填充
     * 2.只需要填充id,其余都是前端传过来的,
     * 3.插入数据库
     * 4.返回一个shippingId,放进map中返回
     * @param userId
     * @param shipping
     * @return
     */
    public ServerResponse add(Integer userId, Shipping shipping) {

        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功",result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    /**
     * 删除,需要传入userId,防止横向越权
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse delete(Integer userId, Integer shippingId) {

        int rowCount = shippingMapper.deleteByShippingIdUserId(userId,shippingId);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    /**
     *  要用session中的userId,防止越权
     * @param userId
     * @param shipping
     * @return
     */
    public ServerResponse update(Integer userId, Shipping shipping) {

        shipping.setUserId(userId);
        Shipping shippingDate = shippingMapper.selectByShippingIdUserId(userId,shipping.getId());
        shipping.setCreateTime(shippingDate.getCreateTime());
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {

        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId,shippingId);
        if (shipping != null) {
            return ServerResponse.createBySuccess("查询地址成功",shipping);
        }
        return ServerResponse.createByErrorMessage("无法查询到该地址");
    }

    /**
     * 分页逻辑
     * 需要数据库返回 shipping记录的List
     */
    public ServerResponse<PageInfo> list(Integer userId, int pageNum,int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo =new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);

    }


}
