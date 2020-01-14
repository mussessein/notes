package flume.sink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySink extends AbstractSink implements Configurable {

    // 自定义前后缀
    private String prefix;
    private String subfix;
    // 我们测试将sink输出到logger
    private Logger logger = LoggerFactory.getLogger(MySink.class);

    public void configure(Context context) {
        prefix=context.getString("prefix","prefix");
        subfix=context.getString("subfix","subfix");
    }
    public Status process() throws EventDeliveryException {
        /**
         * 1. 获取Channel
         * 2. 从Channel中获取事务，数据
         * 3. 发送数据
         */
        Status status =null;
        /**
         * 拿到Channel
         * 获取事务
         * 开启事务---->拿到Event------>业务处理Event
         * 回滚
         */
        Channel channel = getChannel(); // 是一个synchronized同步方法
        Transaction transaction = channel.getTransaction();
        transaction.begin();
        try {
            Event event = channel.take();
            if (event!=null){
                // 业务处理Evnet
                String bobyString = new String(event.getBody());
                logger.info(prefix+bobyString+subfix);

                transaction.commit();
            }
            status=Status.READY;
        } catch (ChannelException e) {
            e.printStackTrace();
            // rollback
            transaction.rollback();
            status=Status.BACKOFF;
        }finally{
            // 关闭事务
            transaction.close();
        }
        return status;
    }
}
