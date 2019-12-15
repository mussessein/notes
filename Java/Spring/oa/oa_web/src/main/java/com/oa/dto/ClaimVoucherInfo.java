package com.oa.dto;

import com.oa.entity.ClaimVoucher;
import com.oa.entity.ClaimVoucherItem;

import java.util.List;

/*
报销单信息：
 包括 基本信息 / 费用明细
 */
public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {

        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {

        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {

        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {

        this.items = items;
    }
}
