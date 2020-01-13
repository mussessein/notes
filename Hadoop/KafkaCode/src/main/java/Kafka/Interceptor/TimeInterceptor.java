package Kafka.Interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 时间拦截器
 * 实现ProducerInterceptor
 */
public class TimeInterceptor implements ProducerInterceptor<String, String> {

    /**
     * onSend:对拦截的数据进行处理:
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        /**
         * 拿到value,重新包装
         * 重新封装新的record对象,返回
         */
        String value = record.value();
        String newValue = System.currentTimeMillis() + "," + value;
        // 将修改后的value，封装进record，返回
        ProducerRecord<String, String> newRecord =
                new ProducerRecord(record.topic(), record.partition(), record.key(), newValue);
        return newRecord;
    }
    /**
     * onAcknowledgement对返回的确认信息，进行拦截处理
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }
    @Override
    public void close() {

    }
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
