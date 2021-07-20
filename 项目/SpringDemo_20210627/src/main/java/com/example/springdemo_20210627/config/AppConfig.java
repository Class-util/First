package com.example.springdemo_20210627.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:SpringMVC中的自定义全局配置（实现后端接口的映射）
 * User:吴博
 * Date:2021 07 17
 * Time:21:21
 */
    //将一个类注册到Spring中,是spring的配置文件
    @Configuration
    //约定大于配置，要实现配置信息的设置，必须实现WebMvcConfigurer接口
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //添加路由前置
        configurer.addPathPrefix("api",c -> true);
    }

    //配置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**") //拦截所有的接口
//                .excludePathPatterns("/api/log/login") //排除一个路径
//                .excludePathPatterns("/api/log/reg2") //排除一个路径
//                .excludePathPatterns("/api/log/reg1") //排除一个路径
//                .excludePathPatterns("/login.html")
//                .excludePathPatterns("/reg.html") // 不拦截注册页面
//                .excludePathPatterns("/**/**.html") // 不拦截注册页面
//                .excludePathPatterns("/**/*.css")
//                .excludePathPatterns("/**/*.js")
//                .excludePathPatterns("/**/*.jpg")
//                .excludePathPatterns("/**/*.jpeg")
//                .excludePathPatterns("/reg_err.html")
//                .excludePathPatterns("/reg_success.html")
//                .excludePathPatterns("/**/*.png");
    }
}
