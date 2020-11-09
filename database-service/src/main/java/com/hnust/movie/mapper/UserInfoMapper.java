package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.UserInfo;
import com.hnust.movie.entity.po.UserInfoExample;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    //用户登录
    @Select("SELECT * FROM m_userinfo WHERE username=#{username} AND password = #{password}")
    List<UserInfo> login( UserInfo userInfo);

    //用户注册
    @Insert("INSERT INTO m_userinfo(`username`, `password`,`emaile`,`register_time`) VALUES(#{username},#{password},#{emaile},#{registerTime});")
    int registyUser(UserInfo userInfo);

    //查询用户名是否存在
    @Select("SELECT * FROM m_userinfo WHERE username=#{userName};")
    UserInfo existUserName(String userName);

}