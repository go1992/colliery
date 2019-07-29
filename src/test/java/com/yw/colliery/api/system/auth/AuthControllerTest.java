package com.yw.colliery.api.system.auth;

import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 权限接口单元测试
 * @Date 2019-05-01
 **/
public class AuthControllerTest {

    private Logger logger = LogManager.getLogger();


    @Test
    public void add() {
        RestTemplate template = new RestTemplate();
        AuthEntity entity = new AuthEntity();
        entity.setName("测试权限");
        entity.setLevel(2);
        entity.setCreateUser("admin");
        entity.setCreateDate(new Date());
        entity.setModifyUser("admin");
        entity.setModifyDate(new Date());
        HttpEntity<AuthEntity> httpEntity = new HttpEntity<AuthEntity>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/auth/add" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void update() {
        RestTemplate template = new RestTemplate();
        AuthEntity entity = new AuthEntity();
        entity.setId(3);
        entity.setName("test_auth1");
        entity.setLevel(1);
        entity.setCreateUser("test_user1");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user1");
        entity.setModifyDate(new Date());
        HttpEntity<AuthEntity> httpEntity = new HttpEntity<AuthEntity>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/auth/update" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }


    @Test
    public void select() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/auth/select/2" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void selectAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/auth/select/all" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void delete() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/auth/delete/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

}
