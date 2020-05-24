package com.hnust.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title:搜索服务
 * @Author: ggh
 * @Date: 2020/5/18 21:31
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SearchStarter {

    public static void main(String[] args) {
        SpringApplication.run(SearchStarter.class, args);
    }

}
