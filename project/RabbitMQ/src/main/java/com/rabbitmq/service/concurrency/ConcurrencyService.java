package com.rabbitmq.service.concurrency;

import com.rabbitmq.entity.Product;
import com.rabbitmq.entity.ProductRobbingRecord;
import com.rabbitmq.mapper.ProductMapper;
import com.rabbitmq.mapper.ProductRobbingRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 每个线程的下单任务
 */
@Service
@Slf4j
public class ConcurrencyService {

    private static final String ProductNo="1";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRobbingRecordMapper productRobbingRecordMapper;

    /**
     * 处理抢单
     * @param mobile
     */
    public void manageRobbing(String mobile){
        /*try {
            Product product=productMapper.selectByProductNo(ProductNo);
            if (product!=null && product.getTotal()>0){
                log.info("当前手机号：{} 恭喜您抢到单了!",mobile);
                productMapper.updateTotal(product);
            }else{
                log.error("当前手机号：{} 抢不到单!",mobile);

            }
        }catch (Exception e){
            log.error("处理抢单发生异常：mobile={} ",mobile);
        }*/ //--v1.0

        //+v2.0
        try {
            // 查询此商品，库存是否存在
            Product product=productMapper.selectByProductNo(ProductNo);
            // 存在，即可下单
            if (product!=null && product.getTotal()>0){
                // 更新库存
                int result=productMapper.updateTotal(product);
                if (result>0) {
                    // 更新成功，插入订单记录
                    ProductRobbingRecord entity=new ProductRobbingRecord();
                    entity.setMobile(mobile);
                    entity.setProductId(product.getId());
                    productRobbingRecordMapper.insertSelective(entity);
                }
            }
        }catch (Exception e){
            log.error("处理抢单发生异常：mobile={} ",mobile);
        }
    }
}
