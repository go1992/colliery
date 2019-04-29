package com.yw.colliery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 隐患排查治理-隐患录入
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
@ApiModel(value="YhpcYhlr对象", description="隐患排查治理-隐患录入")
public class YhpcYhlr implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableField(exist=false)
	private Integer shuliang;

    @ApiModelProperty(value = "主键id ",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "检查时间 ",example = "2012-12-26")
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


	@ApiModelProperty(value = "检查人 ",example = "检查人 ")
	private String jcr;


	@ApiModelProperty(value = "检查类型 ",example = "检查类型 ")
	private String jclx;


	@ApiModelProperty(value = "隐患地点 ",example = "隐患地点 ")
	private String yhdd;


	@ApiModelProperty(value = "整改日期 ",example = "整改日期 ")
	private String zgrq;


	@ApiModelProperty(value = "隐患内容 ",example = "隐患内容 ")
	private String yhnr;


	@ApiModelProperty(value = "整改措施 ",example = "整改措施 ")
	private String zgcs;


	@ApiModelProperty(value = "责任单位 ",example = "责任单位 ")
	private String zrdw;


	@ApiModelProperty(value = "责任人 ",example = "责任人 ")
	private String zrr;


	@ApiModelProperty(value = "隐患类型 ",example = "隐患类型 ")
	private String yhlx;


	@ApiModelProperty(value = "隐患级别 ",example = "隐患级别 ")
	private String yhjb;


	@ApiModelProperty(value = "罚款理由 ",example = "罚款理由 ")
	private String fkly;


	@ApiModelProperty(value = "罚款金额 ",example = "罚款金额 ")
	private String fkje;


	@ApiModelProperty(value = "罚款查找 ",example = "罚款查找 ")
	private String fkcz;


	@ApiModelProperty(value = "隐患状态 ",example = "隐患状态 ")
	private String yhzt;


	@ApiModelProperty(value = "处理过程跟踪 ",example = "处理过程跟踪 ")
	private String clgcgz;


}
