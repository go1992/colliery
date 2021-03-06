package com.yw.colliery.sdk.utils;


import com.google.common.base.CaseFormat;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xuzhou
 *
 */
public class StringUtil {

	public static final String FILE_PATH = "D:";

	public static final String REG_FILE_SEPARATOR = "\\\\";

	public static final String REG_DOUBLE_FILE_SEPARATOR = "\\\\\\\\";

	public static final String[] STATIC_MATCH_END= {".html",".css",".js",".ioc",".png"};

	public static final String[] STATIC_MATCH_START= {"/font","/css","/html","/img","/js","/glng"};
	/**
	 * 判空
	 *
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkNotNull(Object...objects ) {
		if(null == objects) {
			return false;
		}
		for(Object obj : objects) {
			if(null == obj  ) {
				return false;
			} else if(obj instanceof String) {
				String str = obj.toString();
				if(str.trim().isEmpty()) { return false;}
			} else if(obj instanceof Set ) {
				Set set = (Set)obj;
				if(set.isEmpty()) {return false;}
			} else if(obj instanceof List) {
				List list = (List)obj;
				if(list.isEmpty()) {return false;}
			} else if(obj instanceof Map) {
				Map map = (Map)obj;
				if(map.isEmpty()) {return false;}
			}
		}
		
		return true;
	}
	
    /**
     * 驼峰格式字符串转换为下划线格式字符串
     * 
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,param);
    }

	public static boolean startWith(String str, String[] c) {
		for (int n = 0; n < c.length; n++) {
			if (str.startsWith(c[n])) {
				return true;
			}
		}
		return false;
	}

	public static boolean endtWith(String str, String[] c) {
		for (int n = 0; n < c.length; n++) {
			if (str.endsWith(c[n])) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(camelToUnderline("orderName"));
	}
	
}
