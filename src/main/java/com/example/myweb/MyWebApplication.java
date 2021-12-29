package com.example.myweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.myweb.mapper.*")
public class MyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebApplication.class, args);
    }

}