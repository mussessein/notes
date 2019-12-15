package com.oa.biz.impl;

import com.oa.dao.ClaimVoucherDao;
import com.oa.dao.ClaimVoucherItemDao;
import com.oa.dao.DealRecordDao;
import com.oa.dao.EmployeeDao;
import com.oa.entity.ClaimVoucher;
import com.oa.entity.ClaimVoucherItem;
import com.oa.entity.DealRecord;
import com.oa.entity.Employee;
import com.oa.global.Contant;
import com.oa.biz.ClaimVoucherBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
    @Qualifier("claimVoucherDao")
    @Autowired
    private ClaimVoucherDao claimVoucherDao;

    @Qualifier("claimVoucherItemDao")
    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;

    @Qualifier("dealRecordDao")
    @Autowired
    private DealRecordDao dealRecordDao;

    @Qualifier("employeeDao")
    @Autowired
    private EmployeeDao employeeDao;

    //报销单保存方法,点击保存，返回报销单的list界面：
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        //报销单创建时间
        claimVoucher.setCreateTime(new Date());
        //报销单待处理人就是创建者
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        //报销单状态
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        //保存进数据库
        claimVoucherDao.insert(claimVoucher);

        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }
    }
    /*
    查报销单
     */
    public ClaimVoucher get(int id) {

        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(int cvid) {

        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    public List<DealRecord> getRecords(int cvid) {

        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    /*
    查看自己的报销单
     */
    public List<ClaimVoucher> getForSelf(String sn) {

        return claimVoucherDao.selectByCreateSn(sn);
    }
    /*
    创建后的报销单提交给了谁
     */
    public List<ClaimVoucher> getForDeal(String sn) {

        return claimVoucherDao.selectByNextDealSn(sn);
    }

    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        for(ClaimVoucherItem old:olds){
            boolean isHave=false;
            for(ClaimVoucherItem item:items){
                if(item.getId()==old.getId()){
                    isHave=true;
                    break;
                }
            }
            if(!isHave){
                claimVoucherItemDao.delete(old.getId());
            }
        }
        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            if(item.getId()>0){
                claimVoucherItemDao.update(item);
            }else{
                claimVoucherItemDao.insert(item);
            }
        }

    }

    /*
    提交报销单
     */
    public void submit(int id) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());

        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        //通过提交的人所属部门的id 和post 部门的经理，获得，部门经理的sn
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(),Contant.POST_FM).get(0).getSn());
        //提交报销单之后，更新表单的信息，包括：（状态，修改时间，下一个处理人）
        claimVoucherDao.update(claimVoucher);
        //插入一条报销单的提交记录
        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecordDao.insert(dealRecord);
    }
    /*
    处理报销单
     */
    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        Employee employee = employeeDao.select(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());
        //一。表单已通过上一个处理人
        if(dealRecord.getDealWay().equals(Contant.DEAL_PASS)){
            /*
            如果报销单<=5000，或者已经被总经理处理
            那么：设置状态为已审核
                 设置下个处理人为财务
                 设置处理记录为已审核
            否则即报销单>50000且处理人不是总经理，那就是部门经理
            那么：设置状态为待复审
                 设置下一处理人为总经理，交给总经理
             */
            if(claimVoucher.getTotalAmount()<=Contant.LIMIT_CHECK || employee.getPost().equals(Contant.POST_GM)){
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_CASHIER).get(0).getSn());
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            }else{
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }
            /*
            二。表单记录显示未通过：有两种情况
            1.报销单被打回：设置报销单的状态为已打回
                         设置下一个处理人为创建报销单的员工本人
            2.报销单被拒绝：设置报销单的状态为已终止
                         下一个处理人设为空（失效）
            3.如果表单已打款：设置保险到为已打款
                          下一处理人设置为完成

             */
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_BACK)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_REJECT)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn("失效");

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(Contant.DEAL_PAID)){
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn("完成");

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }
        /*
        设置完成，更新表单，插入表单记录
         */
        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }

}
