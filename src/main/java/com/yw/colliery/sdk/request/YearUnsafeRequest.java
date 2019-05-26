package com.yw.colliery.sdk.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 年度风险请求
 * @Date 2019-05-26
 **/
@Data
@ToString
public class YearUnsafeRequest implements Serializable {
    private static final long serialVersionUID = -1279435170491623731L;
    private String queryTime;

    private Date startTime;

    private Date endTime;

    /**
     * 风险等级
     */
    private String fxdj;

    /**
     * 风险分类
     */
    private String fxfl;
}
