package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.example.demo.absys.BaseController;
import com.example.demo.service.IAqfxCsxgService;
import com.example.demo.service.IAqfxGkxgService;
import com.example.demo.service.impl.AqfxGkxgServiceImpl;
import com.example.demo.entity.AqfxCsxg;
import com.example.demo.entity.AqfxGkxg;
/**
 * <p>
 * 安全风险管控-管控效果及分析 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-gkxg")
@Api(value = "AqfxGkxgController", description = "安全风险管控-管控效果及分析")
@Slf4j
public class AqfxGkxgController extends BaseController<AqfxGkxgServiceImpl,AqfxGkxg> {
	
	
	/*@Autowired
	private IAqfxGkxgService service;
	
	@PostMapping("xm")
	public QueryChainWrapper<AqfxGkxg> xiemeng() {
		return service.query();
	}*/

}
