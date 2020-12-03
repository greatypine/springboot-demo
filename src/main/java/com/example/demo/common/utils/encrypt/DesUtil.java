package com.example.demo.common.utils.encrypt;


import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 */
public class DesUtil {
    /**
     * 默认的Key键
     */
    public static final String Default_Key = "ABCD14CJO5F68DY69EQ5IWBYA3F2DESJ";
    /**
     * 加密算法的名称
     */
    private static String Algorithm = "DESede";

    //ABCD14CJO5F68DY69EQ5IWBYA3F2DESJ

    /**
     * 加密字符串
     *
     * @param value 待加密的字串
     * @return 加密后的字串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptString(String value) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        return encryptString(Default_Key, value);
    }

    /**
     * 加密字符串
     *
     * @param key   加密使用的Key
     * @param value 待加密的字串
     * @return 加密后的字串
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptString(String key, String value) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null)
            new IllegalArgumentException("待加密字串不允许为空");
        // 密码器
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try {
            bytesKey = Base64.decodeBase64(key);
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            bytes = value.getBytes();// 待解密的字串
            desCipher.init(Cipher.ENCRYPT_MODE, deskey);// 初始化密码器，用密钥deskey,进入解密模式
            bytesCipher = desCipher.doFinal(bytes);
            return Base64.encodeBase64String(bytesCipher);// PEKBase64.encodeBase64String(bytesCipher).trim();
        } finally {
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
    }

    /**
     * 给IM用的加密方法
     *
     * @param key
     * @param value
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String aesEncodeString(String key, String value) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        byte[] bytesKey = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        byte[] byteContent = value.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] result = cipher.doFinal(byteContent);
        return new String(result);
    }

    /**
     * 解密字符串
     *
     * @param value 待解密的字串
     * @return 解码后的字串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decryptString(String value) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {
        if (StringUtils.isNotBlank(value)) {
            return decryptString(Default_Key, value);
        } else
            return "";
    }

    /**
     * 解密字符串
     *
     * @param key   解码使用的Key
     * @param value 待解密的字串
     * @return 解码后的字串
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decryptString(String key, String value) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null)
            new IllegalArgumentException("待解密字串不允许为空");
        // 密码器
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try {
            bytesKey = Base64.decodeBase64(key);
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            try {
                bytes = Base64.decodeBase64(value);
            } catch (Exception e) {
                bytes = null;
            }
            desCipher.init(Cipher.DECRYPT_MODE, deskey);// 初始化密码器，用密钥deskey,进入解密模式
            bytesCipher = desCipher.doFinal(bytes);
            return (new String(bytesCipher, "UTF-8"));
        } finally {
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
    }

    /**
     * @return 16进制字符串
     * @throws
     * @Title:string2HexString
     * @Description:字符串转16进制字符串 字符串
     */
    public static String string2HexString(String string) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            int ch = (int) string.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2String
     * @Description:16进制字符串转字符串
     */
    public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue();
        }
        return temp;
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException {

        String encodeStr = aesEncodeString("12345678901234567890123456789012", "123");
        System.out.println(Base64Utils.encode(encodeStr.getBytes()));
    	/*
        String source = "Hasdoo19123";
        String dest = DesUtils.encryptString(source);
        System.out.println(dest);
        System.out.println(DesUtils.decryptString(dest));
        String formatter = "%s/%s/%s";
        String url = String.format(formatter, "http://open.51zhangdan.com/contexts/qq.com", "57872051",
            "414hryu&&312312312");
        System.out.println(url);
        */
    }
}
