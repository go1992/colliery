package com.yw.colliery.service.base.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.FileWhiteListRequestDTO;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.file.FileWhiteListEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.utils.FileUtils;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.ResponseUtils;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.dto.FileParamsDTO;
import com.yw.colliery.service.base.FileManegerService;
import com.yw.colliery.service.base.FileWhiteListService;
import com.yw.colliery.service.depart.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/28 18:31
 * @Description: 文件管理实现
 */
@Service
@Slf4j
public class FileManagerServiceImpl implements FileManegerService {

    @Autowired
    private FileWhiteListService fileWhiteListService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private HttpServletResponse response;

    /**
     * 文件管理权限开启标记
     */
    private static final String FILE_MANAGER_AUTH = "true";

    @Override
    public ResultObject saveFile(MultipartFile file, FileParamsDTO paramsDTO) {

        UserRelationEntity user = LoginSessionUtils.getUser();
        //获取部门信息
        DepartmentEntity departmentEntity = departmentService.selectById(user.getSafetyUser().getDepartId());

        //判断文件是否为空
        if (file == null || file.isEmpty()
                || StringUtils.isBlank(paramsDTO.getCoalName())
                || StringUtils.isBlank(paramsDTO.getMenuName())
                || StringUtils.isBlank(paramsDTO.getType())) {
            return ResultObject.buildFailResponse("文件参数非法");
        }
        String fileName = file.getOriginalFilename();

        String path = StringUtil.FILE_PATH
                + File.separator + paramsDTO.getCoalName()
                + File.separator + departmentEntity.getDepartName()
                + File.separator + paramsDTO.getSystemName()
                + File.separator + paramsDTO.getMenuName()
                + File.separator + paramsDTO.getType();
        File destFile = new File(path);
        //判断文件目录是否存在
        if (!destFile.exists()) {
            boolean mkdirs = destFile.mkdirs();
            log.info("创建路径：" + destFile.getPath() + ",");
            if (!mkdirs) {
                return ResultObject.buildFailResponse("创建文件路径失败");
            }

        }
        //保存文件
        File dest = new File(path + File.separator + fileName);
        try {
            file.transferTo(dest);
            return ResultObject.buildSuccessResponse("true");
        } catch (Exception e) {
            log.error("文件上传异常");
            return ResultObject.buildFailResponse("文件上传异常");
        }
    }

    @Override
    public ResultObject getFileList(FileParamsDTO paramsDTO) {

        UserRelationEntity user = LoginSessionUtils.getUser();
        //获取部门信息
        DepartmentEntity departmentEntity = departmentService.selectById(user.getSafetyUser().getDepartId());
        JSONArray objects = new JSONArray();


        // 有文件管理权限的用户获取所有文件
        if (FILE_MANAGER_AUTH.equals(user.getSafetyUser().getFileAuth())) {
            String path = StringUtil.FILE_PATH + File.separator + paramsDTO.getCoalName();
            //获取所有部门的文件夹
            List<String> subFileList = FileUtils.getSubFileList(new File(path));
            if (!CollectionUtils.isEmpty(subFileList)) {
                subFileList.forEach(s -> {
                    String fileList = getSingleDepartFileList(s, paramsDTO,null);
                    if (!fileList.isEmpty()) {
                        //去掉路径中的部分信息
                        objects.add(JSONObject.parseObject(dealFilePath(fileList,paramsDTO)));
                    }
                });
            }

        } else {
            PageBean<FileWhiteListEntity> allFileWhiteList = fileWhiteListService.getAllFileWhiteList(new FileWhiteListEntity(), 0, 0);
            //获取文件白名单
            List<String> whiteList = allFileWhiteList.getList().stream().map(f ->
                    StringUtil.FILE_PATH
                            + File.separator + f.getCoal()
                            + File.separator + f.getDepart()
                            + File.separator + f.getSystem()
                            + File.separator + f.getMenu()
                            + File.separator + f.getType()
                            + File.separator + f.getFileName()
            ).collect(Collectors.toList());
            String fileList = getSingleDepartFileList(departmentEntity.getDepartName(), paramsDTO,whiteList);
            if (!fileList.isEmpty()) {
                objects.add(JSONObject.parseObject(dealFilePath(fileList,paramsDTO)));
            }
        }
        return ResultObject.buildSuccessResponse(objects);
    }

    @Override
    public ResultObject deleteFile(FileParamsDTO paramsDTO) {

        if (paramsDTO.getFileName() != null) {
            String[] split = paramsDTO.getFileName().split("/");
            if(split.length != 3){
                return ResultObject.buildFailResponse("下载文件参数不合法");
            }
            File targetFile = getTargetFile(split[2],split[0],paramsDTO);
            if (targetFile != null) {
                if (targetFile.delete()) {
                    return ResultObject.buildSuccessResponse("删除成功");
                } else {
                    return ResultObject.buildFailResponse("文件删除失败");
                }
            } else {
                return ResultObject.buildFailResponse("没有找到文件信息");
            }
        }
        return ResultObject.buildFailResponse("文件名不能为空");
    }

