package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean WrapperFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WrapperFilter());
        bean.setName("wrapperFilter");     // 命名
        bean.addUrlPatterns("/*");
        bean.setOrder(1);   //優先級別最高
        return bean;
    }
    @Bean
    public FilterRegistrationBean TestFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        });
        bean.setName("TestFilter");
        bean.addUrlPatterns("/test/*");
        bean.setOrder(2);
        return bean;
    }
}