package com.yw.colliery.entity.productmanager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawCoalEntity implements Serializable{


    private static final long serialVersionUID = -7800038877932462416L;

    private Integer id;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 入井人数
     */
    private String minePeoples;

    /**
     * 产量
     */
    private String output;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 销售量
     */
    private String salesVolume;

    /**
     * 库存
     */
    private String stock;
    /**
     * 页数
     */
    @JsonIgnore
    private Integer pageNum=0;
    /**
     * 每页大小
     */
    @JsonIgnore
    private Integer pageSize=0;
    /**
     * 排序的字段
     */
    @JsonIgnore
    private String orderName;
    /**
     * 升序还是降序
     */
    @JsonIgnore
    private String order;

}
