## WebMvcAutoConfiguration

SpringBoot通过此类，已经自动配置了SpringMVC的各项内容

## 扩展SpringMVC

在WebMvcAutoConfiguration已经配置好了MVC的前提下

想要添加更多配置

只需要创建一个Java类来实现`WebMvcConfigurer`，并加上`@Configuration`注解



```java
/**
 * 通过实现WebMvcConfigurer来扩展MVC功能
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
	// 视图映射：所有hello下请求，来到success页面
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("success");
    }
}

```

## 修改SpringBoot的默认配置

模式：

​	1）、SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean、@Component）如果有就用用户配置的，如果没有，才自动配置；如果有些组件可以有多个（ViewResolver）将用户配置的和自己默认的组合起来；

​	2）、在SpringBoot中会有非常多的xxxConfigurer帮助我们进行扩展配置

​	3）、在SpringBoot中会有很多的xxxCustomizer帮助我们进行定制配置