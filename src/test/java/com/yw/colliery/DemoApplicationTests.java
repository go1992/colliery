package com.yw.colliery;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.colliery.entity.DdxxScsg;
import com.yw.colliery.entity.SbglSblb;
import com.yw.colliery.entity.WxyglSjlb;
import com.yw.colliery.mapper.DdxxScddMapper;
import com.yw.colliery.mapper.DdxxScsgMapper;
import com.yw.colliery.mapper.WxyglSjlbMapper;
import com.yw.colliery.service.IDcSwdzZkpcService;
import com.yw.colliery.service.ISbglSblbService;

@RunWith(SpringRunner.class)
@SpringBootTest

@EnableScheduling
public class DemoApplicationTests {

    @Autowired
    private IDcSwdzZkpcService iDcSwdzZkpcService;
    @Autowired
    private DdxxScsgMapper ddxxScsgMapper;
    @Autowired
    private DdxxScddMapper ddxxScddMapper;
    @Autowired
    private ISbglSblbService iSbglSblbService;
    @Autowired
    private WxyglSjlbMapper wxyglSjlbMapper;


    @Test
    public void t5() {
        QueryWrapper<WxyglSjlb> qw = new QueryWrapper<WxyglSjlb>();
        qw.select("count(*) AS shuliang","qymc");
        qw.groupBy(true, "qymc");
        System.out.println(JSON.toJSONString(wxyglSjlbMapper.selectList(qw)));
    }

    public void t4() {
        QueryWrapper<SbglSblb> qw = new QueryWrapper<SbglSblb>();
        qw.eq("sbzt", "锁定");
        System.out.println(">>>>>>>>>>>>\n"+JSON.toJSONString(iSbglSblbService.myCount(qw)));
    }

    public void t3() {
        System.out.println(">>>>>>>>>>>>"+ddxxScddMapper.selectCount(null));
    }

    public void t2() {
        System.out.println("时间>>>>>>>>>>>>>>>"+System.currentTimeMillis());
        while(true);
    }

    public void t1() {
        IPage<Map<String,Object>> page = new Page<Map<String,Object>>();
        QueryWrapper<DdxxScsg> qw = new QueryWrapper<DdxxScsg>();
        Map<String,Object> params = new HashMap<String,Object>();
        //allEq false 判空过滤 用的是字符串判空 {null,""," ","			"}都表示空
        params.put("sgjb", "严重事故");
        qw.allEq(params,false);
        System.err.println("\t\t单元测试\n"+JSON.toJSONString(ddxxScsgMapper.myCount(page,qw)));
    }

    public void contextLoads() {
        System.out.println("------------------------glng------------------------");
        System.out.println("\n\t\t\n"+JSON.toJSONString(iDcSwdzZkpcService.listObjs()));
        System.out.println("\n\t\t\n"+JSON.toJSONString(iDcSwdzZkpcService.list()));
    }

}

