package com.qts.response;

/**
 * @description: 通用接口封装类
 * @author: duanyashu
 * @time: 2020-07-02 15:42
 */
public class ReslutData {

    public final  static  int  SUCCESS_CODE= 200;

    public final  static  int FAIL_CODE=300;

    public final static  int ERROR_CODE=400;

    private  int code;

    private String msg;

    private Object data;

    public static ReslutData success(){
        return  new ReslutData(SUCCESS_CODE,null,null);
    }

    public static  ReslutData success(Object data){
        return new ReslutData(SUCCESS_CODE,null,data);
    }

    public static ReslutData fail(String msg){
        return  new ReslutData(FAIL_CODE,msg,null);
    }

    public static ReslutData fail(int code,String msg){
        return  new ReslutData(code,msg,null);
    }

    public ReslutData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
