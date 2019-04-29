package com.yw.colliery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.colliery.entity.DdxxScdd;
import com.yw.colliery.mapper.DdxxScddMapper;

//@Service
public class ScheduledService {
	
	@Autowired
	private DdxxScddMapper ddxxScddMapper;

	//初始延迟0.001秒，每隔5秒
	@Scheduled(fixedRate=5000,initialDelay = 1)
	public void testFixedRate(){
		long current = 1;
		IPage<DdxxScdd> iPage = new Page<DdxxScdd>(current,4);
		do {
			current = iPage.getCurrent();
			ddxxScddMapper.selectPage(iPage, null);
			System.out.println(JSON.toJSONString(iPage));
			iPage.setCurrent(current+1);
		}while(current<iPage.getPages());
	}
	
}
