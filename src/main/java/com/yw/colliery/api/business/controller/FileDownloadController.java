//package com.yw.colliery.api.business.controller;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.yw.colliery.api.base.ESessionKey;
//import com.yw.colliery.api.base.ResultObject;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//
////@Api(value = "FileUploadController", description = "文件列表获取与文件下载")
////@RequestMapping("/apiv1/file")
////@RestController
//public class FileDownloadController {
//
//	public static final String FILE_ROOT_PATH = "C:"+File.separator+"gz-mk-system"+File.separator;
//
//	/**
//	 * 文件下载
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//	@ApiOperation(value = "单文件下载",response=ResultObject.class)
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
//	@PostMapping("/downloadflie")
//	public void downloadFile(@ApiParam(hidden=true)HttpServletRequest request, @ApiParam(hidden=true)HttpServletResponse response) throws Exception {
////		ResultObject resultObj = new ResultObject(ResultObject.FAILED,"1003","下载失败",null);
////		String MKname = request.getParameter("MKname");//获取煤矿名称
//    	String XTname = request.getParameter("XTname");//煤矿系统名称
//    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
////    	String ZLtype = request.getParameter("ZLtype");//资料类型
//    	String fileName = request.getParameter("fileName");//文件名称
////    	System.out.println( "下载文件属性：煤矿名称-->"+MKname+",系统名称-->"+XTname+",资料类型-->"+ZLtype+",文件名称-->"+fileName);
//	   //String fileName = "license_freeware.txt";// 设置文件名，根据业务需要替换成要下载的文件名
//        if (fileName != null) {
//        	String[] deptsPath = fileName.split("/");
//        	String fileDept = deptsPath[0];
//        	String fileOwnName = deptsPath[1];
//        	HttpSession session = request.getSession();
//        	@SuppressWarnings("unchecked")
//			List<String> detpsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
//        	if(detpsIds.contains(fileDept)) {
//                //设置文件路径
//                String realPath = FILE_ROOT_PATH+XTname+File.separator+XTCDname+File.separator
////    	            		+ZLtype
//                		+fileDept+File.separator;
//                File file = new File(realPath , fileOwnName);
//                if (file.exists()) {
//                    response.setContentType("application/force-download");//设置强制下载不打开
//    				response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));//文件中文名处理
//                    byte[] buffer = new byte[1024];
//                    FileInputStream fis = null;
//                    BufferedInputStream bis = null;
//                    try {
//                        fis = new FileInputStream(file);
//                        bis = new BufferedInputStream(fis);
//                        OutputStream os = response.getOutputStream();
//                        int i = bis.read(buffer);
////    	                    System.out.println("下载文件开始");
//                        while (i != -1) {
//                            os.write(buffer, 0, i);
//                            i = bis.read(buffer);
//                        }
////    	                    System.out.println("success");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (bis != null) {
//                            try {
//                                bis.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        if (fis != null) {
//                            try {
//                                fis.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//        	}
//        }
////        return resultObj;
//	}
//
//    /**
//	 * 获取某文件夹下的全部文件，以json格式返回
//	 * @param req
//	 * @return
//	 */
//	@ApiOperation(value = "文件列表获取",response=ResultObject.class)
//	@ApiImplicitParams
//	({
//
//		@ApiImplicitParam(value="系统名称中文首字母",name="XTname",dataType="String"
//				,paramType="query",required=true,defaultValue="xtgn")
//		,@ApiImplicitParam(value="菜单名称中文首字母(若有多级 - 以英文短横线分割)",name="XTCDname",dataType="String"
//				,paramType="query",required=true,defaultValue="yhlb")
//	})
//	@PostMapping("/getfilename")
//    public ResultObject getfilenames(@ApiParam(hidden=true)HttpServletRequest request) {
//		List<String> list = new ArrayList<>();
////		String MKname = request.getParameter("MKname");//获取煤矿名称
//    	String XTname = request.getParameter("XTname");//煤矿系统名称
//    	String XTCDname = request.getParameter("XTCDname");//煤矿系统菜单名称
//		@SuppressWarnings("unchecked")
//		List<String> userDepts = (List<String>)request.getSession().getAttribute(ESessionKey.DeptsIds.key);
//		for(String each : userDepts) {
//			String path = FILE_ROOT_PATH+XTname+File.separator+XTCDname+File.separator+each;
//			File file = new File(path);
//            // 获取路径下的所有文件
//			if(file.exists()){
//	            File[] files = file.listFiles();
//	            for (File eachFile : files) {
//	                list.add(each+"/"+eachFile.getName());
//	            }
//			}
//
//	}
//		return new ResultObject(ResultObject.SUCCESS,"1003","接口调用成",list);
////		return list;
//    }
//
//}
