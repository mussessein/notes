package Kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 同步消息发送模式：
 */
public class MyProducerFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 1);
        properties.put("batch.size", 16384);
        properties.put("liner.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /**
         *  通过配置文件创建生产者对象
         */
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> message = new ProducerRecord<String, String>("test", "hello" + i);
            /**
             * 同步发送,send返回 Future对象,调用get()
             * 返回RecordMetadata元数据记录，记录了发送的topic，partition，offset
             */
            RecordMetadata metadata = kafkaProducer.send(message).get();
            String topic = metadata.topic();
            int partition = metadata.partition();
            long offset = metadata.offset();
            System.out.println("Topic=>"+topic+" partition=>"+partition + " offset=>" +offset);

        }
        // 关闭连接:会清空内存
        kafkaProducer.close();
    }
}
