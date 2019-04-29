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
 * 安全风险管控-管控措施修改记录
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="AqfxCsxg对象", description="安全风险管控-管控措施修改记录")
public class AqfxCsxg implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id ",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "辨识时间 ",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pcrq;


	@ApiModelProperty(value = "数据所属煤矿 ",example = "数据所属煤矿 ")
	private String ssmk;


	@ApiModelProperty(value = "预留字段a ",example = "预留字段a ")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b ",example = "预留字段b ")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c ",example = "预留字段c ")
	private String ylzdc;


	@ApiModelProperty(value = "风险点 ",example = "风险点 ")
	private String fxd;


	@ApiModelProperty(value = "地图位置定点 ",example = "地图位置定点 ")
	private String dtwzdd;


	@ApiModelProperty(value = "责任单位 ",example = "责任单位 ")
	private String zrdw;


	@ApiModelProperty(value = "风险等级 ",example = "风险等级 ")
	private String fxdj;


	@ApiModelProperty(value = "风险分类 ",example = "风险分类 ")
	private String fxfl;


	@ApiModelProperty(value = "限制人数 ",example = "限制人数 ")
	private String xzrs;


	@ApiModelProperty(value = "风险描述 ",example = "风险描述 ")
	private String fxms;


	@ApiModelProperty(value = "管控措施 ",example = "管控措施 ")
	private String gkcs;


	@ApiModelProperty(value = "主要负责人 ",example = "主要负责人 ")
	private String zyfzr;


	@ApiModelProperty(value = "分管负责人 ",example = "分管负责人 ")
	private String fgfzr;


	@ApiModelProperty(value = "类型(年度风险,专项风险)",example = "类型(年度风险,专项风险)")
	private String fxlx;


    @ApiModelProperty(value = "修改日期",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date xgrq;


	@ApiModelProperty(value = "措施变化",example = "措施变化")
	private String csbh;


    @ApiModelProperty(value = "原始数据id",example = "1")
	private Long ysid;


}
