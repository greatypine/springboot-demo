package com.example.demo.common.utils.encrypt;

import java.io.UnsupportedEncodingException;

/**
 * MD5加密工具
 */
public class MD5Util {

    public static String encode(String source) {
        String sRet = null;
        try {
            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
            alga.update(source.getBytes());
            byte[] digesta = alga.digest();
            sRet = byte2hex(digesta);
        } catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("非法摘要算法");
        }
        return sRet;
    }

    public static String byte2hex(byte[] b) //二行制转字符
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + "";
        }
        return hs;
    }

    /**
     * 专门用来处理包含中文字符的md5
     *
     * @return
     */
    public static String encodeChinese(String source) {
        String sRet = null;
        try {
            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
            alga.update(source.getBytes("utf-8"));
            byte[] digesta = alga.digest();
            sRet = byte2hex(digesta);
        } catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("非法摘要算法");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sRet;
    }


}
