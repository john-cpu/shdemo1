package com.sh.shdemo.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
// 读取配置文件的，通过Environment读取
@PropertySource("classpath:application.properties")
// scan the package of the annotated configuration class for Spring Data repositories
@EnableJpaRepositories(basePackages = "com.sh.shdemo.dao")
// Enables Spring's annotation-driven transaction management
@EnableTransactionManagement
public class JpaConfig {
    @Autowired
    private Environment env;

    /**
     * 1.配置数据源
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName(env.getRequiredProperty("db.driver"));
        source.setUrl(env.getRequiredProperty("db.url"));
        source.setUsername(env.getRequiredProperty("db.username"));
        source.setPassword(env.getRequiredProperty("db.password"));
        return source;
    }

    /**
     * 2.配置EntityManagerFactory
     *
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        // 配置数据源
        factory.setDataSource(dataSource());
        // VendorAdapter
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // entity包扫描路径
        factory.setPackagesToScan(env.getRequiredProperty("packages.to.scan"));
        // jpa属性
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory;
    }
    /**
     * 3.事务管理器配置
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    /**
     * 把HibernateExceptions转换成DataAccessExceptions
     */
    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    /**
     * hibernate配置
     * @return
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // 显示sql语句
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        // 格式化sql语句
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        // 方言
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        // 自动生成表
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        // 名字策略
        properties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
        return properties;
    }

}
