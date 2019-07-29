package com.yw.colliery.api.system.login;


import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.service.login.ValidateHandleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author xuzhou
 * 系统功能-用户列表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/apiv1/xtgn-yhlb")
public class LogInController {
    private Logger log = LoggerFactory.getLogger(LogInController.class);

    @Autowired
    private ValidateHandleService validateHandleService;

    /**
     * 验证码
     * @param params
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "获取验证码图片", response = ResultObject.class)
    @GetMapping("/getKaptcha")
    public void getKaptcha(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) throws IOException {

        log.debug("getKaptcha参数:\n{}", params);

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        BufferedImage validateCode = validateHandleService.createValidateCode();
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(validateCode, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }

    }

}




















































