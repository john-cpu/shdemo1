package com.sh.shdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@SpringBootApplication
@ComponentScan({"com.sh.shdemo.control","com.sh.shdemo.Config","com.sh.shdemo.Service"})
@EnableJpaRepositories(basePackages = "com.sh.shdemo.dao")
@EntityScan(basePackages = "com.sh.shdemo.entity")
@EnableCaching
@RestController
public class ShdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShdemoApplication.class, args);
    }
    @RequestMapping("/user") // 有user权限才能访问
    public Principal user(Principal user) {
        return user;
    }
    @RequestMapping("/admin") // 有admin权限才能访问
    public Principal admin(Principal admin) {
        return admin;
    }
}
