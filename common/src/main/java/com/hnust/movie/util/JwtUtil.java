package com.hnust.movie.util;

import io.jsonwebtoken.*;

import java.util.Map;

public class JwtUtil {

    /**
    *@title:
    *@description: jwt编码，
    *@param: key：key
    *@param: param：用户的信息参数
    *@param: salt：盐值
    *@author:ggh
    *@updateTime: 2020/5/26 9:51
    **/
    public static String encode(String key, Map<String,Object> param, String salt){
        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);

        jwtBuilder = jwtBuilder.setClaims(param);

        String token = jwtBuilder.compact();
        return token;

    }


    /**
    *@title:
    *@description: 解码
    *@param: token:传过来的token
    *@param: key:key
    *@param: salt：盐值
    *@author:ggh
    *@updateTime: 2020/5/26 9:52
    **/
    public  static Map<String,Object>  decode(String token ,String key,String salt){
        Claims claims=null;
        if (salt!=null){
            key+=salt;
        }
        try {
            claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch ( JwtException e) {
           return null;
        }
        return  claims;
    }
}