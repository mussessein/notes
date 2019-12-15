package Kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 同步模式：
 */
public class MyProducerFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
         *  通过配置文件创建生产者对象
         */
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> message = new ProducerRecord<String, String>("bigdata", "hello" + i);
            /**
             * 同步发送,send返回Future对象,调用get()
             */
            kafkaProducer.send(message).get();
        }
        // 关闭连接:会清空内存
        kafkaProducer.close();
    }
}
