package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.entity.po.UserInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    //用户登录
    @Select("SELECT * FROM m_userinfo WHERE username=${username} AND `password` = #{password}")
    List<UserInfo> login(UserInfo userInfo);

}