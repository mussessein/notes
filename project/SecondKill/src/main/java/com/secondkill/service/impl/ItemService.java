package com.secondkill.service.impl;

import com.secondkill.entity.ItemKill;
import com.secondkill.mapper.ItemKillMapper;
import com.secondkill.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemKillMapper itemKillMapper;
    /**
     * 获取待秒杀商品列表：
     * 商品分为：秒杀商品，非秒杀商品
     * 1. 通过秒杀商品表和商品表，查询出秒杀商品的各项信息
     * 2. 限制条件：
     * （1）当前时间now() 处于秒杀商品的开始时间和结束事件之间
     * （2）秒杀商品的剩余数量>0
     * 3. 满足条件，返回
     */
    @Override
    public List<ItemKill> getKillItems() {
        return itemKillMapper.selectAll();
    }

    @Override
    public ItemKill getKillDetail(Integer id) {
        return itemKillMapper.selectById(id);
    }
}
