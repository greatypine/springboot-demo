package com.example.demo.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {

    public static final String REQUEST = "request";
    public static final String RESPONSE = "response";

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

    public static String getParameter(String key) {
        return getRequest().getParameter(key);
    }

    public static String getHead(String key) {
        return getRequest().getHeader(key);
    }

    public static <T> T getHeadBean(String key, Class<T> type) {
        String str = getHead(key);
        return JSONObject.toJavaObject(JSONObject.parseObject(str), type);
    }

    public static <T> T getParameterBean(String key, Class<T> type) {
        String str = getParameter(key);
        return JSONObject.toJavaObject(JSONObject.parseObject(str), type);
    }

    public static JSONObject getHeadJson(String key) {
        String str = getHead(key);
        return JSONObject.parseObject(str);
    }

    public static JSONObject getParameterJson(String key) {
        String str = getParameter(key);
        return JSONObject.parseObject(str);
    }

}
