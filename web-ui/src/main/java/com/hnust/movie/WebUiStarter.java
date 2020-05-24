package com.hnust.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/12 21:48
 */

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
public class WebUiStarter {

    public static void main(String[] args) {
        SpringApplication.run(WebUiStarter.class, args);
    }

}
