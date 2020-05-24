package com.hnust.movie.entity.vo;

import com.hnust.movie.entity.po.MovieInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:在es查询后封装的结果
 * @Author: ggh
 * @Date: 2020/5/19 11:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultVO implements Serializable {

    private String kw;                      //关键词
    private long total;                     //总结果数
    private List<MovieInfo> movieInfoList;  //电影集合列表
    private int currentPage;                //当前页的页数
    private int prePage;                    //上一页的页数
    private int nextPage;                   //下一页的页数
    private int totaltPage;                 //总页数
    private int startPage;                  //显示的起始页数
    private int endPage;                  //显示终止页数

}
