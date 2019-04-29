package com.yw.colliery.absys;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yw.colliery.entity.XtgnYhlb;

@Controller
public class FileUploadController {

    
    /**
     * 实现文件上传
     * */
    @RequestMapping("fileUpload")
    @ResponseBody 
    public String fileUpload(HttpServletRequest request){
    	String MKname = request.getParameter("MKname");//获取煤矿名称
    	String XTname = request.getParameter("XTname");//煤矿系统名称
    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
    	String ZLtype = request.getParameter("rad1");//资料类型
    	System.out.println( "上传文件属性：煤矿名称-->"+MKname+",系统名称-->"+XTname+",资料类型-->"+ZLtype);
    	MultipartFile file = ((MultipartHttpServletRequest)request).getFile("fileName");
        if(file.isEmpty() || MKname.isEmpty() || XTname.isEmpty() || ZLtype.isEmpty()){//判断文件是否为空
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String path = MyUtil.FILE_PATH+""+File.separator+"gz-mk-system"+File.separator
        		+((XtgnYhlb)request.getSession().getAttribute(ESessionKey.User.key)).getSsmk()
//        		MKname
        		+File.separator+XTname+File.separator+XTCDname+File.separator+ZLtype;
        File destFile = new File(path);
        if(!destFile.exists()){//判断文件目录是否存在
        	/*
        	 * mkdir()  创建此抽象路径名指定的目录。如果父目录不存在则创建不成功。
        	 * mkdirs() 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        	 */
        	boolean i = destFile.mkdirs();
        	System.out.println("创建路径："+destFile.getPath()+","+i);
        }
        File dest = new File(path + File.separator + fileName);
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    
    /**
     * 获取multifile.html页面
     */
    @RequestMapping("multifile")
    public String multifile(){
        return "/multifile";
    }
    
    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="multifileUpload",method=RequestMethod.POST) 

    
    /**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
    public @ResponseBody String multifileUpload(HttpServletRequest request){
        
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        
        if(files.isEmpty()){
            return "false";
        }

        String path = MyUtil.FILE_PATH+"/test" ;
        
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            
            if(file.isEmpty()){
                return "false";
            }else{        
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                } 
            }
        }
        return "true";
    }
    
	/**删除指定目录下的指定文件
	 * @param req
	 * @return
	 */
	@RequestMapping("/delfile")
	@ResponseBody
    public String delfilename(HttpServletRequest request) {
		String rString = "删除失败！！";
		String MKname = request.getParameter("MKname");//获取煤矿名称
    	String XTname = request.getParameter("XTname");//煤矿系统名称
    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
    	String ZLtype = request.getParameter("ZLtype");//资料类型
    	String fileName = request.getParameter("fileName");//文件名称
    	System.out.println( "下载文件属性：煤矿名称-->"+MKname+",系统名称-->"+XTname+",煤矿系统菜单名称-->"+XTCDname+",资料类型-->"+ZLtype+",文件名称-->"+fileName);
	        if (fileName != null) {
	        	String[] deptsPath = fileName.split("/");
	        	String fileDept = deptsPath[0];
	        	String fileOwnName = deptsPath[1];
	        	HttpSession session = request.getSession();
	        	@SuppressWarnings("unchecked")
				List<String> detpsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
	        	if(detpsIds.contains(fileDept)) {
	        		//设置文件路径
		            String Path = MyUtil.FILE_PATH+""+File.separator+"gz-mk-system"+File.separator
		            		+((XtgnYhlb)request.getSession().getAttribute(ESessionKey.User.key)).getSsmk()
		            		+File.separator+XTname+File.separator+XTCDname+File.separator+ZLtype;
		            File file = new File(Path);
		    		// 如果这个路径是文件夹
		            if (file.isDirectory()) {
		            	// 获取路径下的所有文件
		                File[] files = file.listFiles();
		                System.out.println("待删文件：" + fileOwnName);
		                for (int i = 0; i < files.length; i++) {
		                	System.out.println("文件：" + files[i].getName());
		                	if(files[i].getName().equals(fileOwnName)){
		                		System.out.println("删除文件：" + files[i].getName());
		                		files[i].delete();
		                		rString = "删除成功！";
		                		break;
		                	}
		                }
		            }
	        	}
	            
	        }
		return rString;
	}
}
