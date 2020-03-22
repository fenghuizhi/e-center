package com.lessly.center.api.response;

import java.io.Serializable;

/**
 * 响应模型
 */
public class CenterResponse<T> implements Serializable {

    private  Integer code;
    private String msg;

    private T Data;

    public CenterResponse() {
    }

    public CenterResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
