package EightClass;

import java.lang.reflect.Field;

public class demo_short {

    public static void main(String[] args) {
        short s1 = 2;
        //s1=s1+1;//报错：不能将s1强转为int（1为int类型）

        //下面的不会报错，（隐式类型转换）相当于s1=(short)(s1+1)
        s1 += 1;

        // float f =1.1 报错(1.1字面是属于double类型，不能向下转型)
        float f1 = (float) 1.1;
        float f2 = 1.1f;

    }
}
