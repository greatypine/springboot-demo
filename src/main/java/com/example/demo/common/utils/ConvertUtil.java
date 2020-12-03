package com.example.demo.common.utils;


import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


public class ConvertUtil {

    /**
     * 转换匹配到的属性,参看单元测试
     *
     * @param beanSrc
     * @param descCls
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> R convertOnlyMatch(T beanSrc, Class<R> descCls) {
        if (beanSrc == null) {
            return null;
        }
        try {
            R destInstance = descCls.newInstance();
            BeanUtils.copyProperties(beanSrc, destInstance);
            return destInstance;
        } catch (Exception e) {
            LoggerFactory.getLogger(ConvertUtil.class).error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * convertOnlyMatch的list的版本
     *
     * @param beanSrc
     * @param descCls
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> List<R> convertListOnlyMatch(List<T> beanSrc, Class<R> descCls) {
        List<R> rList = new ArrayList<>();
        beanSrc.forEach(t -> {
            R r = convertOnlyMatch(t, descCls);
            rList.add(r);
        });
        return rList;
    }

}
