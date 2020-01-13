package Kafka.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 生产者测试
 * 先开启消费者，启动main方法
 */
public class MyProducer {
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
            // 创建记录ProducerRecord("Topic","partition","key","value")
            ProducerRecord<String, String> message =
                    new ProducerRecord<String, String>("test", 0,"MyProducer","hello" + i);
            // send：异步方法，发送之后，立即返回，并不是说调用了，就真的发送成功了；
            kafkaProducer.send(message);
        }
        // 关闭连接:会清空内存
        kafkaProducer.close();
    }
}
