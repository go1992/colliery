package com.yw.colliery.api.role;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.role.RoleEntity;
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
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/role/add" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
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
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/role/update" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }


    @Test
    public void select() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/role/select/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void selectAll() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/role/select/all" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void delete() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/role/delete/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }
}
