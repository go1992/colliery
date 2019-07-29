package com.yw.colliery.api.system.depart;

import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.sdk.request.DepartRequest;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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
        DepartRequest entity = new DepartRequest();
        entity.setDepartName("test_part");
        entity.setAuthIds(Lists.newArrayList("1","2"));
        entity.setCoalMine("test_coal_mine");
        entity.setCreateUser("test_user");
        entity.setCreateDate(new Date());
        entity.setModifyUser("test_user");
        entity.setModifyDate(new Date());
        HttpEntity<DepartRequest> httpEntity = new HttpEntity<DepartRequest>(entity);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8888/department/add" , httpEntity , ResultObject.class);
        Assert.assertEquals(response.getBody().getStatus(), ResultObject.SUCCESS);
    }

    @Test
    public void update() {
        RestTemplate template = new RestTemplate();
        DepartRequest request = new DepartRequest();
        request.setId(10);
        request.setDepartName("test_part1");
        request.setAuthIds(Arrays.asList(new String[]{"1","2"}));
        request.setCoalMine("test_coal_mine1");
        request.setCreateUser("test_user1");
        request.setCreateDate(new Date());
        request.setModifyUser("test_user1");
        request.setModifyDate(new Date());
        HttpEntity<DepartRequest> httpEntity = new HttpEntity<DepartRequest>(request);
        ResponseEntity<ResultObject> response = template.postForEntity("http://localhost:8090/department/update" , httpEntity , ResultObject.class);
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
