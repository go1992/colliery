package com.yw.colliery.absys;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyUtil {
	public static final String FILE_PATH = "D:";
//	public static final String FILE_PATH = "C:"+File.separator+"Users"+File.separator+"49796"+File.separator+"Desktop";
	/*
	 * 判空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkNotNull(Object...objects ) {
		if(null == objects) return false;
		for(Object obj : objects) {
			if(null == obj  ) {
				return false;
			} else if(obj instanceof String) {
				String str = obj.toString();
				if(str.trim().isEmpty()) return false;
			} else if(obj instanceof Set ) {
				Set set = (Set)obj;
				if(set.isEmpty()) return false;
			} else if(obj instanceof List) {
				List list = (List)obj;
				if(list.isEmpty()) return false;
			} else if(obj instanceof Map) {
				Map map = (Map)obj;
				if(map.isEmpty()) return false;
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
        if (param == null || param.isEmpty()) throw new RuntimeException("表名为空");
        int len = param.length();
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if(i!=0)sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
	
}
