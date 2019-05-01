package com.yw.colliery.api.auth;

import com.yw.colliery.entity.auth.AuthEntity;
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
 * @Description 权限接口单元测试
 * @Date 2019-05-01
 **/
public class AuthControllerTest {

    private Logger logger = LogManager.getLogger();


    @Test
    public void add() {
        RestTemplate template = new RestTemplate();
        AuthEntity entity = new AuthEntity();
        entity.setName("test_auth");
        entity.setLevel(1);
        entity.setCreateUser("test_user");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user");
        entity.setModifyDate(new Date());
        HttpEntity<AuthEntity> httpEntity = new HttpEntity<AuthEntity>(entity);
        ResponseEntity<ApiResponse> response = template.postForEntity("http://localhost:8888/auth/add" , httpEntity , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
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
        ResponseEntity<ApiResponse> response = template.postForEntity("http://localhost:8888/auth/update" , httpEntity , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }


    @Test
    public void select() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/auth/select/2" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

    @Test
    public void selectAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/auth/select/all" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

    @Test
    public void delete() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/auth/delete/1" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

}
