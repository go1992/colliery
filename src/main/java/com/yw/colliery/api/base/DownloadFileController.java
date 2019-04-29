package com.yw.colliery.api.base;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DownloadFileController {
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downloadflie")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String MKname = request.getParameter("MKname");//获取煤矿名称
    	String XTname = request.getParameter("XTname");//煤矿系统名称
    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
    	String ZLtype = request.getParameter("ZLtype");//资料类型
    	String fileName = request.getParameter("fileName");//文件名称
    	System.out.println( "下载文件属性：煤矿名称-->"+MKname+",系统名称-->"+XTname+",资料类型-->"+ZLtype+",文件名称-->"+fileName);
	   //String fileName = "license_freeware.txt";// 设置文件名，根据业务需要替换成要下载的文件名
	        if (fileName != null) {
	        	String[] deptsPath = fileName.split("/");
	        	String fileDept = deptsPath[0];
	        	String fileOwnName = deptsPath[1];
	        	HttpSession session = request.getSession();
	        	@SuppressWarnings("unchecked")
				List<String> detpsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
	        	if(detpsIds.contains(fileDept)) {
	        		//设置文件路径
		            String realPath = MyUtil.FILE_PATH+""+File.separator+"gz-mk-system"+File.separator
//		            		+MKname
		            		+fileDept
		            		+File.separator+XTname+File.separator+XTCDname+File.separator+ZLtype;
		            File file = new File(realPath , fileOwnName);
		            if (file.exists()) {
		                response.setContentType("application/force-download");//设置强制下载不打开
		               try {
						response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileOwnName.getBytes("UTF-8"),"iso-8859-1"));//文件中文名处理
		                byte[] buffer = new byte[1024];
		                FileInputStream fis = null;
		                BufferedInputStream bis = null;
		                try {
		                    fis = new FileInputStream(file);
		                    bis = new BufferedInputStream(fis);
		                    OutputStream os = response.getOutputStream();
		                    int i = bis.read(buffer);
		                    System.out.println("下载文件开始");
		                    while (i != -1) {
		                        os.write(buffer, 0, i);
		                        i = bis.read(buffer);
		                    }
		                    System.out.println("success");
		                } catch (Exception e) {
		                    e.printStackTrace();
		                } finally {
		                    if (bis != null) {
		                        try {
		                            bis.close();
		                        } catch (IOException e) {
		                            e.printStackTrace();
		                        }
		                    }
		                    if (fis != null) {
		                        try {
		                            fis.close();
		                        } catch (IOException e) {
		                            e.printStackTrace();
		                        }
		                    }
		                }
		              } catch (UnsupportedEncodingException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}// 设置文件名
		            }
	        	}
	        	
	            
	        }
	        return "false";
	}
	
    /**
	 * 获取某文件夹下的全部文件，以json格式返回
	 * @param req
	 * @return
	 */
	@RequestMapping("/getfilename")
    public List<Map<String, Map<Integer, String>>> getfilenames(HttpServletRequest request) {
		List<Map<String, Map<Integer, String>>> list = new ArrayList<>();
		Map<Integer, String> filenames = null;
		Map<String, Map<Integer, String>> XTfilenames = null;
		String MKname = request.getParameter("MKname");//获取煤矿名称
    	String XTname = request.getParameter("XTname");//煤矿系统名称
    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
		
		
		
		@SuppressWarnings("unchecked")
		List<String> userDepts = (List<String>)request.getSession().getAttribute(ESessionKey.DeptsIds.key);
		
		for(String each : userDepts) {
			String path = MyUtil.FILE_PATH+""+File.separator+"gz-mk-system"+File.separator+each+File.separator+XTname+File.separator+XTCDname;
			File file = new File(path);
	        // 如果这个路径是文件夹
	        if (file.isDirectory()) {
	            // 获取路径下的所有文件
	            File[] files = file.listFiles();
	            for (int i = 0; i < files.length; i++) {
	                // 如果还是文件夹 递归获取里面的文件 文件夹
	                if (files[i].isDirectory()) {
	                   // System.out.println("菜单：" + files[i].getName());
	                    File filetemp = new File(path+File.separator+files[i].getName());
	                    if (filetemp.isDirectory()) {
	                        // 获取路径下的所有文件
	                        File[] filestemp = filetemp.listFiles();
	                        filenames = new HashMap<>();
	                        for (int j = 0; j < filestemp.length; j++) {
	                        	if (!filestemp[j].isDirectory()) {
	                        		//System.out.println("文件：" + filestemp[j].getName());
	                        		filenames.put(j, each+"/"+filestemp[j].getName());
	                        	}
	                        }
	                        XTfilenames = new HashMap<>();
	                        XTfilenames.put(files[i].getName(), filenames);
	                        list.add(XTfilenames);
	                    }
	                }
	            }
	        } else {
	            System.out.println("文件：" + file.getPath());
	        }
		}
		
        return list;
    }
	
}
