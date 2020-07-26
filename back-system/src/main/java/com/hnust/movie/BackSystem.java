package com.hnust.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title: 后台管理系统
 * @Author: ggh
 * @Date: 2020/7/14 9:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BackSystem {

    public static void main(String[] args) {
        SpringApplication.run(BackSystem.class, args);
    }

}
