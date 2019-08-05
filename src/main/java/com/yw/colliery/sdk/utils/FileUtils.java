package com.yw.colliery.sdk.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/31 14:24
 * @Description: 文件工具类
 */
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
}
