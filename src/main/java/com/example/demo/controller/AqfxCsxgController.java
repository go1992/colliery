package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.absys.BaseController;
import com.example.demo.service.impl.AqfxCsxgServiceImpl;
import com.example.demo.entity.AqfxCsxg;
/**
 * <p>
 * 安全风险管控-管控措施修改记录 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-csxg")
@Api(value = "AqfxCsxgController", description = "安全风险管控-管控措施修改记录")
@Slf4j
public class AqfxCsxgController extends BaseController<AqfxCsxgServiceImpl,AqfxCsxg> {

}
