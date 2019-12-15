package notes.springbootnots;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通过实现WebMvcConfigurer来扩展MVC功能
 *
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("success");
//    }
}
