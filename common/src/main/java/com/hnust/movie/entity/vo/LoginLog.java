package com.hnust.movie.entity.vo;

/**
 * @Title:用户登录日志
 * @Author: ggh
 * @Date: 2020/7/29 12:10
 */
public class LoginLog {

    private String userName;
    private String ip;
    private String result;
    private long timeStamp;

    public LoginLog(String userName, String ip, String result, long timeStamp) {
        this.userName = userName;
        this.ip = ip;
        this.result = result;
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
