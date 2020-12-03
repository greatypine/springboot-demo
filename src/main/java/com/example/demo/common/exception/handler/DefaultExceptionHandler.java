package com.example.demo.common.exception.handler;


import com.example.demo.common.base.ResponseInfo;
import com.example.demo.common.exception.LoginException;
import com.example.demo.common.exception.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import static com.example.demo.common.base.ResponseInfo.ResultEnum.PARAM_ERROR;

/**
 * 异常处理器
 *
 * @author junyong
 */
@RestControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * Validated 验证错误
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseInfo validatedException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseInfo.error(PARAM_ERROR, e.getMessage());
    }

    /**
     * 登录异常
     */
    @ExceptionHandler(LoginException.class)
    public ResponseInfo authLoginException(LoginException e) {
        log.error(e.getMessage(), e);
        if (e.getResultEnum() != null) {
            return ResponseInfo.error(e.getResultEnum());
        }
        return ResponseInfo.error(ResponseInfo.ResultEnum.LOGIN_ERROR);
    }

    /**
     * 参数类型不匹配异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseInfo authLoginException(MethodArgumentTypeMismatchException e) {
        return ResponseInfo.paramError(PARAM_ERROR.getMsg());
    }

    /**
     * 请求中发生错误
     *
     * @param e
     */
    @ExceptionHandler(RequestException.class)
    public ResponseInfo requestException(RequestException e) {
        ResponseInfo error = ResponseInfo.error(e.getMessage());
        error.setCode(e.getResultEnum().getCode());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void validException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        throw e;
    }

    /**
     * 异常统一捕获
     */
    @ExceptionHandler(Exception.class)
    public ResponseInfo authException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return ResponseInfo.systemError();
    }

    /**
     * 异常统一捕获
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseInfo unexpectedTypeException(UnexpectedTypeException e) {
        log.info(e.getMessage(), e.toString());
        return ResponseInfo.error(PARAM_ERROR, e.getMessage());
    }


}
