package com.example.myimdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring-Boot启动类
 *
 * @MapperScan("com.example.myimdb.dao") 注解表示扫描com.example.myimdb.dao包下的所有Mapper接口，因此不需要在每个Mapper接口上添加@Mapper注解
 */
@SpringBootApplication
@MapperScan("com.example.myimdb.dao")
public class MyImdbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyImdbApplication.class, args);
    }

}
