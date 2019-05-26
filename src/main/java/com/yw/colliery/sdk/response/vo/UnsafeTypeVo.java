package com.yw.colliery.sdk.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 年度风险类型VO
 * @Date 2019-05-26
 **/
@Data
public class UnsafeTypeVo implements Serializable {
    private static final long serialVersionUID = 9003574003248268569L;
    /**
     * 风险类型
     */
    private String fxfl;

    /**
     * 风险等级
     */
    private List<UnsafeLevelVo> levelVos;
}
