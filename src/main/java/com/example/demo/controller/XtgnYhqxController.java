package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.XtgnYhqxServiceImpl;
import com.example.demo.entity.XtgnYhqx;
/**
 * <p>
 * 系统功能-用户权限 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-09
 */
@RestController
@RequestMapping("/apiv1/xtgn-yhqx")
@Api(value = "XtgnYhqxController", description = "系统功能-用户权限")
@Slf4j
public class XtgnYhqxController extends BaseController<XtgnYhqxServiceImpl,XtgnYhqx> {

}
