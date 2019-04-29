package com.yw.colliery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 系统功能-区域分级
 * </p>
 *
 * @author jobob
 * @since 2019-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="XtgnQyfj对象", description="系统功能-区域分级")
public class XtgnQyfj implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "父级id  ",example = "1")
	private Long parentid;


	@ApiModelProperty(value = "所有父级id,以逗号隔开(,0,1,2,)",example = "所有父级id,以逗号隔开(,0,1,2,)")
	private String parentids;


	@ApiModelProperty(value = "数据所属煤矿",example = "数据所属煤矿")
	private String ssmk;


	@ApiModelProperty(value = "区域名",example = "区域名")
	private String qymdd;


    @ApiModelProperty(value = "添加日期",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pcrq;


	@ApiModelProperty(value = "预留字段a",example = "预留字段a")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b",example = "预留字段b")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c",example = "预留字段c")
	private String ylzdc;


	@ApiModelProperty(value = "创建人",example = "创建人")
	private String cjrdd;


}
