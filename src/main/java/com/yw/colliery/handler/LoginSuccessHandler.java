package com.yw.colliery.handler;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.api.login.helper.UserSessionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 验证成功处理
 * @author: xuzhou-lhq
 * @create: 2019-04-25 12:33
 **/
@Component
@Slf4j
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserSessionHelper userSessionHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("login success...");
        String sessionId = request.getRequestedSessionId();
//        String loginIp = ScsfUtil.getIpAddress(request);
//        asyncTask.saveLoginLogHttp(sessionId,loginIp,loginName);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"SUCCESS\",\"message\":\"登录成功！\",\"date\":\""+ System.currentTimeMillis() +"\"}");
        out.flush();
        out.close();
        String loginName = authentication.getName();
        //登录成功保存用户安全信息到session
        userSessionHelper.saveSafetyUser(loginName);
    }
}
