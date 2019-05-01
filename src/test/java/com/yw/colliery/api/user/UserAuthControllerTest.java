package com.yw.colliery.api.user;

import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
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
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/user-auth/select/id/1" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }

    @Test
    public void selectByUserName() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ApiResponse> response = template.getForEntity("http://localhost:8888/userauth/select/name/test_user" , ApiResponse.class);
        Assert.assertEquals(response.getBody().getCode(), ApiCode.CODE_SUCCESS.code());
    }
}
