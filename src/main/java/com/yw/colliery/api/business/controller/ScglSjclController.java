package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.service.impl.ScglSjclServiceImpl;
import com.yw.colliery.entity.ScglSjcl;
/**
 * <p>
 * 生产管理-实际产量 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-08
 */
@RestController
@RequestMapping("/apiv1/scgl-sjcl")
@Api(value = "ScglSjclController", description = "生产管理-实际产量")
@Slf4j
public class ScglSjclController extends BaseController<ScglSjclServiceImpl,ScglSjcl> {

}
