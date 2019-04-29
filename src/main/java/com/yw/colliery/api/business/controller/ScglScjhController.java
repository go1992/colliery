package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.service.impl.ScglScjhServiceImpl;
import com.yw.colliery.entity.ScglScjh;
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
