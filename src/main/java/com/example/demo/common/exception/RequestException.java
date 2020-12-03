package com.example.demo.common.exception;

import com.example.demo.common.base.ResponseInfo;

/**
 * 请求中发生错误
 */
public class RequestException extends RuntimeException {

    private ResponseInfo.ResultEnum resultEnum;
    private String msg;

    public RequestException(ResponseInfo.ResultEnum resultEnum) {
        this(resultEnum, resultEnum.getMsg());
    }

    public RequestException(ResponseInfo.ResultEnum resultEnum, String msg) {
        super(msg);
        this.resultEnum = resultEnum;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseInfo.ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResponseInfo.ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
