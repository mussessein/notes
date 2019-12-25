package com.example.lock.listener.event;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

@ToString
public class CrmOrderRobbingEvent extends ApplicationEvent implements Serializable {

    private String mobile;

    private Integer userId;

    public CrmOrderRobbingEvent(Object source,String mobile,Integer userId) {
        super(source);
        this.mobile=mobile;
        this.userId=userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
