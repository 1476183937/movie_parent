package com.hnust.movie.entity.vo;

import com.hnust.movie.entity.po.UserCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: 表示返回给用户收藏界面的bean类
 * @Author: ggh
 * @Date: 2020/5/31 9:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionVO extends UserCollection {

    private String categories; //类别

}
