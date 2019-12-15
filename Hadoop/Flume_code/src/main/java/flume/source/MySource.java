package flume.source;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

public class MySource extends AbstractSource implements Configurable, PollableSource {

    // 定义前缀,后缀;工程中是从配置文件中读取
    private String prefix;
    private String subfix;
    /**
     * 1. 给事件定义一些前缀,来封装成我们需要的事件的格式
     * 2. 前缀是从配置文件中读取出来
     */
    public void configure(Context context) {
        prefix=context.getString("prefix","prefix");
        subfix=context.getString("subfix","subfix");
    }

    public Status process() throws EventDeliveryException {
        /**
         * 核心方法
         * 1. 接收数据
         * 2. 封装成事件(封装为Header和Boby)
         * 3. 将事件传给Channel
         */
        Status status=null;
        try {
            for (int i = 0; i < 5; i++) {
                // 拿到数据,封装成Event
                SimpleEvent event = new SimpleEvent();
                // 对Event进行自定义处理
                event.setBody((prefix+"<=>"+i+"<=>"+subfix).getBytes());
                // 将事件传给了Channel
                getChannelProcessor().processEvent(event);
                status = Status.READY;
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = Status.BACKOFF;
        }
        // 让此方法有一些间隙,2秒执行一次
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return status;
    }

    public long getBackOffSleepIncrement() {
        return 0;
    }

    public long getMaxBackOffSleepInterval() {
        return 0;
    }


}
