package com.example.demo.entity;

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
 * 安全风险管控-领导跟踪检查
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
@ApiModel(value="AqfxLdgz对象", description="安全风险管控-领导跟踪检查")
public class AqfxLdgz implements Serializable {

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


	@ApiModelProperty(value = "位置 ",example = "位置 ")
	private String weizhi;


	@ApiModelProperty(value = "带班领导 ",example = "带班领导 ")
	private String dbld;


	@ApiModelProperty(value = "对接人员 ",example = "对接人员 ")
	private String djry;


	@ApiModelProperty(value = "入井时间 ",example = "入井时间 ")
	private String rjsj;


	@ApiModelProperty(value = "升井时间 ",example = "升井时间 ")
	private String sjsj;


	@ApiModelProperty(value = "班次 ",example = "班次 ")
	private String banci;


	@ApiModelProperty(value = "行走录像 ",example = "行走录像 ")
	private String xzlx;


	@ApiModelProperty(value = "LED内容 ",example = "LED内容 ")
	private String lednr;


	@ApiModelProperty(value = "重大风险管控整改 ",example = "重大风险管控整改 ")
	private String zdfxgkzg;


}
