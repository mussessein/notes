项目：springIOC

传统IOC：
    一：创建实体类，实体类属性
    二：配置xml文件：
        -创建此实体类的bean：class指明全类名（包.类）
            <bean id="CollectionBean" class="collectionBean.CollectionBean">
        -创建此实体类的属性配置，name指明属性名，通过value添加属性
            <property name="properties">

            </property>
    三：注入：
        1.导入xml
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Collection.xml");
        2.创建此对象的bean实例
        CollectionBean collectionBean=(CollectionBean)applicationContext.getBean("CollectionBean");

传统IOC内部执行过程见process包下process


方法的加强：
    在传统ioc的基础上，通过重写BeanPostProcessor接口的方法postProcessAfterInitialization，
    创建代理对象proxy的实例，对方法进行加强
    需要在xml中添加下面的bean，指明重写的方法所在类
        <bean class="StrongMethod.OverrideBeanPostProcessor"/>

SQEL注入：
    在xml中实现一个类调用另一个类的方法
        1.可以直接赋值给value       value="#{'男装'}
        2.可以通过方法返回值给value  value="#{OnSale.onsale()
        3.传入别的类的对象          value="#{Categroy}

P命名空间注入：
    在xml中实现，通过p命名空间向一个类中传入另一个类
    具体见    properties.xml