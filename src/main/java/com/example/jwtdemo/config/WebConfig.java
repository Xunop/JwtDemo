package com.example.jwtdemo.config;

import com.example.jwtdemo.filter.Myfilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xun
 * @create 2023/2/13
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<Myfilter> filterRegistrationBean() {
        FilterRegistrationBean<Myfilter> registrationBean = new FilterRegistrationBean<>(new Myfilter());
        // 过滤所有路径
        registrationBean.addUrlPatterns("/*");
        // 添加不过滤路径
        registrationBean.addInitParameter("noFilter", "/login");
        registrationBean.setName("myFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
