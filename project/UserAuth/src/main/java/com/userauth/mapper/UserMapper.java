package com.userauth.mapper;

import com.userauth.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(@Param("userName")String userName);

    int updatePassword(@Param("userName") String userName,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);

}