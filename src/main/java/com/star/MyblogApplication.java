package com.star;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
@SpringBootConfiguration
@SpringBootApplication
@EnableCaching   //注解配置启用缓存
@ServletComponentScan
@MapperScan("com.star.mapper")
@ComponentScan("com.star.controller")
@ComponentScan("com.star.domain.entity")
@ComponentScan("com.star.service")
public class MyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }

}
