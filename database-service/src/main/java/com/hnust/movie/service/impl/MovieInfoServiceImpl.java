package com.hnust.movie.service.impl;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.po.MovieInfoExample;
import com.hnust.movie.entity.vo.ComicListVO;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import com.hnust.movie.entity.vo.MovieListVO;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.mapper.MovieInfoMapper;
import com.hnust.movie.service.MovieInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/13 11:50
 */
@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Resource
    private MovieInfoMapper movieInfoMapper;

    /**
    *@title:
    *@description: 为首页获取数据
    *@param: is_comic
    *@author:ggh
    *@updateTime: 2020/5/22 12:22
    **/
    @Override
    public ResultEntity<List<MovieInfo>> getMovieInfoForIndexPage(int is_comic) {

        ResultEntity<List<MovieInfo>> resultEntity = new ResultEntity<>();

        //获取最新热播动漫
        if (is_comic == 0){

            MovieInfoExample movieInfoExample = new MovieInfoExample();
            MovieInfoExample.Criteria criteria = movieInfoExample.createCriteria();

            //类别字段中包含“动画”的
            criteria.andCategoriesLike("%动画%");
            List<MovieInfo> movieInfos = movieInfoMapper.selectByExample(movieInfoExample);
            List<MovieInfo> movieInfoList = movieInfos.subList(0, 33);

            resultEntity.setData(movieInfoList);

        }else{ //获取最新热播电影

            MovieInfoExample movieInfoExample = new MovieInfoExample();
            MovieInfoExample.Criteria criteria = movieInfoExample.createCriteria();

            //类别中不包含“动画”的
            criteria.andCategoriesNotLike("%动画%");
            //取33条结果
            List<MovieInfo> movieInfoList = movieInfoMapper.selectByExample(movieInfoExample).subList(0, 33);

            resultEntity.setData(movieInfoList);

        }

        return resultEntity;
    }

    /**
    *@title:
    *@description: 根据id获取详情信息
    *@author:ggh
    *@updateTime: 2020/5/14 10:19
    **/
    @Override
    public ResultEntity<MovieInfo> getDetailByMovieId(Long movieId) {

        MovieInfoExample movieInfoExample = new MovieInfoExample();
        movieInfoExample.createCriteria().andMidEqualTo(movieId);

        List<MovieInfo> movieInfos = movieInfoMapper.selectByExample(movieInfoExample);

        if (movieInfos != null && movieInfos.size() > 0){

            ResultEntity<MovieInfo> resultEntity = new ResultEntity<>();
            String[] split = movieInfos.get(0).getMainActors().split("\\|");

            //只取前三个主演的名字
            if (split.length > 3){
                MovieInfo movieInfo = movieInfos.get(0);
                movieInfo.setMainActors(split[0] + "|" + split[1] + "|" + split[2]);

                resultEntity.setData(movieInfo);

            }else {
                resultEntity.setData(movieInfos.get(0));
            }

            return resultEntity;
        }

        return null;
    }

    /**
    *@title:
    *@description: 获取所有电影信息
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/18 21:55
    **/
    @Override
    public List<MovieInfo> getAll() {

        List<MovieInfo> movieInfoList = movieInfoMapper.queryAll();
        return movieInfoList;
    }

    /**
    *@title:
    *@description: 根据类别获取电影数据
    *@author:ggh
    *@updateTime: 2020/5/22 12:02
    **/
    @Override
    public ResultEntity<MovieListVO> getMovieInfoByCategory() {

        //获取
//         * 包含一系列电影数据：获取最新电影(latest)、动作片(action)、喜剧片(comedy)、爱情(love)、
// * 科幻(science)、恐怖(terror)、剧情(feature )、战争片(war)

        List<MovieInfoInCache> latestMovies = movieInfoMapper.queryLatestMovies(33);
        List<MovieInfoInCache> actionMovies = movieInfoMapper.queryMovieByCategory("动作", 33);
        List<MovieInfoInCache> comedyMovies = movieInfoMapper.queryMovieByCategory("喜剧", 33);
        List<MovieInfoInCache> loveMovies = movieInfoMapper.queryMovieByCategory("爱情", 33);
        List<MovieInfoInCache> scienceMovies = movieInfoMapper.queryMovieByCategory("科幻", 33);
        List<MovieInfoInCache> terrorMovies = movieInfoMapper.queryMovieByCategory("恐怖", 33);
        List<MovieInfoInCache> featureMovies = movieInfoMapper.queryMovieByCategory("剧情", 33);
        List<MovieInfoInCache> warMovies = movieInfoMapper.queryMovieByCategory("战争", 33);

        MovieListVO movieListVO = new MovieListVO();
        movieListVO.setLatestMovies(latestMovies);
        movieListVO.setActionMovies(actionMovies);
        movieListVO.setComedyMovies(comedyMovies);
        movieListVO.setFeatureMovies(featureMovies);
        movieListVO.setLoveMovies(loveMovies);
        movieListVO.setScienceMovies(scienceMovies);
        movieListVO.setTerrorMovies(terrorMovies);
        movieListVO.setWarMovies(warMovies);

        return ResultEntity.successWithData(movieListVO);
    }

    /**
     *@title:
     *@description: 根据类别获取动漫数据
     *@author:ggh
     *@updateTime: 2020/5/22 12:02
     **/
    @Override
    public ResultEntity<ComicListVO> getComicByCategpry() {

        //最新动漫(latestComics)，国产动漫(chineseComics)、日本动漫(japaneseComics)、欧美动漫(europeanAndAmericanComics)
        List<MovieInfoInCache> latestComics = movieInfoMapper.queryLatestComics(33);
        List<MovieInfoInCache> chineseComics = movieInfoMapper.queryComicByArea("大陆", 33);
        List<MovieInfoInCache> japaneseComics = movieInfoMapper.queryComicByArea("日本", 33);
        List<MovieInfoInCache> europeanAndAmericanComics = movieInfoMapper.queryComicInEA(33);

        ComicListVO comicListVO = new ComicListVO();
        comicListVO.setChineseComics(chineseComics);
        comicListVO.setJapaneseComics(japaneseComics);
        comicListVO.setLatestComics(latestComics);
        comicListVO.setEuropeanAndAmericanComics(europeanAndAmericanComics);

        return ResultEntity.successWithData(comicListVO);
    }



}
