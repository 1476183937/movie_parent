package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.Location;
import com.hnust.movie.entity.po.LocationExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LocationMapper {

    //获取所有地区信息
    @Select("SELECT * FROM m_location")
    List<Location> queryAll();

    //========================以下为逆向工程自动生成===============================
    int countByExample(LocationExample example);

    int deleteByExample(LocationExample example);

    int deleteByPrimaryKey(Integer lid);

    int insert(Location record);

    int insertSelective(Location record);

    List<Location> selectByExample(LocationExample example);

    Location selectByPrimaryKey(Integer lid);

    int updateByExampleSelective(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByExample(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);
}