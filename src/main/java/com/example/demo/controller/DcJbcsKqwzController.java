package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.DcJbcsKqwzServiceImpl;
import com.example.demo.entity.DcJbcsKqwz;
/**
 * <p>
 * 地测信息管理-基本参数-矿区位置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/dc-jbcs-kqwz")
@Api(value = "DcJbcsKqwzController", description = "地测信息管理-基本参数-矿区位置")
@Slf4j
public class DcJbcsKqwzController extends BaseController<DcJbcsKqwzServiceImpl,DcJbcsKqwz> {
    
}
