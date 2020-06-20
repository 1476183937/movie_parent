package com.hnust.movie.mapper;

import com.hnust.movie.entity.po.ScanHistory;
import com.hnust.movie.entity.po.ScanHistoryExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ScanHistoryMapper {

    //根据用户id查询该用户的观看历史
    @Select("SELECT `mid`, `movie_name`, `image`, `date` " +
            "FROM m_scan_history WHERE uid = #{uid} AND deleted=0 " +
            "ORDER BY date DESC ;")
    List<ScanHistory> queryByUid(@Param("uid") Long uid);

    //添加观看历史记录
    @Insert("INSERT m_scan_history (`scan_id`, `uid`, `mid`, `movie_name`, `image`," +
            " `date`, `deleted`) VALUES (#{scanId},#{uid}, #{mid}, #{movieName}, " +
            "#{image}, #{date}, 0) ON DUPLICATE KEY UPDATE date = #{date},deleted=0")
    int insertHistory(ScanHistory scanHistory);

    //删除相应的观看历史记录
    @Update("UPDATE m_scan_history SET deleted=1 WHERE scan_id=#{scanId};")
    int deleteHistoty(String scanId);


}