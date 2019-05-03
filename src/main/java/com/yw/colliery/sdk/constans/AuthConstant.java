package com.yw.colliery.sdk.constans;

/**
 * @Author: renzhiqiang
 * @Description: 认证常量类
 * @Date: 2019/4/29
 */
public final class AuthConstant {

    public static class Module {
        public static final String NO_MODULE_AUTH = "您没有该模块的任何权限.";

        /**系统设置-增删改权限.**/
        public static final int SYSTEM_MODULE_SUPER = 1;
        /**系统设置-查看权限.**/
        public static final int SYSTEM_MODULE_WATCH = 2;
        /**地测防治水管理-增删改权限.**/
        public static final int GEODETIC_MODULE_SUPER = 3;
        /**地测防治水管理-查看权限.**/
        public static final int GEODETIC_MODULE_WATCH = 4;
        /**安全风险管控-增删改权限.**/
        public static final int SAFE_MODULE_SUPER = 5;
        /**安全风险管控-查看权限.**/
        public static final int SAFE_MODULE_WATCH = 6;
        /**隐患治理-增删改权限.**/
        public static final int UNSAFE_MODULE_SUPER = 7;
        /**隐患治理-查看权限.**/
        public static final int UNSAFE_MODULE_WATCH = 8;
    }

    public static enum Module1 {
        SYSTEM_MODULE_SUPER(1, "增删改权限", "系统设置模块");
        private int authId;
        private String authName;
        private String moduleName;

        public int getAuthId() {
            return authId;
        }

        public String getAuthName() {
            return authName;
        }

        public String getModuleName() {
            return moduleName;
        }

        private Module1(int authId, String authName, String moduleName) {
            this.authId = authId;
            this.authName = authName;
            this.moduleName = moduleName;
        }
    }
}
