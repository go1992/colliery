package com.yw.colliery.sdk.response;

/**
 * @Author renzhiqiang
 * @Description Api错误枚举
 * @Date 2019-04-09
 **/
public enum ApiCode {
    /**
     * 成功返回码
     */
    CODE_SUCCESS(200),

    /**
     * 失败错误码
     */
    CODE_FAIL(201),

    /**
     * 返回内容为空
     */
    CODE_CONTENT_EMPTY(204),

    /**
     * 访问被拒绝
     */
    CODE_REJECT(403);





    private ApiCode(int intCode) {
        this.intCode = intCode;
    }

    private int intCode;

    public int code() {
        return intCode;
    }

    public static ApiCode getApiCode(int code){
        for (ApiCode apiCode : ApiCode.values()){
            if (apiCode.intCode == code){
                return apiCode;
            }
        }
        //如果没找到对应code，返回fail_code
        return CODE_FAIL;
    }
}
