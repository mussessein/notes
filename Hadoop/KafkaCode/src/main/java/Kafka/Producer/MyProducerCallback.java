package Kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 带回调的生产者
 */
public class MyProducerCallback {
    public static void main(String[] args) {
        /**
         * 创建Kafka生产者配置信息：ProducerConfig类中记录了Kafka需要的所有参数信息
         * 1.指定连接的Kafka集群
         * 2.ack应答级别
         * 3.发送失败的重试次数
         * 4.批次大小（一次发送多少大小数据）
         * 5.等待时间
         * 6.RecordAccumulator缓冲区大小
         * 7.指定key，value序列化类
         */
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
            ProducerRecord<String, String> message = new ProducerRecord<String, String>("test", "messuesein--" + i);
            /**
             * 发送消息：带回调
             * 传入CallBack函数接口,参数：
             * 1. RecordMetadata：成功返回元数据记录
             * 2. Exception：失败返回异常
             */
            kafkaProducer.send(message, (metadata, exception) -> {
                // exception==null，即成功
                if (exception == null) {
                    /**
                     * metadata记录元数据信息
                     */
                    String topic = metadata.topic();
                    int partition = metadata.partition();
                    long offset = metadata.offset();
                    System.out.println("Topic=>"+topic+" partition=>"+partition + " offset=>" +offset);
                } else {
                    exception.printStackTrace();
                }
            });
        }
        // 关闭连接:会清空内存
        kafkaProducer.close();
    }
}
