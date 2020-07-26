package com.hnust.movie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: 用户登录注解
 * @Author: ggh
 * @Date: 2020/5/25 20:51
 */
@Target(ElementType.METHOD)  //可以使用的地方：在方法上生效
@Retention(RetentionPolicy.RUNTIME) //范围在运行时有用
public @interface LoginRequired {

    //用于指示是否必须登录，有的情况可以登录也可以不登录
    boolean mustLogin() default false;

}
