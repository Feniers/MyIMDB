package com.example.myimdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.myimdb.dao")
public class MyImdbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyImdbApplication.class, args);
    }

}
