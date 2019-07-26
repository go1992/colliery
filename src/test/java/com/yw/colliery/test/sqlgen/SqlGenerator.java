package com.yw.colliery.test.sqlgen;
import java.util.Scanner;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * @Title:
 * @ClassName: pinyin.PinYinUtil.java
 * @Description: pinyin4j的Maven坐标
 *
 *   <dependency>
 *   <groupId>com.belerweb</groupId>
 *   <artifactId>pinyin4j</artifactId>
 *   <version>2.5.0</version>
 *   </dependency>
 *
 * @Copyright 2016-2017  - Powered By 研发中心
 * @author: FLY
 * @date:  2017-11-13 17:09
 * @version V1.0
 */
public class SqlGenerator {

    /**
     * @Title: 获取中文串拼音首字母，英文字符不变
     * @methodName:  getFirstSpell
     * @param chinese 汉字串
     * @return java.lang.String 中文拼音首字母
     * @Description:
     *
     * @author: FLY
     * @date:  2017-11-13 17:13
     */
    public static String getFirstSpell(String chinese) {


            StringBuffer pybf = new StringBuffer(); 
            char[] arr = chinese.toCharArray(); 
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
            for (int i = 0; i < arr.length; i++) { 
                    if (arr[i] > 128) { 
                            try { 
                                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat); 
                                    if (temp != null) { 
                                            pybf.append(temp[0].charAt(0)); 
                                    } 
                            } catch (BadHanyuPinyinOutputFormatCombination e) { 
                                    e.printStackTrace(); 
                            } 
                    } else { 
                            pybf.append(arr[i]); 
                    } 
            } 
            return pybf.toString().replaceAll("-", "_"); 
    } 

    /**
     * @Title:  获取中文串拼音，英文字符不变
     * @methodName:  getFullSpell
     * @param chinese 中文字符串
     * @return java.lang.String  中文拼音
     * @Description:
     *
     * @author: FLY
     * @date:  2017-11-13 17:15
     */
    public static String getFullSpell(String chinese) {

            StringBuffer pybf = new StringBuffer(); 
            char[] arr = chinese.toCharArray(); 
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
            for (int i = 0; i < arr.length; i++) { 
                    if (arr[i] > 128) { 
                            try { 
                                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]); 
                            } catch (BadHanyuPinyinOutputFormatCombination e) { 
                                    e.printStackTrace(); 
                            } 
                    } else { 
                            pybf.append(arr[i]); 
                    } 
            } 
            return pybf.toString(); 
    }

    public static void main(String[] args) {
    	System.out.println("表名:");
    	Scanner scanner = new Scanner(System.in);
    	String tableName = scanner.next();
//    	System.out.println(tableName); 
    	System.out.println("字段名(以逗号隔开):");
    	String fildsStr = scanner.next();
//    	System.out.println(fildsStr);
    	
    	String enTableName = getFirstSpell(tableName);
    	String enFiledsStr = getFirstSpell(fildsStr);
//    	System.out.println(enTableName);
//    	System.out.println(enFiledsStr);
    	
    	String[] chArr = fildsStr.split(",");
    	String[] enArr = enFiledsStr.split(",");
//    	System.out.println(chArr.length+":"+enArr.length);
    	StringBuffer sqlTemp = new StringBuffer();
    	for(int i = 0; i < enArr.length; i++) {
    		String chStr = chArr[i];
    		String enStr = enArr[i];
    		//字段长度不够3补充dd;
    		if(enStr.length()<=3)enStr=enStr+"dd";
    		sqlTemp.append(""+/**~!{*/""
+ "`" +((enStr))+ "` varchar(255) DEFAULT NULL COMMENT '" +((chStr))+ "',"
    		+ "\r\n"/**}*/);
    	}
    	String doSql = ""+/**~!{*/""
    		+ "CREATE TABLE `" +((enTableName))+ "` ("
				+ "\r\n`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',"
				+ "\r\n`pcrq` timestamp NULL DEFAULT NULL COMMENT '排查日期',"
				+ "\r\n`ssmk` varchar(255) DEFAULT NULL COMMENT '数据所属煤矿',"
				+ "\r\n`ylzda` varchar(255) DEFAULT NULL COMMENT '预留字段a',"
				+ "\r\n`ylzdb` varchar(255) DEFAULT NULL COMMENT '预留字段b',"
				+ "\r\n`ylzdc` varchar(255) DEFAULT NULL COMMENT '预留字段c',"
				+ "\r\n" +((sqlTemp.toString()))+ ""
    		+ "\r\n	PRIMARY KEY (`id`)"
			+ "\r\n) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='" +((tableName))+ "';"
    	+ "\r\n"/**}*/;
    	System.out.println("\n"+doSql);
    	
//        System.out.println(getFirstSpell("治安管理-风险管控-事故统计"));// nh
//
//        System.out.println(getFullSpell("你好"));//nihao
    }
}











































