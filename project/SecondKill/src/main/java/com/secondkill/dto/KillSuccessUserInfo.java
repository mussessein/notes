package com.secondkill.dto;

import com.secondkill.entity.ItemKillSuccess;
import lombok.Data;

import java.io.Serializable;

/**
 * 存放：秒杀成功的用户的信息
 * 用于发送邮件
 **/
@Data
public class KillSuccessUserInfo extends ItemKillSuccess implements Serializable{

    private String userName;

    private String phone;

    private String email;

    private String itemName;

    @Override
    public String toString() {
        return super.toString()+"\nKillSuccessUserInfo{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}