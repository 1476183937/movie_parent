package com.hnust.movie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hnust.movie.entity.vo.ComicListVO;
import com.hnust.movie.entity.vo.MovieListVO;
import com.hnust.movie.entity.vo.ResultEntity;
import com.hnust.movie.service.CacheService;
import com.hnust.movie.service.DatabaseService;
import com.hnust.movie.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/5/22 11:06
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DatabaseService databaseService;

    /**
    *@title:
    *@description: 获取hash结构的电影列表
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/22 11:23
    *
     * @return*/
    @Override
    public ResultEntity getMultipleMoviesList() {

        //先尝试从缓存中获取
        Jedis jedis = redisUtil.getJedis();

        try {
            MovieListVO movieListVO = null;

//        获取最新电影(latest)、动作片(action)、喜剧片(comedy)、爱情(love)、
//        科幻(science)、恐怖(terror)、剧情(feature )、战争片(war)
//        String latestMoviesJson = jedis.hget("movieList", "latest");


            //获取json字符串
            String moviesJson = jedis.get("movieList");

            movieListVO = JSONObject.parseObject(moviesJson, MovieListVO.class);

            if (movieListVO == null){

                //没有从缓存中获取到数据，接着从数据库获取相应的数据
                String token = UUID.randomUUID().toString();

                //尝试获取锁
                String lockKey = "movieList:lock";

                String lock = jedis.setex(lockKey, 20, token);
                if ("OK".equals(lock)){
                    //获取到了锁，接着从数据库里获取相应数据

                    ResultEntity<MovieListVO> moviesResult = databaseService.getMovieListByMultipleCategory();

                    if (moviesResult.getData() == null){
                        //数据库有没有获取到数据，就在缓存中存入一个空值
                        jedis.setex("movieList",60*60,"");
                        //释放锁，用lua脚本
                        String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                        //在获取值的时候获取到了就直接删除
                        jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(token));
                        //返回结果
                        return ResultEntity.successNoData();
                    }

                    //如果从数据库获取到了数据，放进缓存，设置过期时间，返回结果

                    String jsonString = JSONObject.toJSONString(moviesResult.getData());

                    jedis.setex("movieList",60*60,jsonString);

                    //释放锁，用lua脚本
                    String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                    //在获取值的时候获取到了就直接删除
                    jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(token));

                    return ResultEntity.successWithData(moviesResult.getData());

                }

            }else if ("".equals(movieListVO)){
                return ResultEntity.successNoData();
            }else{
                //从缓存中获取到了数据
                return ResultEntity.successWithData(movieListVO);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return ResultEntity.failed("get failed");
//        return null;
    }

    /**
    *@title:
    *@description: 获取hash结构的动漫列表
    *@param:
    *@author:ggh
    *@updateTime: 2020/5/22 11:23
    *
     * @return*/
    @Override
    public ResultEntity getMultipleComicList() {

        //先尝试从缓存中获取
        Jedis jedis = redisUtil.getJedis();

        try {
            ComicListVO comicListVO = null;

            //最新动漫、国产动漫、日本动漫、欧美动漫

            //获取json字符串
            String comicssJson = jedis.get("comicList");

            comicListVO = JSONObject.parseObject(comicssJson, ComicListVO.class);

            if (comicListVO == null){

                //没有从缓存中获取到数据，接着从数据库获取相应的数据
                String token = UUID.randomUUID().toString();

                //尝试获取锁
                String lockKey = "comicList:lock";

                String lock = jedis.setex(lockKey, 20, token);
                if ("OK".equals(lock)){
                    //获取到了锁，接着从数据库里获取相应数据

                    ResultEntity comicResult = databaseService.getComicListByMultipleCategory();

                    if (comicResult.getData() == null){
                        //数据库有没有获取到数据，就在缓存中存入一个空值
                        jedis.setex("comicList",60*60,"");
                        //释放锁，用lua脚本
                        String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                        //在获取值的时候获取到了就直接删除
                        jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(token));
                        //返回结果
                        return ResultEntity.successNoData();
                    }

                    //如果从数据库获取到了数据，放进缓存，设置过期时间，返回结果

                    String jsonString = JSONObject.toJSONString(comicResult.getData());

                    jedis.setex("comicList",60*60,jsonString);

                    //释放锁，用lua脚本
                    String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                    //在获取值的时候获取到了就直接删除
                    jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(token));

                    return ResultEntity.successWithData(comicResult.getData());

                }

            }else if ("".equals(comicListVO)){
                return ResultEntity.successNoData();
            }else{
                //从缓存中获取到了数据
                return ResultEntity.successWithData(comicListVO);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return ResultEntity.failed("get failed");

    }

    /**
    *@title:
    *@description: 获取指定key的值
    *@param: key
    *@author:ggh
    *@updateTime: 2020/5/26 13:54
    **/
    @Override
    public ResultEntity<String> getInfo(String key) {

        Jedis jedis = redisUtil.getJedis();
        try {
            if (jedis != null){

                String val = jedis.get(key);
                if (StringUtils.isNotBlank(val)){
                    return ResultEntity.successWithData(val);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return ResultEntity.failed("get fail");
    }
}
