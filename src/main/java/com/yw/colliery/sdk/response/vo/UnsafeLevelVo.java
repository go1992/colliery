package com.yw.colliery.sdk.response.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author renzhiqiang
 * @Description 年度风险等级VO
 * @Date 2019-05-26
 **/
@Data
public class UnsafeLevelVo implements Serializable {
    private static final long serialVersionUID = -2613501640009429852L;
    /**
     * 风险等级
     */
    private String fxdj;

    /**
     * 风险数量
     */
    private Integer count;
}
