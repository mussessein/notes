package com.oa.dao;

import com.oa.entity.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 此注解是让Spring找到这个接口的实现类,以便后续需要的时候注入
 其实不写这个注解,也可以找到实现类
 因为MapperScannerConfigurer这个bean,已经扫描了dao包
 */

@Repository("claimVoucherDao")
public interface ClaimVoucherDao {

    void insert(ClaimVoucher claimVoucher);

    void update(ClaimVoucher claimVoucher);

    void delete(int id);

    ClaimVoucher select(int id);
    //自己看自己的报销单
    List<ClaimVoucher> selectByCreateSn(String csn);
    //有权限的人看自己权限下的报销单
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}
