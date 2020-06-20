package com.hnust.movie;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/14 11:25
 */
public class Test {

    public static void main(String[] args) {


        System.out.println(Math.pow(2, 32));

        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(format);
    }

}
