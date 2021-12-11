package io.github.xxyopen.util;


import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * MD5工具类
 *
 * @author xiongxiaoyang
 * @version 1.0
 * @since 2020/5/23
 */
public class MD5Util {

    private static final String DEFAULT_CHARSET = "utf-8";

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) {
            resultSb.append(byteToHexString(value));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    @SneakyThrows
    public static String MD5Encode(String origin) {
        return MD5Encode(origin,DEFAULT_CHARSET);
    }

    @SneakyThrows
    public static String MD5Encode(String origin, String charsetName) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToHexString(md.digest(origin
                .getBytes(charsetName)));
    }

    @SneakyThrows
    public static String MD5New(String str) {
        //首先利用MD5算法将密码加密，变成等长字节
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b1 = md.digest(str.getBytes());
        //将等长字节利用Base64算法转换成字符串
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(b1);
    }

    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


}