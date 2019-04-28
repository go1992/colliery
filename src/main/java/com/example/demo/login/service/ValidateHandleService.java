package com.example.demo.login.service;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 验证码处理服务
 * @author: xuzhou-lhq
 * @create: 2019-04-28 16:45
 **/
@Service
public class ValidateHandleService {

    /**缓存生成的验证码，注意校验完后删除，防止map过大,key是sessionId*/
    private static final ConcurrentHashMap<String,String> VALIDATE_MAP = new ConcurrentHashMap<>();

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private HttpServletRequest request;


    /**
     * 删除缓存的验证码
     * @param sessionId
     */
    public void dellValidate(String sessionId){
        VALIDATE_MAP.remove(sessionId);
    }

    /**
     * 获取生成的验证码
     * @param sessionId
     * @return
     */
    public String getValidate(String sessionId){
        return VALIDATE_MAP.get(sessionId);
    }

    /**
     * 保存生成的验证码
     * @param sessionId
     * @param validateCode
     * @return
     */
    private String saveValidate(String sessionId, String validateCode){
        return VALIDATE_MAP.put(sessionId,validateCode);
    }

    /**
     * 创建一个新的验证码
     * @return
     */
    public BufferedImage createValidateCode(){
        // 生成验证码文本
        String capText = captchaProducer.createText();
        HttpSession session = request.getSession();
        saveValidate(session.getId(),capText);
        // 利用生成的字符串构建图片
        return captchaProducer.createImage(capText);
    }
}
