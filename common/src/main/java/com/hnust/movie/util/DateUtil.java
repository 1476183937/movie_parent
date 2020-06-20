package com.hnust.movie.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/28 22:07
 */
public class DateUtil {


    public static String format(Date date,String format){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateStr = simpleDateFormat.format(date);

        return dateStr;
    }

}
