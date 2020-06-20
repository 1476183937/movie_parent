package com.hnust.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Title:专门用户测试的controller
 * @Author: ggh
 * @Date: 2020/5/17 12:10
 */
@Controller
public class TestController {


    @Resource
    private KafkaTemplate<String , Object> kafkaTemplate;

    @Value("${kafka.MovieRating.topic}")
    private String topic;

    @RequestMapping("/kafka/test")
    @ResponseBody
    public String kafkaTest(){

        System.out.println(topic);
        kafkaTemplate.send(topic,"12|22|33|44");

        return "success";
    }

    @RequestMapping("/test1")
    public String test1(@RequestParam(value = "val",required = false) String val, ModelMap modelMap){
        modelMap.addAttribute("test1Text",UUID.randomUUID().toString());
        modelMap.addAttribute("testVal",UUID.randomUUID().toString()+"val");

        System.out.println("val===" + val);

        return "test1";
    }


    @RequestMapping("/test2")
    public String test2(ModelMap modelMap){
        modelMap.addAttribute("test2Text",UUID.randomUUID().toString());

        return "test2";
    }

    @RequestMapping("/change1")
    @ResponseBody
    public void change1(ModelMap modelMap){
        modelMap.addAttribute("test1Text",UUID.randomUUID().toString());
    }

    @RequestMapping("/change2")
    @ResponseBody
    public void change2(ModelMap modelMap){
        modelMap.addAttribute("test2Text",UUID.randomUUID().toString());
    }

    @RequestMapping("/test3")
    public String test3(ModelMap modelMap){
//        modelMap.addAttribute("test2Text","222222");
//        this.test1(modelMap);
//        this.test2(modelMap);
        modelMap.addAttribute("test1Text",UUID.randomUUID().toString());
        modelMap.addAttribute("test2Text",UUID.randomUUID().toString());
        modelMap.addAttribute("testVal",UUID.randomUUID().toString()+"val");

        return "test3";
    }

}
