package com.yw.colliery.api.role;

import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 角色接口单元测试
 * @Date 2019-05-01
 **/
public class RoleControllerTest {

    @Test
    public void add() {
        RestTemplate template = new RestTemplate();
        RoleEntity entity = new RoleEntity();
        entity.setRoleName("test_role");
        entity.setAuthLevel(1);
        entity.setCreateUser("test_user");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user");
        entity.setModifyDate(new Date());
        HttpEntity<RoleEntity> httpEntity = new HttpEntity<RoleEntity>(entity);
        ResponseEntity<ApiResponse> response = template.postForEntity("http://localhost:8888/role/add" , httpEntity , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

    @Test
    public void update() {
        RestTemplate template = new RestTemplate();
        RoleEntity entity = new RoleEntity();
        entity.setId(1);
        entity.setRoleName("test_role1");
        entity.setAuthLevel(1);
        entity.setCreateUser("test_user1");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user1");
        entity.setModifyDate(new Date());
        HttpEntity<RoleEntity> httpEntity = new HttpEntity<RoleEntity>(entity);
        ResponseEntity<ApiResponse> response = template.postForEntity("http://localhost:8888/role/update" , httpEntity , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }


    @Test
    public void select() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/role/select/1" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

    @Test
    public void selectAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/role/select/all" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

    @Test
    public void delete() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/role/delete/1" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }
}
