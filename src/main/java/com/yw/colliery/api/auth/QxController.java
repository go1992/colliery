package com.yw.colliery.api.auth;


import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.entity.XtgnYhlb;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/apiv1/quanxian")
public class QxController {

    @PostMapping("/ifqx")
    public JSONObject quanxianif(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        HttpSession httpSession = request.getSession();
        XtgnYhlb session = (XtgnYhlb)httpSession.getAttribute(ESessionKey.User.key);
        jsonObject.put("session",session);
        jsonObject.put("quanxian","无操作权限");
        return jsonObject;
    }

}
