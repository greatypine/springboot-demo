package com.example.demo.common.exception;


import com.example.demo.common.base.ResponseInfo;

/**
 * 登录异常
 */
public class LoginException extends RuntimeException {
    private ResponseInfo.ResultEnum resultEnum;


    public LoginException(ResponseInfo.ResultEnum result) {
        super(result.getMsg());
        this.resultEnum = result;
    }

    public ResponseInfo.ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResponseInfo.ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    public LoginException() {
        super();
    }
}
