package com.yw.colliery.sdk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: renzhiqiang
 * @Description: 认证异常
 * @Date: 2019/4/29
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public final class AuthException extends RuntimeException{
    private AuthEnum auth;
    private String msg;

    public static enum AuthEnum {
        MODULE(1, "模块认证"),
        USER(2, "用户认证");

        private int type;
        private String desc;

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        private AuthEnum(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }
    }
}
