package Kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 消费者
 */
public class MyConsumer {
    public static void main(String[] args) {
        /**
         * 创建消费者的配置信息,并put配置
         * 1.集群ip端口
         * 2.开启自动提交
         * 3. 自动提交延迟
         * 4. key，value序列化类
         * 5. 消费者组id
         */
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "c2");
        /**
         * 创建消费者---KafkaConsumer
         * 订阅主题---subscribe---需要传入一个集合
         * 订阅指定Topic+分区---assign
         * 拉取消息---poll---需要参数timeout，没消息时的等待时间
         */
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(props);
        // 仅订阅分区
        // kafkaConsumer.subscribe(Collections.singleton("test"));
        TopicPartition test = new TopicPartition("test", 0);
        kafkaConsumer.assign(Collections.singleton(test));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                String topic = record.topic();
                String key = record.key();
                long offset = record.offset();
                int partition = record.partition();
                System.out.println(
                        "Topic"+"==>"+topic+" partition:"+"==>" + partition + "key:"+ "==>" + key + " offset:" +"==>" + offset);
            }
        }
    }
}
