package com.hnust.movie.util;

import com.hnust.movie.entity.vo.LoginLog;
import com.hnust.movie.entity.vo.UserActionLog;

/**
 * @Title:
 * @Author: ggh
 * @Date: 2020/7/26 11:32
 */
public class LogUtils {

    public static void ErrorLog(org.slf4j.Logger errorLog,Exception e,String url,String data){

        errorLog.error("Exception: {}",e);
        errorLog.error("URL : {}, data: {}",url,data);

    }

    public static void UserActionLog(org.slf4j.Logger userActionLog, UserActionLog actionLog){

        long time = System.currentTimeMillis();
        StringBuffer log = new StringBuffer();

        String sessionId = actionLog.getSessionId();

        String userId = actionLog.getUserId();
        String pageId = actionLog.getPageId();
        String searchKeyWord = actionLog.getSearchKeyWord();
        String clickCategory = actionLog.getClickCategory();
        String collectCategory = actionLog.getCollectCategory();
        String ratingCategory = actionLog.getRatingCategory();
        String commentCategory = actionLog.getCommentCategory();
        String playCategory = actionLog.getPlayCategory();
        String cilckMovieId = actionLog.getCilckMovieId();
        String collectMovieId = actionLog.getCollectMovieId();
        String ratingMovieId = actionLog.getRatingMovieId();
        String commentMovieId = actionLog.getCommentMovieId();
        String playMovieId = actionLog.getPlayMovieId();

        log.append(time).append("|")
                .append(userId).append("|")
                .append(sessionId).append("|")
                .append(pageId).append("|")
                .append(searchKeyWord).append("|")
                .append(clickCategory).append("|")
                .append(collectCategory).append("|")
                .append(ratingCategory).append("|")
                .append(commentCategory).append("|")
                .append(playCategory).append("|")
                .append(cilckMovieId).append("|")
                .append(collectMovieId).append("|")
                .append(ratingMovieId).append("|")
                .append(commentMovieId).append("|")
                .append(playMovieId).append("|");
        userActionLog.info(log.toString());

    }

    public static void loginLog(org.slf4j.Logger loginLogger, LoginLog loginLog){

        loginLogger.info("{}|{}|{}|{}",loginLog.getUserName(),loginLog.getIp(),loginLog.getResult(),loginLog.getTimeStamp());

    }


}
