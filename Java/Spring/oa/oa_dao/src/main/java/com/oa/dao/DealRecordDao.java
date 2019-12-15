package com.oa.dao;

import com.oa.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dealRecordDao")
public interface DealRecordDao {

    /*
    处理记录，不存在修改和删除
     */
    void insert(DealRecord dealRecord);
//    void update(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
