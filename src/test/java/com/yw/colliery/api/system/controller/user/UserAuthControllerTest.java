package com.yw.colliery.api.system.controller.user;

import com.yw.colliery.api.base.ResultObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Author renzhiqiang
 * @Description 用户权限接口单元测试
 * @Date 2019-05-01
 **/
public class UserAuthControllerTest {

    @Test
    public void selectByUserId() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/user-auth/select/id/1", ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void selectByUserName() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ResultObject> response = template.getForEntity("http://localhost:8888/userauth/select/name/test_user" , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }
}
