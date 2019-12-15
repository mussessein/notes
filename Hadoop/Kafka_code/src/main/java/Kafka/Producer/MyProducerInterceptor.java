package Kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;

public class MyProducerInterceptor {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put("retries", 1);
        properties.put("batch.size", 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /**
         * 添加拦截器:
         * 1 添加单个
         * 2 添加多个,传入list
         */
        ArrayList<String> interceptorList = new ArrayList<>();
        interceptorList.add("Kafka.Interceptor.TimeInterceptor");
        interceptorList.add("Kafka.Interceptor.CounterInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorList);
        // 创建生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {

            ProducerRecord<String, String> message = new ProducerRecord<String, String>("bigdata", "hello" + i);
            // send发送消息
            kafkaProducer.send(message);
        }
        // 关闭连接:会清空内存
        kafkaProducer.close();
    }
}
