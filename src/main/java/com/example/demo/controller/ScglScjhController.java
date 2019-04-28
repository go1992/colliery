package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.ScglScjhServiceImpl;
import com.example.demo.entity.ScglScjh;
/**
 * <p>
 * 生产管理-生产计划 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-08
 */
@RestController
@RequestMapping("/apiv1/scgl-scjh")
@Api(value = "ScglScjhController", description = "生产管理-生产计划")
@Slf4j
public class ScglScjhController extends BaseController<ScglScjhServiceImpl,ScglScjh> {

}
