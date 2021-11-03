package com.swx.store1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;


@SpringBootApplication
@MapperScan(basePackages = "com.swx.store1.mapper")
public class Store1Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(Store1Application.class, args);
    }
}
