package com.hnust.movie.entity.vo;

import com.hnust.movie.entity.po.MovieInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:点击导航栏的动漫后，进入动漫列表页，该bean为一系列动漫列表数据
 *包含最新动漫，国产动漫、日本动漫、欧美动漫
 * @Author: ggh
 * @Date: 2020/5/22 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComicListVO implements Serializable {

    private List<MovieInfoInCache> latestComics;
    private List<MovieInfoInCache> chineseComics;
    private List<MovieInfoInCache> japaneseComics;
    private List<MovieInfoInCache> europeanAndAmericanComics;

}
