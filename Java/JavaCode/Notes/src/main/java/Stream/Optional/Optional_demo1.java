package Stream.Optional;

import java.util.Optional;

/*
Optional<T>��һ�ְ�װ������:���õĴ���null

empty() ���ء��ա�Optional���󣬾�̬����

of(T value) �� constructor һ��������Optional����

ofNullable(T value) ����Ĳ���Ϊnullʱ���ء��ա�Optional�����򷵻ذ�װ��value��Optional����

isPresent() �ж�Optional��װ�Ķ����Ƿ�Ϊ��

get()��ȡOptional��װ�Ķ��󣬰�װ�Ķ���Ϊnullʱ����NPE

orElse() Ϊ��ʱ����ָ���Ĳ��������򷵻��ڲ���װ�Ķ���

map() ִ��ָ���ġ�ת��������������nullʱ�����԰�װΪ���ա���Optional����

filter() ����Optional���Զ����ֵ���а�ȫ�ļ��͹���

flatMap(T, Optional<U>)  �ȼ���˵�ɣ��е���ģʽ�İ�װת����������������ص���Ҫ��ע�Ķ���

 */
public class Optional_demo1 {

    public static void main(String[] args) {

        Optional<String> op1 = null;
        // ��Optionnal���󲻴���ʱ,��������Ĭ��ֵ
        String result = op1.orElse("");

    }
}
