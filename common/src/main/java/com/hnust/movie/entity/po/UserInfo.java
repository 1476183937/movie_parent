package com.hnust.movie.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 用户信息表
*@author:ggh
*@updateTime: 2020/5/13 11:22
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    private Long uid;            //用户id
    private String username;     //用户名
    private String password;     //密码
    private String nickName;     //昵称
    private String registerTime; //注册时间
    private String birthday;     //生日
    private String phone;        //电话
    private String emaile;       //邮箱
    private String loginTime;    //上次登录时间
    private String gender;       //性别
    private String noLoginTime;  //禁止登录时间
    private String face;         //头像图片url


}