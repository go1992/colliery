package com.yw.colliery.absys;

/**
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

	/**
	 * 测试
	 * @param req
	 * @return
	 */
	@RequestMapping
    public String hello(HttpServletRequest req) {
//		System.out.println("req:"+req.getParameter("rad1"));
        return "Hello Spring-Boot12";
    }
	
	


	/**
	 * 测试返回map, 自动转json格式
	 * @param name
	 * @return
	 */
    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    /**
     * 测试返回list，自动转json格式
     * @return
     */
    @RequestMapping("/list")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<>();
            map.put("name", "Shanhy-" + i);
            list.add(map);
        }
        return list;
    }
}
