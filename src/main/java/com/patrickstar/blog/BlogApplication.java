package com.patrickstar.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@EnableTransactionManagement
//springboot定时任务使用，加上enablescheduling注解
@EnableScheduling
public class BlogApplication{
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
