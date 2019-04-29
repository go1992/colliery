package com.yw.colliery.api.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //chain 若为true，则setter方法返回当前对象，通过该注解可以控制getter和setter方法的形式。
@AllArgsConstructor//生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@ApiModel(value="ResultMessage", description="返回结果")//用于响应类上，表示一个返回响应数据的信息
public class ResultObject {
	public static String SUCCESS = "success";
	public static String FAILED = "failed";
	
	@ApiModelProperty(value="接口调用状态(success表示成功,failed表示失败)")   
	private String status;
	@ApiModelProperty(value="接口编号")//用在属性上，描述响应类的属性
	private String code;
	@ApiModelProperty(value="接口调用消息")
	private String message;
	@ApiModelProperty(value="接口调用数据结果")
	private Object result;
}
