package com.qiantao.file.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/utils/file")
public class FileController {
	
	 @PostMapping("/import")
	 @ResponseBody
	 public String upload(@RequestParam("file") MultipartFile file) {
	    if (file.isEmpty()) {
	       return "上传失败，请选择文件";
	    }
       String fileName = file.getOriginalFilename();
       String filePath = "D://file/";
       File dest = new File(filePath + fileName);
       try {
          file.transferTo(dest);
	            return "上传成功";
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return "上传失败！";
	    }
	@RequestMapping("/toFile")
	public String toFile(){

		return "util/file";
	}
}
