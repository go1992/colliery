package com.yw.colliery.sdk.response;

import com.yw.colliery.sdk.response.vo.UnsafeLevelVo;
import com.yw.colliery.sdk.response.vo.UnsafeTypeVo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 年度风险返回结果
 * @Date 2019-05-26
 **/
@Data
@AllArgsConstructor
public class YearUnsafeResponse implements Serializable {
    private static final long serialVersionUID = -2977319232540532899L;
    private List<UnsafeTypeVo> typeVos;

    private List<UnsafeLevelVo> levelVos;
}
