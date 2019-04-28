package com.example.demo.login.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.absys.BaseController;
import com.example.demo.absys.ESessionKey;
import com.example.demo.absys.ResultObject;
import com.example.demo.entity.XtgnQyfj;
import com.example.demo.entity.XtgnYhlb;
import com.example.demo.login.service.ValidateHandleService;
import com.example.demo.login.service.impl.XtgnYhlbServiceImpl;
import com.example.demo.service.IXtgnQyfjService;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.absys.MyUtil.checkNotNull;

/**
 * <p>
 * 系统功能-用户列表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/apiv1/xtgn-yhlb")
@Api(value = "XtgnYhlbController", description = "系统功能-用户列表")
@Slf4j
public class CollieryLogInController extends BaseController<XtgnYhlbServiceImpl, XtgnYhlb> {
    private Logger log = LoggerFactory.getLogger(CollieryLogInController.class);

    @Autowired
    private IXtgnQyfjService iXtgnQyfjService;

    @Autowired
    private ValidateHandleService validateHandleService;

    /*
     * 登陆
     */
    @ApiOperation(value = "登陆", notes = "登陆", response = ResultObject.class)
    @ApiImplicitParams
            ({
                    @ApiImplicitParam(value = "用户名", dataType = "String",
                            name = "username", paramType = "query", required = true, defaultValue = "admin")
                    , @ApiImplicitParam(value = "密码(md5不加盐摘要)", dataType = "String",
                    name = "password", paramType = "query", required = true, defaultValue = "admin")
                    , @ApiImplicitParam(value = "验证码", dataType = "String",
                    name = "captcha", paramType = "query", required = true, defaultValue = "验证码")
            })
    @PostMapping("/login")
    public Object login(@ApiParam(hidden = true) @RequestParam Map<String, Object> params
            , @ApiParam(hidden = true) HttpServletRequest request
    ) {

        if (log.isDebugEnabled()) log.debug("login参数:\n{}", params);
        HttpSession session = request.getSession();
        Map<String, Object> result = new HashMap<String, Object>();
        ResultObject resultObject = new ResultObject(ResultObject.SUCCESS, "2000", "登陆成功", result);

        //请求参数判空
        String paramCapText = MapUtils.getString(params, "captcha", null);
        String username = MapUtils.getString(params, "username", null);
        String password = MapUtils.getString(params, "password", null);
        if (!checkNotNull(paramCapText))
            return resultObject.setMessage("请输入验证码").setCode("2002").setStatus(ResultObject.FAILED);
        if (!checkNotNull(username))
            return resultObject.setMessage("请输入用户名").setCode("2004").setStatus(ResultObject.FAILED);
        if (!checkNotNull(password))
            return resultObject.setMessage("请输入密码").setCode("2006").setStatus(ResultObject.FAILED);

        //验证验证码 用户名 密码
        //验证码 session
        String capText = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 加强安全性 每次调用接口之后刷新验证码  登陆成功删除验证码
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        //检验验证码
        if (!checkNotNull(capText))
            return resultObject.setMessage("请先获取验证码").setCode("2001").setStatus(ResultObject.FAILED);
        //TODO
        //capText
        if (!paramCapText.equals("AK47"))
            return resultObject.setMessage("验证码错误").setCode("2003").setStatus(ResultObject.FAILED);


        //验证用户名密码
        XtgnYhlb user = service.getOne(new QueryWrapper<XtgnYhlb>()
                        .eq(true, "yhmdd", username)
                        //TODO md5摘要应该由前端进行
//				.eq(true, "yhmm",DigestUtils.md5DigestAsHex(password.getBytes()))
                        .eq(true, "yhmm", password)
        );
        if (!checkNotNull(user))
            return resultObject.setMessage("用户名或密码错误").setCode("2005").setStatus(ResultObject.FAILED);
        result.put("user", user);
        session.setAttribute(ESessionKey.User.key, user);
        //获取该用户部门id 及其所有子部门id
        String deptId = user.getSsmk();
        XtgnQyfj userRootDept = iXtgnQyfjService.getById(deptId);
        List<XtgnQyfj> userDepts = null;
        if (checkNotNull(userRootDept)) userDepts = iXtgnQyfjService.list(
                new QueryWrapper<XtgnQyfj>().like(true, "parentids", "," + userRootDept.getId() + ","));
        if (userDepts == null) userDepts = new ArrayList<>();
        if (checkNotNull(userRootDept)) {
            userDepts.add(userRootDept);
            result.put("userDepts", userDepts);
            List<String> deptsIds = null;
            StringBuffer sbf = new StringBuffer("(");
            deptsIds = userDepts.stream().map((obj) -> {
                String str = obj.getId().toString();
                sbf.append("'");
                sbf.append(str);
                sbf.append("',");
                return str;
            }).collect(Collectors.toList());
            sbf.append(")");
            sbf.replace(sbf.lastIndexOf(","), sbf.lastIndexOf(",") + 1, "");

            session.setAttribute(ESessionKey.DeptsIds.key, deptsIds);
            session.setAttribute(ESessionKey.DeptsIdStr.key, sbf.toString());

        }


        return resultObject;
    }

    /*
     * 获取验证码图片
     */
    @ApiOperation(value = "获取验证码图片", response = ResultObject.class)
    @GetMapping("/getKaptcha")
    public void getKaptcha(@ApiParam(hidden = true) @RequestParam Map<String, Object> params
            , @ApiParam(hidden = true) HttpServletRequest request
            , @ApiParam(hidden = true) HttpServletResponse response
    ) throws IOException {

        if (log.isDebugEnabled()) log.debug("getKaptcha参数:\n{}", params);

        HttpSession session = request.getSession();
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




















































