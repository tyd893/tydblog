package com.my.tydblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableTransactionManagement
public class MyApplication {
    private static Logger logger = LoggerFactory.getLogger(MyApplication.class);
    public static void main(String[] args) {
        logger.info("Service 正在启动");
        SpringApplication.run(MyApplication.class, args);
    }
}
