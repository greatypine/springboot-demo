package com.example.demo.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 金额计算工具类
 */
public class BigDecimalUtil {

    /**
     * 将一个数据分成固定份数，除不尽时放到最后一份
     *
     * @return
     */
    public static ArrayList<BigDecimal> resolve(BigDecimal bigDecimal, int size) {
        ArrayList<BigDecimal> result = new ArrayList<>();
        BigDecimal sizeDecimal = new BigDecimal(size);
        BigDecimal divide = bigDecimal.divide(sizeDecimal, 2, BigDecimal.ROUND_HALF_DOWN);

        for (int i = 0; i < size; i++) {
            result.add(divide);
        }

        BigDecimal multiply = divide.multiply(sizeDecimal);
        if (!(bigDecimal.compareTo(multiply) == 0)) {
            BigDecimal subtract = bigDecimal.subtract(multiply);
            BigDecimal add = divide.add(subtract);
            result.set(size - 1, add);
        }

        return result;

    }

    public static boolean canDivide(BigDecimal bigDecimal, int size) {
        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) return true;
        BigDecimal sizeDecimal = new BigDecimal(size);
        BigDecimal divide = bigDecimal.divide(sizeDecimal, 2, BigDecimal.ROUND_HALF_DOWN);
        int i = bigDecimal.compareTo(divide.multiply(sizeDecimal));
        return i == 0;
    }
}
