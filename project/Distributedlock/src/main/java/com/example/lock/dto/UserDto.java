package com.example.lock.dto;/**
 * Created by Administrator on 2018/10/25.
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 **/
@Data
public class UserDto {

    @NotBlank
    private String userName;

    private String email;

}
































