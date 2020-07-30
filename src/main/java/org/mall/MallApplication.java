package org.mall;

import org.mall.aspect.MyAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//Spring Boot应用的标识
@SpringBootApplication(scanBasePackages = {"org.mall"})
//mapper接口类扫描包配置，用这个注解可以注册mybatis mapper接口类
@MapperScan("org.mall.dao")
public class MallApplication {

//    定义切面
    @Bean(name = "MyAspect")
    public MyAspect initMyaspect(){
        return new MyAspect();
    }

    public static void main(String[] args) {
        // 程序启动入口,启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(MallApplication.class,args);
    }
}
