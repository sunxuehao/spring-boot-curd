package org.mall.config;

import org.mall.filter.MyFilter01;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter01());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter01HaHaHa");
        registration.setOrder(1);
        return registration;
    }

}
