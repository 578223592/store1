package com.swx.store1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
@MapperScan(basePackages = "com.swx.store1.mapper")
public class Store1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Store1Application.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        System.out.println("store2 started at http://localhost:" + serverPort);
    }
}
