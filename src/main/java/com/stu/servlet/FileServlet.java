package com.stu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.bean.Datas;

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
		Datas d = new Datas();
		d.setUrl(path);
		d.setSize((targetFile.length() / 1024) * 1.0);
		return "redirect:rwsh";
	}

	@RequestMapping(value = "/place")
	public String place() {
		return "fileShow.html";
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

	@RequestMapping(value = "/show", produces = "application/json")
	@ResponseBody
	private String show() {
		return "show";
	}

	@RequestMapping(value = "/search", produces = "application/json")
	@ResponseBody
	private String search() {
		return null;
	}

	@RequestMapping(value = "/download", produces = "application/json")
	@ResponseBody
	private String download(HttpServletRequest request, HttpServletResponse response, String id) {
		try {
			response.setContentType("application/msexcel;");
			response.setHeader("Content-Disposition",
					new String(("attachment;filename=" + "downLode.xls").getBytes("GB2312"), "UTF-8"));
			File f = new File("path");
			FileInputStream in = new FileInputStream(f);
			byte b[] = new byte[1024];
			int i = 0;
			ServletOutputStream out = response.getOutputStream();
			while ((i = in.read(b)) != -1) {
				out.write(b, 0, i);
			}
			out.flush();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
