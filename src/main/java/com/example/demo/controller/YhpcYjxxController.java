package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.YhpcYjxxServiceImpl;
import com.example.demo.entity.YhpcYjxx;
/**
 * <p>
 * 隐患排查治理-安全预警信息发布 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/yhpc-yjxx")
@Api(value = "YhpcYjxxController", description = "隐患排查治理-安全预警信息发布")
@Slf4j
public class YhpcYjxxController extends BaseController<YhpcYjxxServiceImpl,YhpcYjxx> {

}
