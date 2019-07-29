package com.yw.colliery.service.base;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.dto.FileParamsDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: xuzhou-013
 * @Date: 2019/7/28 18:28
 * @Description: 文件管理服务
 */
public interface FileManegerService {

    /**
     * 保存文件
     * @param paramsDTO 文件参数
     * @param file 文件
     * @return
     */
    ResultObject saveFile(MultipartFile file, FileParamsDTO paramsDTO);

    /**
     * 获取文件列表
     * @return
     */
    ResultObject getFileList(FileParamsDTO paramsDTO);

    /**
     * 删除文件
     * @param paramsDTO 文件参数
     * @return
     */
    ResultObject deleteFile(FileParamsDTO paramsDTO);

    /**
     * 下载文件
     * @param paramsDTO
     * @return
     */
    ResultObject downLoadFile(FileParamsDTO paramsDTO);



}
