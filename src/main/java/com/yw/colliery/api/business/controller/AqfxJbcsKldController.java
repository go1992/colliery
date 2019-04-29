package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.service.impl.AqfxJbcsKldServiceImpl;
import com.yw.colliery.entity.AqfxJbcsKld;
/**
 * <p>
 * 安全风险管控-基本参数设置-矿领导设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-jbcs-kld")
@Api(value = "AqfxJbcsKldController", description = "安全风险管控-基本参数设置-矿领导设置")
@Slf4j
public class AqfxJbcsKldController extends BaseController<AqfxJbcsKldServiceImpl,AqfxJbcsKld> {

}
