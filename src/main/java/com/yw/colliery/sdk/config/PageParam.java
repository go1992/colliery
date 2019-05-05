package com.yw.colliery.sdk.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author renzhiqiang
 * @Description 分页参数
 * @Date 2019-05-05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageParam implements Serializable {
    private static final long serialVersionUID = 328578193509559912L;
    private int pageNum;
    private int pageSize;
    private String ordername;
    private String order;

    public String generateOderBy() {
        if (ordername == null) {
            return null;
        }
        if (order != null) {
            return ordername + " " + order;
        }
        return ordername;
    }
}
