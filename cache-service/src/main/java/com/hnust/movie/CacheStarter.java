package com.hnust.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/17 20:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CacheStarter {

    public static void main(String[] args) {
        SpringApplication.run(CacheStarter.class, args);
    }

}
