package Object;

import java.util.HashSet;

/*
在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。

1.equals():判断两个对象是否等价
    1）.基本类型：(非包装类)没有equals方法，==判断基本类型是否相等
    2）.引用类型：（包括包装类）==判断两个变量是否引用同一个对象，equals判断引用的对象是否等价
2.hashCode()：返回散列值
    1）等价的对象，散列值一定相等。散列值相等的对象不一定等价
    2）在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。
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
        //引用同一个对象，直接返回true
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        //引用不是同一个对象，但是有相同的父类（可以强转），再判断对象属性
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
在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。

如果不重写hashCode方法，会导致，判断equals相同，但是散列值不相同。
从而被添加到同一个HashSet集合中（HashSet根据散列值判断集合内的对象是否相同）
 */
    public static void main(String[] args) {
        equals_hashCode e1 = new equals_hashCode(1, 1, 1);
        equals_hashCode e2 = new equals_hashCode(1, 1, 1);
        System.out.println(e1.equals(e2)); // true
        HashSet<equals_hashCode> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println(set.size());   //如果重写HashCode，返回2，会当成两个对象
    }
}
