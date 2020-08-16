package com.hnust.movie.common;

import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.CommentVO;
import com.hnust.movie.entity.vo.CommentVO2;
import com.hnust.movie.entity.vo.MovieInfoInCache;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/16 18:06
 */
public class CommonUtil {

    /**
     *@title:
     *@description: 根据电影id获取详情信息和评论信息，并设置进modelMap
     *@author:ggh
     *@updateTime: 2020/5/16 18:04
     **/
    public static boolean getMovieInfoAndComments(DatabaseService databaseService, CacheService cacheService, Long movieId, int startPage, ModelMap modelMap) {

        //检查传入的电影id是否合法
        if ("".equals(movieId) || movieId == null) {
            return false;
        } else {
            //获取电影的详情数据
//            ResultEntity<MovieInfo> resultEntity = databaseService.getDetailInfo(movieId);
//            ResultEntity<MovieInfo> movieInfoFromCache = cacheService.getMovieInfoFromCache(movieId);

            ResultEntity<MovieInfoInCache> resultEntity = databaseService.getDetailInfo(movieId);
            ResultEntity<MovieInfoInCache> movieInfoFromCache = cacheService.getMovieInfoFromCache(movieId);

            MovieInfoInCache movieInfo = resultEntity.getData();
            //将 "导演" 属性中的"|"换成空格
            movieInfo.setDirectors(movieInfo.getDirectors().replace("\\|", " "));

            //将 "主演" 属性中的"|"换成空格
            movieInfo.setMainActors(movieInfo.getMainActors().replace("\\|", " "));
            resultEntity.setData(movieInfo);

            //获取该电影的评论信息以及回复
            ResultEntity<List<CommentVO>> commentByMid = null;
            //如果没有插入起始页

            commentByMid = databaseService.getCommentByMid(movieId, startPage);
            int totalPage = 0, count = 0;
            if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 != 0) {
                totalPage = commentByMid.getData().get(0).getCount() / 10;
                totalPage++; //评论总页数
                count = commentByMid.getData().get(0).getCount(); //评论总条数
            }else if (commentByMid.getData().size() > 0 && commentByMid.getData().get(0).getCount() % 10 == 0){
                totalPage = commentByMid.getData().get(0).getCount() / 10;
                count = commentByMid.getData().get(0).getCount(); //评论总条数
            }

            //封装评论返回结果
            CommentVO2 commentVO2 = new CommentVO2();
            commentVO2.setCount(count);
            commentVO2.setCurrentPage(startPage);
            commentVO2.setNextPage((startPage+1)>=totalPage?totalPage:(startPage+1));
            commentVO2.setPrePage((startPage-1)<=1?1:(startPage-1));
            commentVO2.setTotalPage(totalPage);
            commentVO2.setCommentVOList(commentByMid.getData());


            modelMap.addAttribute("commentResult", ResultEntity.successWithData(commentVO2));

//            modelMap.addAttribute("movieInfo", resultEntity);
            modelMap.addAttribute("movieInfo", movieInfoFromCache);
            /*
            modelMap.addAttribute("commentList", commentByMid);
            modelMap.addAttribute("totalPage", totalPage);
            modelMap.addAttribute("currentPage", startPage);
            modelMap.addAttribute("count", count);*/

            return true;
        }
    }




}
