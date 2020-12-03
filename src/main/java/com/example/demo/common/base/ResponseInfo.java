package com.example.demo.common.base;

public class ResponseInfo<T> {
    /**
     * 返回数据
     */
    private T data;

    private Integer code;
    private String message;

    public ResponseInfo(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }


    public ResponseInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseInfo(Integer code, T data) {
        this.data = data;
        this.code = code;
    }

    public ResponseInfo(Integer code) {
        this.code = code;
    }

    public ResponseInfo() {
    }

    public static ResponseInfo systemError() {
        return new ResponseInfo(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg());
    }

    public static ResponseInfo paramError(String msg) {
        return new ResponseInfo(ResultEnum.PARAM_ERROR.getCode(), msg);
    }

    public static ResponseInfo error(String msg) {
        return new ResponseInfo(ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> ResponseInfo<T> error(ResultEnum resultEnum) {
        return new ResponseInfo<T>(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static ResponseInfo error(ResultEnum resultEnum, String msg) {
        return new ResponseInfo(resultEnum.getCode(), msg);
    }

    public static ResponseInfo success() {
        return new ResponseInfo(ResultEnum.SUCCESS.getCode());
    }

    public static <T> ResponseInfo<T> success(T data) {
        return new ResponseInfo<>(ResultEnum.SUCCESS.getCode(), data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum ResultEnum {
        ERROR(500, "请求异常"),
        SYSTEM_ERROR(510, "系统错误"),
        ROLE_ERROR(511, "权限错误"),
        CODE_ERROR(512, "code错误"),
        NOT_REPEAT_SUBMIT(513, "请勿重复提交"),
        PARAM_ERROR(520, "参数错误"),
        CHECK_CODE_ERROR(521, "验证码错误"),
        LOGIN_ERROR(530, "请先登陆"),
        LOGIN_NAME_ERROR(531, "账号或密码错误"),
        ACCOUNT_IS_ACTIVE_ERROR(532, "账号已禁用"),




        SUCCESS(200, "OK");

        private Integer code;
        private String msg;

        ResultEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

/*    public boolean isSuccess() {
        return 200 == this.getCode();
    }*/
}
