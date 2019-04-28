package com.example.demo.entity;

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
 * 系统功能-用户列表
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
@ApiModel(value="XtgnYhlb对象", description="系统功能-用户列表")
public class XtgnYhlb implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "创建时间",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pcrq;


	@ApiModelProperty(value = "数据所属煤矿",example = "数据所属煤矿")
	private String ssmk;


	@ApiModelProperty(value = "预留字段a",example = "预留字段a")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b",example = "预留字段b")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c",example = "预留字段c")
	private String ylzdc;


	@ApiModelProperty(value = "用户名",example = "用户名")
	private String yhmdd;


	@ApiModelProperty(value = "用户密码",example = "用户密码")
	private String yhmm;


	private String cjsj;


	@ApiModelProperty(value = "创建人",example = "创建人")
	private String cjrdd;


	@ApiModelProperty(value = "用户所属区域的名称 qylb里面的名称")
	private int ssqymc;


}
