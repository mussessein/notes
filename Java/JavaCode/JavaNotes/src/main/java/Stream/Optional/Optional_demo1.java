package Stream.Optional;

import java.util.Optional;

/*
Optional<T>是一种包装器对象:更好的处理null

empty() 返回“空”Optional对象，静态方法

of(T value) 和 constructor 一样，创建Optional对象

ofNullable(T value) 传入的参数为null时返回“空”Optional，否则返回包装好value的Optional对象

isPresent() 判断Optional包装的对象是否为空

get()获取Optional包装的对象，包装的对象为null时产生NPE

orElse() 为空时返回指定的参数，否则返回内部包装的对象

map() 执行指定的“转换”方法，返回null时，可以包装为“空”的Optional对象

filter() 基于Optional，对对象的值进行安全的检查和过滤

flatMap(T, Optional<U>)  先简单来说吧，有点流模式的包装转换器，后面这个是重点需要关注的对象

 */
public class Optional_demo1 {

    public static void main(String[] args) {

        Optional<String> op1 = null;
        // 当Optionnal对象不存在时,可以设置默认值
        String result = op1.orElse("");

    }
}
