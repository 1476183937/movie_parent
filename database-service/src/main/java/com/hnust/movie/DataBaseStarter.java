package com.hnust.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 10:54
 */
@SpringBootApplication
@MapperScan(basePackages = "com.hnust.movie.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class DataBaseStarter {

    public static void main(String[] args) {
        SpringApplication.run(DataBaseStarter.class, args);
    }

}
