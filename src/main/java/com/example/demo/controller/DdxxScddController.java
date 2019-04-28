package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.DdxxScddServiceImpl;
import com.example.demo.entity.DdxxScdd;
/**
 * <p>
 * 调度信息管理-安全生产调度报表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/ddxx-scdd")
@Api(value = "DdxxScddController", description = "调度信息管理-安全生产调度报表")
@Slf4j
public class DdxxScddController extends BaseController<DdxxScddServiceImpl,DdxxScdd> {

}
