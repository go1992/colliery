package com.yw.colliery.sdk.constans;

/**
 * @Author: renzhiqiang
 * @Description: 认证常量类
 * @Date: 2019/4/29
 */
public final class AuthConstant {

    public static class Module {
        public static final String NO_MODULE_AUTH = "您没有该模块的任何权限.";

        /**系统设置.**/
        public static final int SYSTEM_MODULE = 1;
        /**地测防治水管理.**/
        public static final int GEODETIC_MODULE = 2;
        /**安全风险管控.**/
        public static final int SAFE_MODULE = 3;
        /**隐患治理.**/
        public static final int UNSAFE_MODULE= 4;
        /**调度信息管理.**/
        public static final int DISPATCH_MODULE = 5;


    }

    public static class Level {
        public static final String NO_MODULE_AUTH = "您没有该模块的任何权限.";

        /**高等级.**/
        public static final int HIGH = 1;
        /**低等级.**/
        public static final int LOW = 2;
    }

}
