
====================================整体布局==========================================

一：父module：全局定义，组织的作用，父的src可以删掉。
    全局pom.xml的配置：<modules>
                         <module>oa_dao</module>
                         <module>oa_biz</module>
                         <module>oa_web</module>
                     </modules>
    关联各个modules

二.oa_dao持久层（空module）：
        -结构：
            -entity：实体类(成员变量对应数据库的每个数据)
            -dao   ：持久化操作，对数据库操作的单个方法
            -glogbal：数据字典类，存放需要的常量

        -配置好oa_dao下的spring-dao.xml配置文件：
            1.扫描dao包
            2.数据源dataSource（四大参数）
            3.SessionFactory
                -dataSource
                -typeAliasesPackage配置别名，使用dao下的类，可以直接使用类名，不用写全类名
            4.basePackage：自动扫描接口，关联xml文件

三.oa_zib事务层（空module）：
        -结构：
            -biz：事务方法接口(先声明了每个业务方法)
            -bizimpl：将dao层的持久化操作，多个方法，封装为一个业务方法！！！(将接口方法实现)

        -配置spring-biz.xml：
            1.导入spring-dao。扫描biz
            2.aop自动代理 <aop:aspectj-autoproxy/>
            3.配置声明式事务：
                -声明事务管理器：transationManager-dataSource
                -声明通知tx：关联transationManager，设置只读方法
                -aop关联：将txAdvice和切入点关联


四.oa_web视图层（WEBmodule）：
        -结构：
            -cotroller：控制页面跳转
            -dto：   类似于entity，
            -global：
                1.编码过滤器：wen.xml配置
                2.登陆拦截器
            -webapp：静态资源
        -配置web下的spring.xml配置文件：
            1.引入spring-bin.xml，开启自动扫描
            2.mvc注解驱动，注解相关
            3.mvc静态资源，试图转换：设置文件prefix / suffix

        -配置web.xml（springMVC的加载）
            1.配置servlet和servlet-mapping
                -告诉DispatcherServlet，此项目的springMVC配置在哪里
            2.编码过滤器：
            3.配置一个系统本身存在的default Servlet
                -将静态文件目录下的请求，交给default处理


==============================================================================================================
四个功能的实现：
    -部门管理：
            部门信息的增删改查
            1.dao层
                三大类：entity（实体类）：对应数据库表设置属性：一表一类，一列一属性
                       dao（持久层）：全部是接口，一个实体类对应一个dao接口，每个dao接口列出此数据库表需要用的操作sql方法
                       mapper.xml文件：一个接口映射一个xml，xml中对每个dao的方法，映射具体的sql语句

            2.biz业务层
                biz接口:   在dao持久层之上，整合一个个小方法，组成一个业务
                biz实现类： 业务层的操作方法，内部基本是调用了dao层的方法进行操作

            3.web层
                控制器类：向页面传递信息的层，
                        主要完成，页面中需要的方法，以及页面之间的跳转
                页面
    -员工管理
    -登录以及个人中心：
            登录限制：拦截器！
                LoginInterceptor.java
                1.对登录界面本身不进行拦截：拿到含有/login的url，进行放行。url.toLowerCase().indexOf("login")>=0
                2.对于其他路径，都必须登录之后进行访问，需要拦截，通过判断HttpSession对象的Attribute是否含有对应的登录对象
                3.否则，返回登陆界面
                3.xml中配置拦截器！
    -报销单的处理

================================================注解======================================================
@Repository("employeeDao")
    dao层的bean(数据访问层)
    此注解是让Spring找到这个接口的实现类,以便后续需要的时候注入
    (其实不写这个注解,也可以找到实现类,因为MapperScannerConfigurer这个bean,已经扫描了dao包)

@Service("employeeBiz")

@Autowired
    自动装配
    作用:消除代码Java代码里面的getter/setter与bean属性中的property
    此注释会自动在容器中查找对应的bean
@Qualitier()
    如果容器中有多个匹配的bean,需要此注解指示指定的bean

@Resource
    与@Autowired有着相同的作用,不同的实现
    @Resource:按照name属性匹配bean---->属于j2EE的注解
    @Autowired:按照type属性匹配bean--->属于Spring的注解

@Service("employeeBiz")
    业务层的bean
    与@Repository类似
    声明此类是一个bean,并标注id.以便后续需要的时候注入

@Controller("employeeController")
    表现层的bean

@RequestMapping("/employee")
    URL

@param()
    dao层方法的参数注解
    使用此注解之后,在xml中的数据库操作,可以直接使用此参数变量
