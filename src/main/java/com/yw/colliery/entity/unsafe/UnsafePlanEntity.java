package com.yw.colliery.entity.unsafe;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 隐患排查治理-隐患信息实体
 *
 * @author xuzhou
 * @since 2019-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UnsafePlanEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 计划ID
     */
    private Long planId;
    /**
     * 计划内容
     */
        private String content;
    /**
     * 排查部门
     */
    private String departName;
    /**
     * 排查类型
     */
    private String type;
    /**
     * 排查地点
     */
    private String location;

    /**
     * 排查日期
     */
    private Date planDate;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改者
     */
    private String modifyUser;

    /**
     * 修改日期
     */
    private Date modifyDate;
}
