//package com.yw.colliery.api.business.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.yw.colliery.api.base.ESessionKey;
//import com.yw.colliery.api.base.ResultObject;
//import com.yw.colliery.entity.XtgnYhlb;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//
//
////@Api(value = "FileUploadController", description = "文件上传与文件删除")
////@RequestMapping("/apiv1/file")
////@RestController
//public class FileUploadController {
//
//    /**
//     * 实现文件上传
//     * @throws IOException
//     * @throws IllegalStateException
//     * */
//	@ApiOperation(value = "单文件上传",response=ResultObject.class)
//	@ApiImplicitParams
//	({
//
////		@ApiImplicitParam(value="部门",name="MKname",dataType="String"
////				,paramType="query",required=false,defaultValue="1")
////
////		,
//		@ApiImplicitParam(value="系统名称中文首字母",name="XTname",dataType="String"
//				,paramType="query",required=true,defaultValue="xtgn")
//		,@ApiImplicitParam(value="菜单名称中文首字母(若有多级 - 以英文短横线分割)",name="XTCDname",dataType="String"
//				,paramType="query",required=true,defaultValue="yhlb")
//	})
//    @PostMapping("fileUpload")
//    @ResponseBody
//    public ResultObject fileUpload( @ApiParam(hidden=true) HttpServletRequest request
//    		,@ApiParam(value="上传的文件",required=true) MultipartFile file
//    		) throws Exception{
////    	String MKname = request.getParameter("MKname");//获取煤矿名称
//    	String XTname = request.getParameter("XTname");//煤矿系统名称
//    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
////    	String ZLtype = request.getParameter("rad1");//资料类型
////    	System.out.println( "上传文件属性：煤矿名称-->"+MKname+",系统名称-->"+XTname+",资料类型-->"+ZLtype);
////    	MultipartFile file = ((MultipartHttpServletRequest)request).getFile("fileName");
//        if(file.isEmpty()  || XTname.isEmpty() ){//判断文件是否为空 || ZLtype.isEmpty() || MKname.isEmpty()
//        	return new ResultObject(ResultObject.FAILED,"1003","参数错误",null);
//        }
//        String fileName = file.getOriginalFilename().replaceAll("/", "");
//        file.getSize();
////        int size = (int)
////        System.out.println(fileName + "-->" + size);
//        String path = FileDownloadController.FILE_ROOT_PATH+XTname+File.separator+XTCDname+File.separator
////        		+ZLtype
//        		+((XtgnYhlb)request.getSession().getAttribute(ESessionKey.User.key)).getSsmk()+File.separator;
//        File destFile = new File(path);
//        if(!destFile.exists()){//判断文件目录是否存在
//        	/*
//        	 * mkdir()  创建此抽象路径名指定的目录。如果父目录不存在则创建不成功。
//        	 * mkdirs() 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
//        	 */
//        	destFile.mkdirs();
////        	boolean i =
////        	System.out.println("创建路径："+destFile.getPath()+","+i);
//        }
//        File dest = new File(path + File.separator + fileName);
//        file.transferTo(dest); //保存文件
//        return new ResultObject(ResultObject.SUCCESS,"1003","上传成功",null);
//    }
//
//	/**删除指定目录下的指定文件
//	 * @param req
//	 * @return
//	 */
//	@ApiOperation(value = "单文件删除",response=ResultObject.class)
//	@ApiImplicitParams
//	({
//
//		@ApiImplicitParam(value="系统名称中文首字母",name="XTname",dataType="String"
//				,paramType="query",required=true,defaultValue="xtgn")
//		,@ApiImplicitParam(value="菜单名称中文首字母(若有多级 - 以英文短横线分割)",name="XTCDname",dataType="String"
//				,paramType="query",required=true,defaultValue="yhlb")
//		,@ApiImplicitParam(value="文件名称(由用户在文件列表中选中取得)",name="fileName",dataType="String"
//				,paramType="query",required=true,defaultValue="1/testFile.txt")
//	})
//	@PostMapping("/delfile")
//    public ResultObject delfilename(@ApiParam(hidden=true) HttpServletRequest request) {
//		ResultObject resultObj = new ResultObject(ResultObject.FAILED,"1003","删除失败",null);
////		String MKname = request.getParameter("MKname");//获取煤矿名称
//    	String XTname = request.getParameter("XTname");//煤矿系统名称
//    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
////    	String ZLtype = request.getParameter("ZLtype");//资料类型
//    	String fileName = request.getParameter("fileName");//文件名称
////    	System.out.println( "下载文件属性：煤矿名称-->"+MKname+",系统名称-->"+XTname+",煤矿系统菜单名称-->"+XTCDname+",资料类型-->"+ZLtype+",文件名称-->"+fileName);
//	        if (fileName != null) {
//	        	String[] deptsPath = fileName.split("/");
//	        	String fileDept = deptsPath[0];
//	        	String fileOwnName = deptsPath[1];
//	        	HttpSession session = request.getSession();
//	        	@SuppressWarnings("unchecked")
//				List<String> detpsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
//	        	if(detpsIds.contains(fileDept)) {
//	        		//设置文件路径
//		            String Path = FileDownloadController.FILE_ROOT_PATH+XTname+File.separator+XTCDname+File.separator
////		            		+ZLtype
//		            		+fileDept+File.separator;
//		            File file = new File(Path);
//		    		// 如果这个路径是文件夹
//		            if (file.isDirectory()) {
//		            	// 获取路径下的所有文件
//		                File[] files = file.listFiles();
////		                System.out.println("待删文件：" + fileName);
//		                for (int i = 0; i < files.length; i++) {
////		                	System.out.println("文件：" + files[i].getName());
//		                	if(files[i].getName().equals(fileOwnName)){
////		                		System.out.println("删除文件：" + files[i].getName());
//		                		files[i].delete();
//		                		resultObj.setStatus(ResultObject.SUCCESS).setMessage("删除成功");
//		                		break;
//		                	}
//		                }
//		            }
//	        	}else {
//	        		return resultObj.setMessage("权限不足");
//	        	}
//
//
//	        }
//	        return resultObj;
//	}
//}
