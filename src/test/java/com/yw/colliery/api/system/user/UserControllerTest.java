package com.yw.colliery.api.system.user;

import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.sdk.request.UserRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author renzhiqiang
 * @Description 用户接口单元测试
 * @Date 2019-05-01
 **/
public class UserControllerTest {

    @Test
    public void add() {
        RestTemplate template = new RestTemplate();
        UserRequest entity = new UserRequest();
        entity.setRoleId(1);
        entity.setDepartId(1);
        entity.setName("测试账号");
        entity.setUserPwd("test");
        entity.setCreateUser("test_user");
        entity.setModifyUser("test_user");
        HttpEntity<UserRequest> httpEntity = new HttpEntity<UserRequest>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/user/add" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void update() {
        RestTemplate template = new RestTemplate();
        UserRequest entity = new UserRequest();
        entity.setRoleId(1);
        entity.setDepartId(1);
        entity.setName("test_user1");
        entity.setUserPwd("test_password");
        entity.setCreateUser("test_user1");
        entity.setModifyUser("test_user1");
        HttpEntity<UserRequest> httpEntity = new HttpEntity<UserRequest>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/user/update" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }


    @Test
    public void select() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/user/select/allinfo/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }


    @Test
    public void delete() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/user/delete/1" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }
}
