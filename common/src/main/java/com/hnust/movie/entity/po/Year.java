package com.hnust.movie.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@title:
*@description: 表示所有电影信息中锁包含的年份
*@param: null
*@author:ggh
*@updateTime: 2020/5/20 10:45
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Year {

    private Integer yid;        //年份id
    private String year;        //年份
    private Byte parentCategory;//父类别id->0:电影 1:动漫 。。。

}