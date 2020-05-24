package com.hnust.movie.mapper;


import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.po.MovieInfoExample;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieInfoMapper {

    @Select("SELECT * FROM m_movie_info")
    List<MovieInfo> queryAll();

    @Select("SELECT mid,movie_name,img_urls,categories,directors, main_actors,rating_num,summery\n" +
            "FROM m_movie_info WHERE off_shelf=0 AND categories LIKE '%${category}%' AND categories NOT LIKE '%动画%' ORDER BY hot_degree LIMIT 0, #{size};")
    List<MovieInfoInCache> queryMovieByCategory(@Param("category")String category, @Param("size") int size);

    @Select("SELECT mid,movie_name,img_urls,categories,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE off_shelf=0 AND categories LIKE '%${category}%' AND categories LIKE '%动画%'\n" +
            "ORDER BY hot_degree LIMIT 0, #{size};")
    List<MovieInfoInCache> queryComicByCategory(@Param("category") String category, @Param("size")int size);

    @Select("SELECT mid,movie_name,img_urls,categories,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories LIKE '%动画%' AND location LIKE '%${area}%' ORDER BY hot_degree DESC LIMIT 0, #{size}")
    List<MovieInfoInCache> queryComicByArea(@Param("area") String area,@Param("size") int size);

    //获取欧美动漫
    @Select("SELECT mid,movie_name,img_urls,categories,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories LIKE '%动画%' AND location LIKE '%美国%' " +
            "OR location LIKE '%法国%' ORDER BY hot_degree DESC LIMIT 0, #{size}")
    List<MovieInfoInCache> queryComicInEA(int size);

    @Select("SELECT mid,movie_name,img_urls,categories,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories NOT LIKE '%动画%' ORDER BY release_date DESC LIMIT 0, #{size};")
    List<MovieInfoInCache> queryLatestMovies(int size);

    @Select("SELECT mid,movie_name,img_urls,categories,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories LIKE '%动画%' ORDER BY release_date DESC LIMIT 0, #{size};")
    List<MovieInfoInCache> queryLatestComics(int size);

    int countByExample(MovieInfoExample example);

    int deleteByExample(MovieInfoExample example);

    int deleteByPrimaryKey(Long mid);

    int insert(MovieInfo record);

    int insertSelective(MovieInfo record);

    List<MovieInfo> selectByExample(MovieInfoExample example);

    MovieInfo selectByPrimaryKey(Long mid);

    int updateByExampleSelective(@Param("record") MovieInfo record, @Param("example") MovieInfoExample example);

    int updateByExample(@Param("record") MovieInfo record, @Param("example") MovieInfoExample example);

    int updateByPrimaryKeySelective(MovieInfo record);

    int updateByPrimaryKey(MovieInfo record);
}