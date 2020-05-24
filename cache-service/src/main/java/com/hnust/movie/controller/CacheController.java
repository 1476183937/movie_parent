package com.hnust.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.entity.po.MovieInfo;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @Title:操作缓存的conroller
 * @Author: ggh
 * @Date: 2020/5/17 20:47
 */
@Controller
public class CacheController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CacheService cacheService;

    /**
    *@title:
    *@description: 根据电影id从缓存中获取数据
    *@author:ggh
    *@updateTime: 2020/5/17 21:21
    **/
    @RequestMapping("/movieInfo/get/{movieId}")
    @ResponseBody
    public ResultEntity getMovieInfoFromCache(@PathVariable("movieId") Long movieId){


        //1、获取jedis连接
        Jedis jedis = redisUtil.getJedis();

        //数据在缓存中的存储格式(string结构):key(电影id)->value(电影详情数据)
        //电影详情数据存储的为json数据

        //2、先从缓存中获取相应数据，
        String movieInfos = jedis.get(movieId + ":info");

        if (StringUtils.isNotBlank(movieInfos)){ //如果不为空
            //2.1、获取到了数据，就直接返回结果
            //解析
            MovieInfo movieInfo = JSONObject.parseObject(movieInfos, MovieInfo.class);
            //更新过期时间
            jedis.expire(movieId+":info", 10*60);

            return ResultEntity.successWithData(movieInfo);

        }else if ("".equals(movieInfos)){ //获取到了，但是内容是空的，也就是数据库也没有该条数据，照样返回
            return ResultEntity.successNoData();

        }else {
            //2.2、如果没有获取到，就从数据库获取

            String token = UUID.randomUUID().toString();

            //2.2.1、先获取分布式锁
            //生成锁的key
            String lockKey = "info:" + movieId + "lock";

            //EX表示秒，PX表示毫秒
            String lock = jedis.set(lockKey, token, "NX", "PX", 100 * 1000);
            //2.2.1.1、如果获取到锁，就从数据库读取数据
            if ("OK".equals(lock)){
                //2.2.1.2、从数据库获取到了相应的数据，存到缓存里面，设置过期时间，并返回结果

                ResultEntity<MovieInfo> detailInfo = databaseService.getDetailInfo(movieId);
                //如果数据库也没有该条数据，就在缓存里存个空值，防止缓存穿透
                if (detailInfo == null){
                    jedis.setex(movieId+":info",5*60,"");
                    //释放锁
                    //lua脚本
                    String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                    //在获取值的时候获取到了就直接删除
                    jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(token));

                    return ResultEntity.successNoData();
                }

                //存到缓存、设置过期时间
                String jsonString = JSONObject.toJSONString(detailInfo.getData());
                jedis.setex(movieId+":info", 10*60, jsonString);


                //同时释放锁

                //方案一：
                /*if (token.equals(jedis.get(lockKey))){
                    //通过比较分布式锁的key的值和之前自己得到的token是否一致，一致就是自己设置的锁，可以删除
                    //不一致就不是自己设置的锁，不能删除
                    jedis.decr(lockKey);
                }*/

                //方案二：使用lua脚本
                //lua脚本
                String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                //在获取值的时候获取到了就直接删除
                jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(token));

                //返回结果
                return ResultEntity.successWithData(detailInfo.getData());

            }else {
                //2.2.2、如果没有获取到锁，就等下一次
                //释放连接
                jedis.close();

                //重新调用自己
                return getMovieInfoFromCache(movieId);
            }
        }


//        return getMovieInfoFromCache(movieId);
    }

    /**
    *@title:
    *@description: 为顶部导航栏中的动漫标签页获取显示的数据
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/22 15:50
    **/
    @RequestMapping("/cache/comic/list")
    @ResponseBody
    public ResultEntity getMultipleComicList(){

        ResultEntity comicListResult = cacheService.getMultipleComicList();

        if (comicListResult.getData() == null){
            return ResultEntity.failed("get failed");
        }

        return comicListResult;
    }


    /**
    *@title:
    *@description: 为顶部导航栏中的电影标签页获取显示的数据
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/22 15:52
    **/
    @RequestMapping("/cache/movie/list")
    @ResponseBody
    public ResultEntity getMultipleMoviesList(){

        ResultEntity movieListResult = cacheService.getMultipleMoviesList();

        if (movieListResult.getData() == null){
            return ResultEntity.failed("get failed");
        }

        return movieListResult;
    }

}
