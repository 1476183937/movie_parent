package com.hnust.movie.mapper;


import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.po.MovieInfoExample;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieInfoMapper {


//    下面两个方法导入数据测试用
    @Select("SELECT mid,play_url FROM m_movie_info2 WHERE mid < 200000000")
    List<MovieInfo> getAll();

    @Insert("INSERT INTO m_movie_play_urls(`id`, `mid`, `play_urls`) VALUES (#{id}, #{mid}, #{playUrl});")
    void insertTest(@Param("id") Long id,@Param("mid")Long mid,@Param("playUrl")String playUrl);


    @Select("SELECT * FROM m_movie_info")
//    @Select("SELECT * FROM m_movie_info2")
    List<MovieInfo> queryAll();

    //获取评分较高的电影信息，可用于用户未登陆时或有的电影还未生产推荐列表，可以暂时作为获取推荐列表使用
    //暂时指定获取评分大于等于60.以上的
    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "(\n" +
            "SELECT mid,movie_name,img_urls,categories,location,directors,screen_writer,main_actors,rating_num,summery,play_url FROM m_movie_info WHERE rating_num>=6.0 ORDER BY rating_num LIMIT #{start},#{size}\n" +
            ")m1\n" +
            "JOIN m_movie_play_urls m2 ON m1.mid=m2.mid")
    List<MovieInfoInCache> getTopRatingMovies(@Param("start") int start,@Param("size") int size);

    //通过电影id查询
    @Select("SELECT m2.play_urls,m1.mid,m1.movie_name,m1.img_urls,m1.categories,m1.location,m1.directors,m1.screen_writer,m1.main_actors,m1.rating_num,m1.summery,m1.play_url FROM m_movie_play_urls m2 JOIN m_movie_info m1 ON m1.mid=m2.mid WHERE m2.mid=#{mid};")
    MovieInfoInCache queryMovieByMid(Long mid);

    //通过类别查询电影信息(不含动漫)
    @Select("SELECT mid,movie_name,img_urls,categories,run_time,directors, main_actors,rating_num,summery\n" +
            "FROM m_movie_info WHERE off_shelf=0 AND categories LIKE '%${category}%' AND categories NOT LIKE '%动画%' ORDER BY hot_degree LIMIT 0, #{size};")
    List<MovieInfoInCache> queryMovieByCategory(@Param("category")String category, @Param("size") int size);

    //通过类别查询动漫信息
    @Select("SELECT mid,movie_name,img_urls,categories,run_time,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE off_shelf=0 AND categories LIKE '%${category}%' AND categories LIKE '%动画%'\n" +
            "ORDER BY hot_degree LIMIT 0, #{size};")
    List<MovieInfoInCache> queryComicByCategory(@Param("category") String category, @Param("size")int size);

    //通过区域查询电影信息
    @Select("SELECT mid,movie_name,img_urls,categories,run_time,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories LIKE '%动画%' AND location LIKE '%${area}%' ORDER BY hot_degree DESC LIMIT 0, #{size}")
    List<MovieInfoInCache> queryComicByArea(@Param("area") String area,@Param("size") int size);

    //获取欧美动漫
    @Select("SELECT mid,movie_name,img_urls,categories,run_time,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories LIKE '%动画%' AND location LIKE '%美国%' " +
            "OR location LIKE '%法国%' ORDER BY hot_degree DESC LIMIT 0, #{size}")
    List<MovieInfoInCache> queryComicInEA(int size);

    //获取最新的电影数据
    @Select("SELECT mid,movie_name,img_urls,categories,run_time,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories NOT LIKE '%动画%' ORDER BY release_date DESC LIMIT 0, #{size};")
    List<MovieInfoInCache> queryLatestMovies(int size);

    //获取最新的动漫数据
    @Select("SELECT mid,movie_name,img_urls,categories,run_time,directors, main_actors,rating_num,summery \n" +
            "FROM m_movie_info WHERE categories LIKE '%动画%' ORDER BY release_date DESC LIMIT 0, #{size};")
    List<MovieInfoInCache> queryLatestComics(int size);

    //获取最新发布的电影数据(包含电影和动漫)
    @Select("SELECT mid,movie_name,img_urls,rating_num FROM m_movie_info ORDER BY release_date DESC LIMIT 0,#{size};")
    List<MovieInfoInCache> queryLatestAllMovies(int size);

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