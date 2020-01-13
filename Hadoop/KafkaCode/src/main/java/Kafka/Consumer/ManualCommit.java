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
 * 自动提交：每次消费之后，提交自己的offset
 * 关闭自动提交：不更新offset，每次都从未提交的offset开始消费；
 * 这里实现：手动提交
 * 两种提交方式：同步提交commitSync，异步提交commitAsync
 */
public class ManualCommit {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "c2");
        // 创建消费者对象
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(props);
        // 订阅topic
        TopicPartition test = new TopicPartition("test", 0);
        kafkaConsumer.assign(Collections.singleton(test));

        /**
         * 只能消费实时数据
         * 要想实现--from-beginning
         * 需要重置offset
         */
        while (true) {
            ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                String topic = record.topic();
                String key = record.key();
                long offset = record.offset();
                int partition = record.partition();
                System.out.println(
                        "Topic"+"==>"+topic+" partition:"+"==>" + partition + "key:"+ "==>" + key + " offset:" +"==>" + offset);
            }
            /**
             * 同步提交（commitSync）：阻塞，提交成功，才会下一次数据拉取
             * 异步提交（commitAsync）:提交线程和poll线程，异步
             */
            kafkaConsumer.commitAsync();
        }
    }
}
