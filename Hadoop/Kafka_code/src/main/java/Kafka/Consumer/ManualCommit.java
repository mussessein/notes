package Kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

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
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        // 关闭自动提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "bigdata");
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
            /**
             * 同步提交：阻塞，提交成功，才会下一次数据拉取
             * 异步提交:提交线程和poll线程，异步
             */
            kafkaConsumer.commitAsync();
        }

    }

}
