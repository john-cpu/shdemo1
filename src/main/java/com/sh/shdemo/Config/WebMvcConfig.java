package com.sh.shdemo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("logout").setViewName("login");
        registry.addViewController("/user/u").setViewName("user/uuu");
        registry.addViewController("/AdminPage/a").setViewName("AdminPage/admin");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/e").setViewName("myerror");
        registry.addViewController("/user/cm").setViewName("user/cartManage");
        registry.addViewController("/user/regi").setViewName("myerror");

    }
}
