package Kafka.Interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 计数拦截器
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {

    int success;
    int error;

    /**
     * 此拦截器,意在计数,不需要动数据,直接返回
     */
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        return record;
    }

    /**
     * ack:成功收到metadata,失败收到exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            success++;
        } else
            error++;

    }

    @Override
    public void close() {
        System.out.println("success:" + success);
        System.out.println("error:" + error);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
