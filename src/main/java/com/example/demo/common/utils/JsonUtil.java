package com.example.demo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 自定义响应结构, 转换类
 */
public class JsonUtil {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 补齐没有的字段
     *
     * @param arry
     */
    public static JSONArray fillCol(JSONArray arry) {
        JSONObject keyNames = new JSONObject();
        JSONArray rsAry = (JSONArray) arry.clone();
        rsAry.forEach(obj -> {
            JSONObject json = (JSONObject) obj;
            json.keySet().forEach(colName -> keyNames.put(colName, colName));
        });

        for (int i = 0; i < rsAry.size(); i++) {
            JSONObject json = rsAry.getJSONObject(i);
            for (Object keyName : keyNames.values()) {
                if (!json.containsKey(keyName)) {
                    json.put(keyName.toString(), "");
                }
            }
        }
        return rsAry;
    }

    public static int countEmptyValue(JSONObject json) {
        AtomicInteger sum = new AtomicInteger(0);
        json.forEach((o, v) -> {
            if (v == null || StringUtils.isBlank(v.toString())) {
                sum.incrementAndGet();
            }
        });
        return sum.intValue();
    }

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param clazz    对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
