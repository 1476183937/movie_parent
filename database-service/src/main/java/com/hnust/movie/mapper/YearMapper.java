package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.Year;
import com.hnust.movie.entity.po.YearExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface YearMapper {

    //获取所有年份信息
    @Select("SELECT * FROM m_year")
    List<Year> queryAll();

    //========================以下为逆向工程自动生成===============================
    int countByExample(YearExample example);

    int deleteByExample(YearExample example);

    int deleteByPrimaryKey(Integer yid);

    int insert(Year record);

    int insertSelective(Year record);

    List<Year> selectByExample(YearExample example);

    Year selectByPrimaryKey(Integer yid);

    int updateByExampleSelective(@Param("record") Year record, @Param("example") YearExample example);

    int updateByExample(@Param("record") Year record, @Param("example") YearExample example);

    int updateByPrimaryKeySelective(Year record);

    int updateByPrimaryKey(Year record);
}