package Kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

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
         * 3.
         * 4.key，value序列化类
         */
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        /**
         * 自动提交：每次消费之后，提交自己的offset
         * 关闭自动提交：不更新offset，每次都从未提交的offset开始消费；
         */
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动提交延迟
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        /**
         * 创建消费者---KafkaConsumer
         * 订阅主题---subscribe---需要传入一个集合
         * 订阅指定分区---assign
         * 拉取消息---poll---需要参数timeout，没消息时的等待时间
         */
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singleton("bigdata"));
        /**
         * 只能消费实时数据
         * 要想实现--from-beginning
         * 需要重置offset
         */
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecords : records) {
                System.out.println(
                        consumerRecords.key() + "------" + consumerRecords.value());
            }
        }
    }
}
