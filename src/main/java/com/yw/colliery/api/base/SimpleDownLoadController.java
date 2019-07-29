package com.yw.colliery.api.base;

import static com.yw.colliery.sdk.utils.StringUtil.checkNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yw.colliery.entity.securityrisk.AqfxCsxg;
import com.yw.colliery.service.business.impl.AqfxCsxgServiceImpl;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sdownload")
@Api(value = "SimpleDownLoadController", description = "文件导出")
@Slf4j
public class SimpleDownLoadController extends BaseController<AqfxCsxgServiceImpl, AqfxCsxg> {

    //excel文件夹绝对路径
    public static final String simpleUpRootPath;

    static {
        StringBuffer sbf = new StringBuffer("C:");
        sbf.append(File.separator);
        sbf.append("gz-mk-system");
        sbf.append(File.separator);
        sbf.append("excel-export");
        sbf.append(File.separator);
        simpleUpRootPath = sbf.toString();
    }

    @RequestMapping("/export")
    @ResponseBody
    public ResponseEntity<byte[]> export(String fileName) throws IOException {
        log.info("\n文件导出,参数{}", fileName);
        if (!checkNotNull(fileName)) throw new RuntimeException("文件地址错误");
        File file = new File(SimpleDownLoadController.simpleUpRootPath + fileName);
        if (!file.exists()) throw new RuntimeException("文件已被删除或不存在");
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", fileName);// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);// 开始下载
    }


}



















































