package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@title:
*@description: 表示所有电影数据中包含的类别
*@param: null
*@author:ggh
*@updateTime: 2020/5/20 10:48
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private Integer cid;        //类别id
    private String categoryName;//类别名称
    private Byte parentCategory;//父类别id->0:电影 1:动漫 。。。

}