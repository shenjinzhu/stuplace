package com.stu.servlet;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller("/file")
public class FileServlet {

	@RequestMapping(value = "/upload", produces = "application/json")
	@ResponseBody
	private String upFile(HttpServletRequest req, HttpServletResponse res, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String path = "/usr/local/files/" + fileName;
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:rwsh";
	}

	@RequestMapping(value = "/remove", produces = "application/json")
	@ResponseBody
	private String remove(String id) {
		return null;
	}

	@RequestMapping(value = "/edite", produces = "application/json")
	@ResponseBody
	private String edite(String id) {
		return null;
	}

	/**
	 * չʾ
	 * 
	 * @return
	 */
	@RequestMapping(value = "/show", produces = "application/json")
	@ResponseBody
	private String show() {
		return null;
	}

	@RequestMapping(value = "/search", produces = "application/json")
	@ResponseBody
	private String search() {
		return null;
	}

}
