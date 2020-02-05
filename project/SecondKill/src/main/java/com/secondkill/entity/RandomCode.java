package com.secondkill.entity;

import lombok.Data;

import javax.annotation.security.DenyAll;

@Data
public class RandomCode {
    private Integer id;

    private String code;
}