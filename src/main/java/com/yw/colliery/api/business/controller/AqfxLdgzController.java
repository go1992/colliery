package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.impl.AqfxLdgzServiceImpl;
import com.yw.colliery.entity.AqfxLdgz;
/**
 * <p>
 * 安全风险管控-领导跟踪检查 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-ldgz")
@Api(value = "AqfxLdgzController", description = "安全风险管控-领导跟踪检查")
@Slf4j
public class AqfxLdgzController extends BaseController<AqfxLdgzServiceImpl,AqfxLdgz> {

}
