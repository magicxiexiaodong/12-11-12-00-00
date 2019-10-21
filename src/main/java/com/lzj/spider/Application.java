package com.lzj.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@MapperScan({"com.lzj.**.dao.**"})
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(new Class[]{Application.class}).run(args);
    }
}