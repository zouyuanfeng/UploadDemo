package com.itzyf.bean;

/**
 * @author 依风听雨
 * @version 创建时间：2017/10/18 11:16
 */
public class Result<T> {
    private String msg;
    private T result;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
