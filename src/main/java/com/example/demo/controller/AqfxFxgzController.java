package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.AqfxFxgzServiceImpl;
import com.example.demo.entity.AqfxFxgz;
/**
 * <p>
 * 安全风险管控-风险跟踪及报表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-fxgz")
@Api(value = "AqfxFxgzController", description = "安全风险管控-风险跟踪及报表")
@Slf4j
public class AqfxFxgzController extends BaseController<AqfxFxgzServiceImpl,AqfxFxgz> {

}
