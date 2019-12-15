package mapreduce.FlowSum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
public class FlowSumReduce extends Reducer<Text, FlowBean, Text, FlowBean> {
    /*
     每传递一组数据<13688887777,FlowBean>,调用一次reduce方法
     reduce方法进行便利value,累加求和,然后输出
     */
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        /**
         * Text:手机号
         * key:JavaBean对象
         * values：所有手机号相同的JavaBean对象的迭代器，遍历values，统计同一手机号的总流量
         */
        long up_flow_counter = 0;
        long down_flow_counter = 0;
        // 遍历,计算流量
        for (FlowBean bean : values) {
            up_flow_counter += bean.getUp_flow();
            down_flow_counter += bean.getDown_flow();
        }
        // 输出
        context.write(key, new FlowBean(key.toString(), up_flow_counter, down_flow_counter));
    }
}