    @Override
    public ResultObject downLoadFile(FileParamsDTO paramsDTO) {
        if (paramsDTO.getFileName() != null) {
            //设置文件路径
            String[] split = paramsDTO.getFileName().split("/");
            if(split.length != 3){
                return ResultObject.buildFailResponse("下载文件参数不合法");
            }
            File targetFile = getTargetFile(split[2],split[0],paramsDTO);
            if (targetFile != null) {
                //设置强制下载不打开
                response.setContentType("application/force-download");
                try {
                    //文件中文名处理
                    response.addHeader("Content-Disposition"
                            , "attachment;fileName=" + new String(paramsDTO.getFileName().getBytes("UTF-8"), "iso-8859-1"));
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;
                    try {
                        fis = new FileInputStream(targetFile);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();
                        int i = bis.read(buffer);
                        log.info("下载文件开始");
                        while (i != -1) {
                            os.write(buffer, 0, i);
                            i = bis.read(buffer);
                        }
                    } finally {
                        try {
                            if (bis != null) {
                                bis.close();
                            }
                            if (fis != null) {
                                fis.close();
                            }
                        } catch (IOException e) {
                            log.error("文件关闭异常");
                        }
                    }
                } catch (Exception e) {
                    log.error("文件下载异常！", e);
                    return ResultObject.buildFailResponse("文件下载异常");
                }
            } else {
                return ResultObject.buildFailResponse("文件不存在");
            }

        }
        return ResultObject.buildFailResponse("下载文件成功");
    }

    @Override
    public ResultObject saveHideFileList(List<FileWhiteListRequestDTO> fileList) {
        List<FileWhiteListEntity> entities = transWhiteListEntity(fileList);
        Integer integer = fileWhiteListService.saveFileWhiteList(entities);
        if (integer > 0) {
            return ResultObject.buildSuccessResponse("保存隐藏文件成功");
        }
        return ResultObject.buildFailResponse("隐藏文件失败！");
    }

    @Override
    public ResultObject getHideFileList() {
        PageBean<FileWhiteListEntity> allFileWhiteList = fileWhiteListService.getAllFileWhiteList(new FileWhiteListEntity(), 0, 0);
        return ResultObject.buildSuccessResponse(ResponseUtils.wrapResponse(allFileWhiteList));
    }

    @Override
    public ResultObject deleteHideFileList(List<String> ids) {
        fileWhiteListService.deleteFileWhiteList(ids);
        return ResultObject.buildSuccessMessageResponse("删除白名单成功");
    }

    private String getSingleDepartFileList(String departName, FileParamsDTO paramsDTO, List<String> filterList) {
        String departPath = StringUtil.FILE_PATH
                + File.separator + paramsDTO.getCoalName()
                + File.separator + departName
                + File.separator + paramsDTO.getSystemName()
                + File.separator + paramsDTO.getMenuName();
        return FileUtils.getFileList(new File(departPath),filterList);
    }

    private File getTargetFile(String fileName,String departName,FileParamsDTO paramsDTO) {

        String path = StringUtil.FILE_PATH
                + File.separator + paramsDTO.getCoalName()
                + File.separator + departName
                + File.separator + paramsDTO.getSystemName()
                + File.separator + paramsDTO.getMenuName()
                + File.separator + paramsDTO.getType();
        File file = new File(path);
        return FileUtils.findTargetFile(fileName, file);
    }

    private String dealFilePath(String filePath, FileParamsDTO paramDTO){
        return filePath.replaceAll(StringUtil.FILE_PATH+StringUtil.REG_DOUBLE_FILE_SEPARATOR,"")
                .replaceAll(paramDTO.getCoalName()+StringUtil.REG_DOUBLE_FILE_SEPARATOR,"")
                .replaceAll(paramDTO.getSystemName()+StringUtil.REG_DOUBLE_FILE_SEPARATOR,"")
                .replaceAll(paramDTO.getMenuName()+StringUtil.REG_DOUBLE_FILE_SEPARATOR,"")
                .replaceAll(StringUtil.REG_DOUBLE_FILE_SEPARATOR,"/");
    }

    private List<FileWhiteListEntity> transWhiteListEntity(List<FileWhiteListRequestDTO> requestDTO){
        return requestDTO.stream().map(dto->{
            FileWhiteListEntity fileWhiteListEntity = new FileWhiteListEntity();
            fileWhiteListEntity.setCoal(dto.getCoalName());
            fileWhiteListEntity.setSystem(dto.getSystemName());
            fileWhiteListEntity.setMenu(dto.getMenuName());
            fileWhiteListEntity.setType(dto.getType());
            String[] split = dto.getFileName().split("/");
            fileWhiteListEntity.setDepart(split[0]);
            fileWhiteListEntity.setFileName(split[2]);
            return fileWhiteListEntity;
        }).collect(Collectors.toList());
    }
}
