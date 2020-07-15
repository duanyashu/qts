package com.qts.response;

import java.util.List;

public class LayuiData {

	/**
	 * 结果状态码
	 */
	private int code =0;
	
	/**
	 * 数据为空 提示信息
	 */
	private String msg = "";
	
	/**
	 * 总条数
	 */
	private int count;
	
	/**
	 * 数据
	 */
	private List<?> data;
	
	
	public  LayuiData(List<?> data, int count) {
		this.data = data;
		this.count = count;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	
	
	
}
