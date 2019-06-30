package com.sh.shdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.sh.shdemo.control","com.sh.shdemo.Config"})
@EnableJpaRepositories(basePackages = "com.sh.shdemo.dao")
@EntityScan(basePackages = "com.sh.shdemo.entity")
public class ShdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShdemoApplication.class, args);
    }

}
