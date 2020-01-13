package Kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
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
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "c2");
        // 每次都从头消费，重启消费者offset归0
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 创建消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        // 订阅topic
        kafkaConsumer.subscribe(Collections.singleton("test"));
        /**
         * 只能消费实时数据
         * 要想实现--from-beginning
         * 需要重置offset
         */
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                String key = record.key();
                long offset = record.offset();
                int partition = record.partition();
                System.out.println(
                        "key:"+ "==>" + key + " partition:"+"==>" + partition + " offset:" +"==>" + offset);
            }
        }
    }
}
