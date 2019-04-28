package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.DcJbcsYhServiceImpl;
import com.example.demo.entity.DcJbcsYh;
/**
 * <p>
 * 地测信息管理-基本参数-用户 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/dc-jbcs-yh")
@Api(value = "DcJbcsYhController", description = "地测信息管理-基本参数-用户")
@Slf4j
public class DcJbcsYhController extends BaseController<DcJbcsYhServiceImpl,DcJbcsYh> {

}
