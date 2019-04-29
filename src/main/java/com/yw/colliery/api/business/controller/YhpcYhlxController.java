package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.service.impl.YhpcYhlxServiceImpl;
import com.yw.colliery.entity.YhpcYhlx;
/**
 * <p>
 * 隐患排查治理-隐患类型设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/yhpc-yhlx")
@Api(value = "YhpcYhlxController", description = "隐患排查治理-隐患类型设置")
@Slf4j
public class YhpcYhlxController extends BaseController<YhpcYhlxServiceImpl,YhpcYhlx> {

}
