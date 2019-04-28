package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.DdxxJbScdwServiceImpl;
import com.example.demo.entity.DdxxJbScdw;
/**
 * <p>
 * 调度信息管理-基本信息设置-生产单位设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/ddxx-jb-scdw")
@Api(value = "DdxxJbScdwController", description = "调度信息管理-基本信息设置-生产单位设置")
@Slf4j
public class DdxxJbScdwController extends BaseController<DdxxJbScdwServiceImpl,DdxxJbScdw> {

}
