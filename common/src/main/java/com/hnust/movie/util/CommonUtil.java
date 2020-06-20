package com.hnust.movie.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/27 20:13
 */
public class CommonUtil {


    /**
    *@title:
    *@description: 根据request获取ip
    *@param: request
    *@author:ggh
    *@updateTime: 2020/5/27 20:16
    **/
    public static String getIpByRequest(HttpServletRequest request){

        //获取ip
        String ip = request.getHeader("x-forwarded-for");//获取通过ngnix转发的客户端ip
        if (StringUtils.isBlank(ip)){
            ip = request.getRemoteAddr(); //获取request里的ip

            //如果request里也没有ip，就直接指定
            if (StringUtils.isBlank(ip)){
                ip = "127.0.0.1";
            }
        }

        return ip;
    }

}
