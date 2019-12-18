package com.userauth.mapper;

import com.userauth.entity.AuthToken;
import org.apache.ibatis.annotations.Param;

public interface AuthTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthToken record);

    int insertSelective(AuthToken record);

    AuthToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthToken record);

    int updateByPrimaryKey(AuthToken record);

    // 使当前用户的token无效，isActice=0(新增dao层方法)
    void invalidateTokenByUser(@Param("id") Integer id);

    AuthToken selectByAccessToken(@Param("accessToken") String accessToken);
    // 使得token失效
    int invalidateByToken(@Param("accessToken") String accessToken);
    // 删除失效token，所有is_avtice=0的token删除
    int deleteUnactiveToken();

}