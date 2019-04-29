package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.service.impl.YhpcFktkServiceImpl;
import com.yw.colliery.entity.YhpcFktk;
/**
 * <p>
 * 隐患排查治理-隐患罚款条款设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/yhpc-fktk")
@Api(value = "YhpcFktkController", description = "隐患排查治理-隐患罚款条款设置")
@Slf4j
public class YhpcFktkController extends BaseController<YhpcFktkServiceImpl,YhpcFktk> {

}
