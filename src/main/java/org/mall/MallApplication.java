package org.mall;

import org.mall.aspect.MyAspect;
import org.mall.interceptor.Interceptor01;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Spring Boot应用的标识
@SpringBootApplication(scanBasePackages = {"org.mall"})
//mapper接口类扫描包配置，用这个注解可以注册mybatis mapper接口类
@MapperScan("org.mall.dao")
//@ServletComponentScan("org.mall.filter")
public class MallApplication implements WebMvcConfigurer {

//    定义切面
    @Bean(name = "MyAspect")
    public MyAspect initMyaspect(){
        return new MyAspect();
    }

    public static void main(String[] args) {
        // 程序启动入口,启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(MallApplication.class,args);
    }


//    下面这个方法是注册拦截器，需要实现WebMvcConfigurer接口
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor01 = registry.addInterceptor(new Interceptor01());
        interceptor01.addPathPatterns("/commodity/deleteById");
        interceptor01.order(1);
    }
}
