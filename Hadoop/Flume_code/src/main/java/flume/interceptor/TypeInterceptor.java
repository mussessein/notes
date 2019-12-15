package flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义Flume的Interceptor
 * 1. 实现Interceptor接口
 * 2. 重写4个方法
 * 3. 定义静态内部Builder类实现Interceptor.Builder
 */
public class TypeInterceptor implements Interceptor {

    // 声明一个事件集合
    private List<Event> headerEvents;

    public void initialize() {
        // 初始化事件集合
        headerEvents=new ArrayList<Event>();
    }

    /**
     * 单个事件拦截
     * @param event：接收的每个Event
     * @return
     */
    public Event intercept(Event event) {
        /**
         * 1. 获取事件的头信息(Key-Value)
         * 2. 获取事件的body信息(字节流)，包装成String
         * 3. 业务逻辑：根据body的日志类型，添加不同的头信息到headers
         */
        Map<String, String> headers = event.getHeaders();
        byte[] bodyBtyes = event.getBody();
        String body = new String(bodyBtyes);
        // 业务逻辑:全为英文返回event1,否则返回event2
        if (body.contains("hello")){
            headers.put("type","event1");
        }else {
            headers.put("type","event2");
        }
        return event;
    }

    /**
     * 批量事件的拦截
     * @param list
     * @return
     */
    public List<Event> intercept(List<Event> list) {
        /**
         * 1. 清空集合
         * 2. 为每一个事件添加头信息
         * 3. 返回处理过后的headers集合
         */
        headerEvents.clear();

        for (Event event:headerEvents){
            Event res = intercept(event);
            headerEvents.add(res);
        }
        return headerEvents;
    }

    public void close() {
    }
    // 静态内部类
    public static class Builder implements Interceptor.Builder{

        public Interceptor build() {

            return new TypeInterceptor();
        }

        public void configure(Context context) {

        }
    }
//
//    public static void main(String[] args) {
//        String a ="aaaa";
//        String b ="111";
//        boolean a1 = a.matches("[a-zA-Z]+");
//        boolean b1 = b.matches("[a-zA-Z]+");
//        System.out.println(a1);
//        System.out.println(b1);
//    }
}
