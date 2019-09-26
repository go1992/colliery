package com.yw.colliery.sdk.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/31 14:24
 * @Description: 文件工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 遍历所有文件
     * @param rootFile
     * @return
     */
    public static String getFileList(File rootFile,List<String> filterList) {
        File[] files = rootFile.listFiles();
        JSONObject objects = new JSONObject();
        if (files != null) {
            ArrayList<File> fileArrayList = Lists.newArrayList(files);
            fileArrayList.forEach(subFile -> {
                if (subFile.isFile()) {
                    if( CollectionUtils.isEmpty(filterList) || !filterList.contains(subFile.getPath())){
                        objects.put(String.valueOf(objects.size()),subFile.getPath());
                    }
                } else {
                    JSONObject fileList = JSONObject.parseObject(getFileList(subFile,filterList));
                    if (!fileList.isEmpty()){
                        objects.put(subFile.getName(),fileList);
                    }
                }
            });
        }
        return objects.toJSONString();
    }

    /**
     * 获取所有子文件夹
     *
     */
    public static List<String> getSubFileList(File rootFile){
        File[] files = rootFile.listFiles();
        if (files == null){
            return null;
        }
        ArrayList<File> fileArrayList = Lists.newArrayList(files);
        return fileArrayList.stream().filter(f -> f.isDirectory()).map(File::getName).collect(Collectors.toList());
    }

    public static File findTargetFile(String fileName,File rootFile){
        if(rootFile.isFile() && fileName.equals(rootFile.getName())){
            return rootFile;
        }
        File[] files = rootFile.listFiles();
        if (files == null){
            return null;
        }
        for (File subFile: files) {
            if (subFile.isFile() && fileName.equals(subFile.getName())) {
                return subFile;
            } else if (subFile.isDirectory()){
                File targetFile = findTargetFile(fileName, subFile);
                if( targetFile!= null){
                    return targetFile;
                }
            }
        }
        return null;
    }

    public static void writeFile (String path, byte[] bytes) throws IOException {
        Files.write(Paths.get(path),bytes);
    }

    /**
     * 读文件全部内容
     * @param fileName
     * @param charset
     * @return
     */
    public static String read(String fileName, Charset charset) {
        try {
            File file = new File(fileName);
            if (file.isFile()) {
                return new String(Files.readAllBytes(file.toPath()), charset);
            } else {
                String prefix = "/";
                String fullPath = FileUtils.class.getResource(prefix + fileName).toString();
                if (fullPath.startsWith("jar:file:")) {
                    URL url = new URL(fullPath);
                    try (InputStream is = url.openStream();
                         Reader reader = new InputStreamReader(is, charset);
                         BufferedReader br = new BufferedReader(reader)) {
                        return br.lines().collect(Collectors.joining("\n"));
                    }
                } else {
                    return new String(Files.readAllBytes(Paths.get(URI.create(fullPath))), charset);
                }
            }
        } catch (Exception e) {
            log.error("Read [{}] error: {}", fileName, e);
            throw new RuntimeException(e);
        }
    }

    public static String getClassPath(String outPath){
        String path1 = FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(5);
        log.info(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            String path = new File(path1, outPath).getCanonicalPath();
            return path;
        } catch (IOException e) {
            log.error("获取classpath异常",e);
            throw new RuntimeException("获取classpath异常");
        }
    }
}
