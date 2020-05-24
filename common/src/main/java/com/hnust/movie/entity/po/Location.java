package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@title:
*@description: 表示所有电影数据中包含的地区
*@param: null
*@author:ggh
*@updateTime: 2020/5/20 10:47
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private Integer lid;        //地区id
    private String location;    //地区名称
    private Byte parentCategory;//父类别id->0:电影 1:动漫 。。。

}