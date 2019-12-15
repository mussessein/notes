package Kafka.Partitioner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyPartition_Test {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put("retries", 1);
        properties.put("batch.size", 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 指定分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "Kafka.Partitioner.MyPartitioner");
        /**
         *  通过配置文件创建生产者对象
         */
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++) {
            /**
             * 创建生产者记录对象
             * 参数：
             * 1. topic
             * 2. Partition
             * 3.
             */
            ProducerRecord<String, String> message = new ProducerRecord<String, String>("bigdata", "hello" + i);
            // send发送消息
            kafkaProducer.send(message, (metadata, exception) -> {
                // exception==null，即成功
                if (exception == null) {
                    /**
                     * 返回partition,offset
                     */
                    int partition = metadata.partition();
                    long offset = metadata.offset();
                    System.out.println(partition + "-------" + offset);
                } else {
                    exception.printStackTrace();
                }
            });
        }
        // 关闭连接:会清空内存
        kafkaProducer.close();
    }
}
