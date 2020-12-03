package com.example.demo.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额计算工具类
 * BigDecimal.ROUND_UP:无论正数或者负数都进1
 * BigDecimal.ROUND_DOWN:无论正数或者负数直接舍入，任何情况都不进1
 * BigDecimal.ROUND_CEILING:正数在舍入的时候都进1；负数直接舍掉
 * BigDecimal.ROUND_FLOOR:正数直接舍掉，负数舍入时进1
 * BigDecimal.ROUND_HALF_UP:四舍五入
 * BigDecimal.ROUND_HALF_DOWN:四舍五入
 * BigDecimal.ROUND_HALF_EVEN:四舍五入
 */
public class NumberUtil {

    /**
     * 正数按小数位,四舍五入
     *
     * @return
     */
    public static BigDecimal getNumberOfRound(BigDecimal value,Integer dotNumber) {
        BigDecimal resultNum = value.setScale(dotNumber, RoundingMode.HALF_UP);
        return resultNum;
    }

    /**
     * 正数按小数位,截取
     *
     * @return
     */
    public static BigDecimal getNumberOfCutoff(BigDecimal value,Integer dotNumber) {
        BigDecimal resultNum = value.setScale(dotNumber, RoundingMode.DOWN);
        return resultNum;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(subZeroAndDot("00.100"));
        System.out.println(new BigDecimal("0100.0001").stripTrailingZeros().toPlainString());
    }
}
