package com.stu.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/file")
public class FileServlet {

	@RequestMapping(value="/upload",produces="application/json")
	@ResponseBody
	private String upFile() {
		
		
		return null;
	}
	
	@RequestMapping(value="/remove",produces="application/json")
	@ResponseBody
	private String remove(String id) {
		return null;
	}
	
	@RequestMapping(value="/edite",produces="application/json")
	@ResponseBody
	private String edite(String id) {
		return null;
	}
	
	/**
	 * չʾ
	 * @return
	 */
	@RequestMapping(value="/show",produces="application/json")
	@ResponseBody
	private String show() {
		return null;
	}
	
	@RequestMapping(value="/search",produces="application/json")
	@ResponseBody
	private String search() {
		return null;
	}
	
}
