package com.secondkill.service;

import com.secondkill.entity.ItemKill;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IItemService {
    List<ItemKill> getKillItems();
    ItemKill getKillDetail(Integer id);
}
