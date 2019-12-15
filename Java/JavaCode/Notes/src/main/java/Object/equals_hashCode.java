package Object;

import java.util.HashSet;

/*
�ڸ��� equals() ����ʱӦ�����Ǹ��� hashCode() ��������֤�ȼ۵���������ɢ��ֵҲ��ȡ�

1.equals():�ж����������Ƿ�ȼ�
    1��.�������ͣ�(�ǰ�װ��)û��equals������==�жϻ��������Ƿ����
    2��.�������ͣ���������װ�ࣩ==�ж����������Ƿ�����ͬһ������equals�ж����õĶ����Ƿ�ȼ�
2.hashCode()������ɢ��ֵ
    1���ȼ۵Ķ���ɢ��ֵһ����ȡ�ɢ��ֵ��ȵĶ���һ���ȼ�
    2���ڸ��� equals() ����ʱӦ�����Ǹ��� hashCode() ��������֤�ȼ۵���������ɢ��ֵҲ��ȡ�
 */
public class equals_hashCode {
    private int x;
    private int y;
    private int z;

    public equals_hashCode(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        //����ͬһ������ֱ�ӷ���true
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        //���ò���ͬһ�����󣬵�������ͬ�ĸ��ࣨ����ǿת�������ж϶�������
        equals_hashCode that = (equals_hashCode) o;
        if (x != that.x) return false;
        if (y != that.y) return false;
        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
/*
�ڸ��� equals() ����ʱӦ�����Ǹ��� hashCode() ��������֤�ȼ۵���������ɢ��ֵҲ��ȡ�

�������дhashCode�������ᵼ�£��ж�equals��ͬ������ɢ��ֵ����ͬ��
�Ӷ�����ӵ�ͬһ��HashSet�����У�HashSet����ɢ��ֵ�жϼ����ڵĶ����Ƿ���ͬ��
 */
    public static void main(String[] args) {
        equals_hashCode e1 = new equals_hashCode(1, 1, 1);
        equals_hashCode e2 = new equals_hashCode(1, 1, 1);
        System.out.println(e1.equals(e2)); // true
        HashSet<equals_hashCode> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());   //�����дHashCode������2���ᵱ����������
    }
}
