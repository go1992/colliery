package com.yw.colliery.api.base;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yw.colliery.dto.FileParamsDTO;
import com.yw.colliery.dto.FileWhiteListRequestDTO;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.file.FileWhiteListEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.aop.log.LogRecord;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.base.FileManegerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE,level = AuthConstant.Level.LOW)
    public ResultObject fileUpload(HttpServletRequest request) {
        FileParamsDTO fileDTO = parseFileDTO(request);
        logger.info("上传文件属性：煤矿名称:[{}],系统名称:[{}],资料类型:[{}]" + fileDTO.getCoalName(), fileDTO.getMenuName(), fileDTO.getType());
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("fileName");
        return fileManegerService.saveFile(file, fileDTO);
    }

    /**
     * 删除指定目录下的指定文件
     *
     * @param request
     * @return
     */
    @PostMapping("/delfile")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
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
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
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
     *
     * @param
     * @return
     */
    @PostMapping("/getfilename")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE,level = AuthConstant.Level.LOW)
    public ResultObject getFileNames(HttpServletRequest request) {
        FileParamsDTO fileParamsDTO = parseFileDTO(request);
        return fileManegerService.getFileList(fileParamsDTO);
    }

    /**
     * 保存文件白名单
     * @param dtos
     * @return
     */
    @PostMapping("/save/file/whiteList")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE,level = AuthConstant.Level.LOW,fileAuth = true)
    public ResultObject saveHideFileList(@RequestBody List<FileWhiteListRequestDTO> dtos){
        try {

            return fileManegerService.saveHideFileList(dtos);
        } catch (Exception e) {
            logger.error("保存隐藏文件列表异常",e);
            return ResultObject.buildFailResponse("保存隐藏文件列表异常！");
        }
    }


    /**
     * 保存文件白名单
     * @param
     * @return
     */
    @PostMapping("/get/file/whiteList")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE,level = AuthConstant.Level.LOW,fileAuth = true)
    public ResultObject getHideFileList(@RequestBody FileWhiteListRequestDTO dto){
        try {
            return fileManegerService.getHideFileList(dto);
        } catch (Exception e) {
            logger.error("获取隐藏文件列表异常",e);
            return ResultObject.buildFailResponse("获取隐藏文件列表异常！");
        }
    }

    /**
     * 移除文件白名单
     * @param
     * @return
     */
    @PostMapping("/delete/file/whiteList")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE,level = AuthConstant.Level.LOW,fileAuth = true)
    public ResultObject removeHideFileList(@RequestBody List<String> ids){
        try {

            return fileManegerService.deleteHideFileList(ids);
        } catch (Exception e) {
            logger.error("删除隐藏文件列表异常",e);
            return ResultObject.buildFailResponse("删除隐藏文件列表异常！");
        }
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
