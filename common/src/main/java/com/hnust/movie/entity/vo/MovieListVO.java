package com.hnust.movie.entity.vo;

import com.hnust.movie.entity.po.MovieInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 点击导航栏的电影标签后，该bean即为本页的包装结果
 * 包含一系列电影数据：获取最新电影(latest)、动作片(action)、喜剧片(comedy)、爱情(love)、
 * 科幻(science)、恐怖(terror)、剧情(feature )、战争片(war)
 * @Author: ggh
 * @Date: 2020/5/22 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListVO implements Serializable {

    private List<MovieInfoInCache> latestMovies;
    private List<MovieInfoInCache> actionMovies;
    private List<MovieInfoInCache> comedyMovies;
    private List<MovieInfoInCache> loveMovies;
    private List<MovieInfoInCache> scienceMovies;
    private List<MovieInfoInCache> terrorMovies;
    private List<MovieInfoInCache> featureMovies;
    private List<MovieInfoInCache> warMovies;

}
