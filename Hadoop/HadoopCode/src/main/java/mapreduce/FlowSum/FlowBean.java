package mapreduce.FlowSum;


import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
FlowBean是自定义类
要在hadoop的各个节点之间传输,应该遵循hadoop的序列化机制
FlowBean必须实现相应的序列化接口org.apache.hadoop.io.Writable
必须实现：write\readFields的API
 */
public class FlowBean implements WritableComparable<FlowBean> {

    private String phone;// 用户手机号
    private long up_flow;// 上行总流量
    private long down_flow;// 下行总流量
    private long sum_flow;// 总流量

    // 反序列化必须有空参构造函数
    public FlowBean() {
    }

    public FlowBean(String phone, long up_flow, long down_flow) {
        this.phone = phone;
        this.up_flow = up_flow;
        this.down_flow = down_flow;
        this.sum_flow = up_flow + down_flow;
    }

    // 将对象数据序列化到流中
    @Override
    public void write(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(phone);
        dataOutput.writeLong(up_flow);
        dataOutput.writeLong(down_flow);
        dataOutput.writeLong(sum_flow);

    }

    // 反序列化——反射机制
    // 从数据流中读取字段,必须与序列化的顺序保持一致
    @Override
    public void readFields(DataInput dataInput) throws IOException {

        phone = dataInput.readUTF();
        up_flow = dataInput.readLong();
        down_flow = dataInput.readLong();
        sum_flow = dataInput.readLong();
    }

    @Override
    public int compareTo(FlowBean o) {
        return this.sum_flow>o.getSum_flow()?-1:1;
    }

    @Override
    public String toString() {
        return "\t"+up_flow + "\t" + down_flow + "\t" + sum_flow;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUp_flow() {
        return up_flow;
    }

    public void setUp_flow(long up_flow) {
        this.up_flow = up_flow;
    }

    public long getDown_flow() {
        return down_flow;
    }

    public void setDown_flow(long down_flow) {
        this.down_flow = down_flow;
    }

    public long getSum_flow() {
        return sum_flow;
    }

    public void setSum_flow(long sum_flow) {
        this.sum_flow = sum_flow;
    }

}
