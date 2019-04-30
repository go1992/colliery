package com.yw.colliery.sdk.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author renzhiqiang
 * @Description 加密工具
 * @Date 2019-04-30
 **/
public class EncodeUtils {

    private static BCryptPasswordEncoder encoder;
    static {
        encoder = new BCryptPasswordEncoder();
    }

    /**
     * 加密
     * @param content
     * @return
     */
    public static String encode(String content) {
        return encoder.encode(content);
    }
}
