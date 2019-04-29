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
 * 地测信息管理-水文险情及预警处理
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
@ApiModel(value="DcSwxqYjcl对象", description="地测信息管理-水文险情及预警处理")
public class DcSwxqYjcl implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableField(exist=false)
	private Integer shuliang;
    
    @ApiModelProperty(value = "主键id ",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "排查日期 ",example = "2012-12-26")
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


	@ApiModelProperty(value = "责任单位 ",example = "责任单位 ")
	private String zrdw;


	@ApiModelProperty(value = "地点 ",example = "地点 ")
	private String didian;


	@ApiModelProperty(value = "情况描述 ",example = "情况描述 ")
	private String qkms;


	@ApiModelProperty(value = "处理措施 ",example = "处理措施 ")
	private String clcs;


	@ApiModelProperty(value = "危险等级 ",example = "危险等级 ")
	private String wxdj;


	@ApiModelProperty(value = "治理效果 ",example = "治理效果 ")
	private String zlxg;


	@ApiModelProperty(value = "记录人 ",example = "记录人 ")
	private String jlr;


	@ApiModelProperty(value = "审核人 ",example = "审核人 ")
	private String shhr;


}
