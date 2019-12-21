package com.example.lock.dto;/**
 * Created by Administrator on 2018/10/31.
 */

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 *
 **/
@Data
@ToString
public class RobbingDto {

    @NotNull
    private Integer userId;

    @NotBlank
    private String mobile;

}
























































