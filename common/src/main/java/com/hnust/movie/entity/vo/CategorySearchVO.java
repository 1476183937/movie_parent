package com.hnust.movie.entity.vo;

import com.hnust.movie.entity.po.Category;
import com.hnust.movie.entity.po.Location;
import com.hnust.movie.entity.po.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:表示进入类别搜索页面需要的分类信息
 * @Author: ggh
 * @Date: 2020/5/20 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySearchVO implements Serializable {

    List<Category> categoryList;//所有类别的集合
    List<Location> locationList;//所有地区的集合
    List<Year> yearList;        //所有年份的结合

}
