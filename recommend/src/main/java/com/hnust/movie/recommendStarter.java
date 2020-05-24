package com.hnust.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/23 9:46
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class recommendStarter {

    public static void main(String[] args) {
        SpringApplication.run(recommendStarter.class, args);
    }

}
