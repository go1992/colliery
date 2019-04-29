package com.yw.colliery.api.business.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.impl.AqfxGkxgServiceImpl;
import com.yw.colliery.entity.AqfxGkxg;
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
