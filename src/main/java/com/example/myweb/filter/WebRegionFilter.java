package com.example.myweb.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebRegionFilter implements WebMvcConfigurer {

    @Autowired
    private LoginFilter loginFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginFilter).addPathPatterns("/log/udp_pwd").addPathPatterns("/order/**");
    }
}
