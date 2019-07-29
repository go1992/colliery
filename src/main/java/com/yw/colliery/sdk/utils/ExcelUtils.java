package com.yw.colliery.sdk.utils;

import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.entity.securityrisk.AqfxNdfx;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xuzhou-013
 * @Date: 2019/7/15 10:43
 * @Description: excel文件读取
 */
public class ExcelUtils {

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("文件不是Excel文件");
        }
        return workbook;
    }

    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static <T> List<T> getDataByExcel(InputStream in, String fileName, Class<T> tClass) throws Exception {
        List<T> list = new ArrayList<>();
        ArrayList<String> keyList = new ArrayList<>();

        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(j == row.getFirstCellNum()){
                    row.forEach(r->keyList.add(r.getStringCellValue()));
                    continue;
                }
                JSONObject rowMap = new JSONObject();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    Cell dataCell = row.getCell(y);
                    if(StringUtils.isNotBlank(dataCell.getStringCellValue())){
                        rowMap.put(keyList.get(y),dataCell.getStringCellValue());
                    }
                }
                if(!rowMap.isEmpty()){
                    T t = JSONObject.toJavaObject(rowMap,tClass);
                    list.add(t);
                }
            }
        }
        work.close();
        return list;
    }

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream(new File("D:/test.xlsx"));
            List<AqfxNdfx> bankListByExcel = getDataByExcel(in, "test.xlsx", AqfxNdfx.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
