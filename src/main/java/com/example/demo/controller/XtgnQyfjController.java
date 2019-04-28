package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.XtgnQyfjServiceImpl;
import com.example.demo.entity.XtgnQyfj;
/**
 * <p>
 * 系统功能-区域分级 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@RestController
@RequestMapping("/apiv1/xtgn-qyfj")
@Api(value = "XtgnQyfjController", description = "系统功能-区域分级")
@Slf4j
public class XtgnQyfjController extends BaseController<XtgnQyfjServiceImpl,XtgnQyfj> {

}
