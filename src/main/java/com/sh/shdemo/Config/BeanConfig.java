package com.sh.shdemo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

@EnableRedisHttpSession
@Configuration
public class BeanConfig {
    /**
     * 设置Session的token策略
     */
    @Bean
    public HeaderHttpSessionIdResolver httpSessionIdResolver() {
        return new HeaderHttpSessionIdResolver("token");
    }
}
