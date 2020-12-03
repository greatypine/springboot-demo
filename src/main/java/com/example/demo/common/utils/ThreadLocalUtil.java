package com.example.demo.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程变量保持者
 */
public class ThreadLocalUtil {

    public static final String USER_KEY = "salary_user_key";

    private static ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, Object value) {
        threadLocal.get().put(key, value);
    }

    public static Object get(String key) {
        return threadLocal.get().get(key);
    }

    public static String getString(String key) {
        return (String) threadLocal.get().get(key);
    }

    public static Boolean getBool(String key) {
        Boolean value = (Boolean) threadLocal.get().get(key);
        if (value == null) {
            return false;
        }
        return value;
    }

    public static <T> T getBean(String key) {
        Object value = threadLocal.get().get(key);
        return (T) value;
    }


}
