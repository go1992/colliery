package com.yw.colliery.api.depart;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeTest;

import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 部门controller测试接口
 * @Date 2019-05-01
 **/
public class DepartControllerTest {


    @Test
    public void add() {
        RestTemplate template = new RestTemplate();
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDepartName("test_part");
        entity.setAuthIds("1,2");
        entity.setCoalMine("test_coal_mine");
        entity.setCreateUser("test_user");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user");
        entity.setModifyDate(new Date());
        HttpEntity<DepartmentEntity> httpEntity = new HttpEntity<DepartmentEntity>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/department/add" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void update() {
        RestTemplate template = new RestTemplate();
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(1);
        entity.setDepartName("test_part1");
        entity.setAuthIds("1,2");
        entity.setCoalMine("test_coal_mine1");
        entity.setCreateUser("test_user1");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user1");
        entity.setModifyDate(new Date());
        HttpEntity<DepartmentEntity> httpEntity = new HttpEntity<DepartmentEntity>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/epartment/update" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }


    @Test
    public void select() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/department/select/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void selectAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/department/select/all" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void delete() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/department/delete/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }
}
