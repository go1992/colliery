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
 * 安全风险管控-风险跟踪及报表
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
@ApiModel(value="AqfxFxgz对象", description="安全风险管控-风险跟踪及报表")
public class AqfxFxgz implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id ",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "辨识日期 ",example = "2012-12-26")
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


	@ApiModelProperty(value = "类型 ",example = "类型 ")
	private String leixing;


	@ApiModelProperty(value = "风险点 ",example = "风险点 ")
	private String fxd;


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


	@ApiModelProperty(value = "整改措施 ",example = "整改措施 ")
	private String zgcs;


	@ApiModelProperty(value = "跟踪记录 ",example = "跟踪记录 ")
	private String gzjl;


	@ApiModelProperty(value = "跟踪说明 ",example = "跟踪说明 ")
	private String gzsm;


	@ApiModelProperty(value = "跟踪人员 ",example = "跟踪人员 ")
	private String gzry;


	@ApiModelProperty(value = "跟踪时间 ",example = "跟踪时间 ")
	private String gzsj;


	@ApiModelProperty(value = "罚款 ",example = "罚款 ")
	private String fakuan;


	@ApiModelProperty(value = "效果 ",example = "效果 ")
	private String xiaoguo;


	@ApiModelProperty(value = "是否上报 ",example = "是否上报 ")
	private String sfsb;


	@ApiModelProperty(value = "是否消警 ",example = "是否消警 ")
	private String sfxj;


}
