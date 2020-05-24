package com.hnust.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title:在类别搜索页面选中的类别
 * @Author: ggh
 * @Date: 2020/5/20 13:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedCategoryVO {

    private int pcid;     //选中的父类别id->0:电影 1:动漫
    private String category; //选中的分类的值
    private String area;     //选中的地区的值
    private String year;     //选中的年份的值
    private String letter;   //选中的字母的值
    private String sort;     //选中的排序的值


}
