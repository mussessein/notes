package Kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * 不重置offset,消费者默认从最新的消息消费(lastest)
 * 重置offset(让消费者从最早的消息,开始消费,earliest),
 * 从头消费partition,配置AUTO_OFFSET_RESET_CONFIG为"earliest",并且满足条件:
 * 1.消费者换了组
 * 2.消费者的offset,已经被集群给删了,不存在了
 * 以上两种情况,可以重置offset为topic最新的offset;
 */
public class MyConsumerResetOffset {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        /**
         * 重置消费者offset
         * 并且,需要换组,才能从头消费
         */
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "small");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 创建消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        // 订阅topic
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
                        consumerRecords.value());
            }
        }
    }
}
