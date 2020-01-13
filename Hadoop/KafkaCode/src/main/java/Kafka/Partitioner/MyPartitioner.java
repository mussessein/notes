package Kafka.Partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 在发送消息没有指定分区的情况下，会调用分区器给消息进行分区；
 * 自定义分区器：（默认是哈希分区）
 * 实现Partitioner
 */
public class MyPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 通过集群id，拿到当前topic分区数
        return 1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
