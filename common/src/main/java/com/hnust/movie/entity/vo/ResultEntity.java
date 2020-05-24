package com.hnust.movie.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@title:
*@description: 统一返回结果
*@author:ggh
*@updateTime: 2020/5/13 11:37
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> implements Serializable {
	
	private String result;
	private String message;
	private T data;
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String NO_MSG = "NO_MSG";
	public static final String NO_DATA = "NO_DATA";
	
	public static ResultEntity<String> successNoData() {
		return new ResultEntity<>(SUCCESS, NO_MSG, NO_DATA);
	}
	
	public static <T> ResultEntity<T> successWithData(T data) {
		return new ResultEntity<>(SUCCESS, NO_MSG, data);
	}
	
	public static <T> ResultEntity<T> failed(String message) {
		return new ResultEntity<>(FAILED, message, null);
	}

}