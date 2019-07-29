package com.yw.colliery.api.base;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.yw.colliery.dto.FileParamsDTO;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.base.FileManegerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yw.colliery.entity.XtgnYhlb;

/**
 * @author xuzhou
 */
@RestController
public class FileManagerController {

    private Logger logger = LoggerFactory.getLogger(FileManagerController.class);

    @Autowired
    private FileManegerService fileManegerService;

    /**
     * 实现文件上传
     */
    @PostMapping("fileUpload")
    @AuthModule(authId = AuthConstant.Module.FILE_MODULE)
    public ResultObject fileUpload(HttpServletRequest request) {
        FileParamsDTO fileDTO = parseFileDTO(request);
        logger.info("上传文件属性：煤矿名称:[{}],系统名称:[{}],资料类型:[{}]" + fileDTO.getCoalName(), fileDTO.getMenuName(), fileDTO.getType());
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("fileName");
        return fileManegerService.saveFile(file,fileDTO);
    }

    /**
     * 删除指定目录下的指定文件
     *
     * @param request
     * @return
     */
    @PostMapping("/delfile")
    @AuthModule(authId = AuthConstant.Module.FILE_MODULE)
    public ResultObject delFileName(HttpServletRequest request) {
        FileParamsDTO fileParamsDTO = parseFileDTO(request);
        logger.info("删除文件参数:{},{},{},{},{}", fileParamsDTO.getCoalName()
                , fileParamsDTO.getMenuName(), fileParamsDTO.getSystemName()
                , fileParamsDTO.getType(), fileParamsDTO.getFileName());
        return fileManegerService.deleteFile(fileParamsDTO);
    }

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/downloadflie")
    @AuthModule(authId = AuthConstant.Module.FILE_MODULE)
    public ResultObject downloadFile(HttpServletRequest request, HttpServletResponse response) {
        FileParamsDTO fileParamsDTO = parseFileDTO(request);
        //文件名称
        String fileName = request.getParameter("fileName");
        logger.info("下载文件参数:{},{},{},{},{}", fileParamsDTO.getCoalName()
                , fileParamsDTO.getMenuName(), fileParamsDTO.getSystemName(), fileParamsDTO.getType(), fileName);
        return fileManegerService.downLoadFile(fileParamsDTO);
    }

    /**
     * 获取某文件夹下的全部文件，以json格式返回
     * @param
     * @return
     */
    @PostMapping("/getfilename")
    @AuthModule(authId = AuthConstant.Module.FILE_MODULE)
    public ResultObject getfilenames(HttpServletRequest request) {

        FileParamsDTO fileParamsDTO = parseFileDTO(request);
        return fileManegerService.getFileList(fileParamsDTO);



    }
    private FileParamsDTO parseFileDTO(HttpServletRequest request) {
        FileParamsDTO fileParamsDTO = new FileParamsDTO();
        //煤矿名称
        fileParamsDTO.setCoalName(request.getParameter("MKname"));
        //煤矿系统名称
        fileParamsDTO.setSystemName(request.getParameter("XTname"));
        //煤矿系统菜单名称
        fileParamsDTO.setMenuName(request.getParameter("XTCDname"));
        //资料类型
        fileParamsDTO.setType(Optional.ofNullable(request.getParameter("ZLtype")).orElse(request.getParameter("rad1")));
        //获取文件名
        fileParamsDTO.setFileName(Optional.ofNullable(request.getParameter("fileName")).orElse(""));

        return fileParamsDTO;
    }
}
