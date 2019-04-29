package com.yw.colliery.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.service.impl.AqfxJbcsFxlxServiceImpl;
import com.yw.colliery.entity.AqfxJbcsFxlx;
/**
 * <p>
 * 安全风险管控-基本参数设置-风险类型设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-jbcs-fxlx")
@Api(value = "AqfxJbcsFxlxController", description = "安全风险管控-基本参数设置-风险类型设置")
@Slf4j
public class AqfxJbcsFxlxController extends BaseController<AqfxJbcsFxlxServiceImpl,AqfxJbcsFxlx> {

}
