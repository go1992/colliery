package com.yw.colliery.service.base.impl;

import com.google.common.collect.Lists;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.dto.FileParamsDTO;
import com.yw.colliery.entity.XtgnYhlb;
import com.yw.colliery.service.base.FileManegerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/28 18:31
 * @Description: 文件管理实现
 */
@Service
@Slf4j
public class FileManegerServiceImpl implements FileManegerService {
    
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Override
    public ResultObject saveFile(MultipartFile file,FileParamsDTO paramsDTO) {
        //判断文件是否为空
        if (file == null || file.isEmpty()
                || StringUtils.isBlank(paramsDTO.getCoalName())
                || StringUtils.isBlank(paramsDTO.getMenuName())
                || StringUtils.isBlank(paramsDTO.getType())) {
            return ResultObject.buildFailResponse("文件参数非法");
        }
        String fileName = file.getOriginalFilename();

        String path = StringUtil.FILE_PATH
                + File.separator + "gz-mk-system"
                + File.separator + ((XtgnYhlb) request.getSession().getAttribute(ESessionKey.User.key)).getSsmk()
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
            return ResultObject.buildSucessResponse("true");
        } catch (Exception e) {
            log.error("文件上传异常");
            return ResultObject.buildFailResponse("文件上传异常");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultObject getFileList(FileParamsDTO paramsDTO) {

        ArrayList<Map<String, Map<Integer, String>>> list = new ArrayList<>();

        List<String> userDeparts = (List<String>)request.getSession().getAttribute(ESessionKey.DeptsIds.key);
        for(String each : userDeparts) {
            String path = StringUtil.FILE_PATH
                    +File.separator+"gz-mk-system"
                    +File.separator+each
                    +File.separator+paramsDTO.getSystemName()
                    +File.separator+paramsDTO.getMenuName();
            File file = new File(path);
            File[] files = file.listFiles();
            // 如果这个路径是文件夹
            if (file.isDirectory() && files != null) {
                ArrayList<File> fileArrayList = Lists.newArrayList(files);
                fileArrayList.forEach(f->{
                    if (f.isDirectory()) {
                        // System.out.println("菜单：" + files[i].getName());
                        File fileTemp = new File(path+File.separator+f.getName());
                        if (fileTemp.isDirectory()) {
                            // 获取路径下的所有文件
                            File[] filestemp = fileTemp.listFiles();
                            HashMap<Integer, String>filenames = new HashMap<>();
                            for (int j = 0; j < filestemp.length; j++) {
                                if (!filestemp[j].isDirectory()) {
                                    //System.out.println("文件：" + filestemp[j].getName());
                                    filenames.put(j, each+"/"+filestemp[j].getName());
                                }
                            }
                            HashMap<String, Map<Integer, String>> XTfilenames = new HashMap<>();
                            XTfilenames.put(f.getName(), filenames);
                            list.add(XTfilenames);
                        }
                    }
                });
                // 获取路径下的所有文件
                for (int i = 0; i < files.length; i++) {
                    // 如果还是文件夹 递归获取里面的文件 文件夹

                }
            } else {
                System.out.println("文件：" + file.getPath());
            }
        }

        return ResultObject.buildSucessResponse(list);
    }

    @Override
    public ResultObject deleteFile(FileParamsDTO paramsDTO) {
        ResultObject result = ResultObject.buildFailResponse("删除失败");
        if (paramsDTO.getFileName() != null) {
            String[] departsPath = paramsDTO.getFileName().split("/");
            String fileDept = departsPath[0];
            String fileOwnName = departsPath[1];
            @SuppressWarnings("unchecked")
            List<String> departsIds = (List<String>) request.getSession().getAttribute(ESessionKey.DeptsIds.key);
            if (departsIds.contains(fileDept)) {
                //设置文件路径
                String path = StringUtil.FILE_PATH
                        + File.separator + "gz-mk-system"
                        + File.separator + ((XtgnYhlb) request.getSession().getAttribute(ESessionKey.User.key)).getSsmk()
                        + File.separator + paramsDTO.getSystemName()
                        + File.separator + paramsDTO.getMenuName()
                        + File.separator + paramsDTO.getType();
                File file = new File(path);
                // 如果这个路径是文件夹
                File[] files = file.listFiles();
                if (file.isDirectory() && files != null) {
                    // 获取路径下的所有文件
                    ArrayList<File> fileList = Lists.newArrayList(files);
                    fileList.forEach(f -> {
                        log.info("待删文件：" + fileOwnName);
                        if (f.getName().equals(fileOwnName)) {
                            log.info("删除文件：{}", f.getName());
                            if (f.delete()) {
                                result.setCode(ResultObject.SUCCESS);
                                result.setMessage("文件删除成功");
                            }
                        }
                    });
                }
            } else {
                return ResultObject.buildFailResponse("文件不属于该部门");
            }

        }
        return result;
    }

    @Override
    public ResultObject downLoadFile(FileParamsDTO paramsDTO) {
        if (paramsDTO.getFileName() != null) {
            String[] deptsPath = paramsDTO.getFileName().split("/");
            String fileDept = deptsPath[0];
            String fileOwnName = deptsPath[1];
            @SuppressWarnings("unchecked")
            List<String> detpsIds = (List<String>) request.getSession().getAttribute(ESessionKey.DeptsIds.key);
            if (detpsIds.contains(fileDept)) {
                //设置文件路径
                String path = StringUtil.FILE_PATH
                        + File.separator + "gz-mk-system"
                        + File.separator + ((XtgnYhlb) request.getSession().getAttribute(ESessionKey.User.key)).getSsmk()
                        + File.separator + paramsDTO.getSystemName()
                        + File.separator + paramsDTO.getMenuName()
                        + File.separator + paramsDTO.getType();
                File file = new File(path, fileOwnName);
                if (file.exists()) {
                    //设置强制下载不打开
                    response.setContentType("application/force-download");
                    try {
                        //文件中文名处理
                        response.addHeader("Content-Disposition"
                                , "attachment;fileName=" + new String(fileOwnName.getBytes("UTF-8")
                                        , "iso-8859-1"));
                        byte[] buffer = new byte[1024];
                        FileInputStream fis = null;
                        BufferedInputStream bis = null;
                        try {
                            fis = new FileInputStream(file);
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
                        log.error("文件下载异常！",e);
                        return ResultObject.buildFailResponse("文件下载异常");
                    }
                }
            }


        }
        return ResultObject.buildFailResponse("下载文件成功");
    }
}
