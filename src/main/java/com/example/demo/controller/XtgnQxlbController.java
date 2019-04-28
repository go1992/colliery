package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.XtgnQxlbServiceImpl;
import com.example.demo.entity.XtgnQxlb;
/**
 * <p>
 * 系统功能-权限列表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@RestController
@RequestMapping("/apiv1/xtgn-qxlb")
@Api(value = "XtgnQxlbController", description = "系统功能-权限列表")
@Slf4j
public class XtgnQxlbController extends BaseController<XtgnQxlbServiceImpl,XtgnQxlb> {

}
