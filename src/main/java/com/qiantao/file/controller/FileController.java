package com.qiantao.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;
@Controller
@RequestMapping("/utils/file")
public class FileController {
	@Value("${file.upload.path}")
	String filepath;
	
	
	 @PostMapping("/import")
	 @ResponseBody
	 public String upload(@RequestParam("file") MultipartFile file) throws Exception {
	    String fileName = file.getOriginalFilename();
	    return GetLocation(filepath,0,file)!=null?"上传成功":"上传失败";
	 }
	 
	 
	/**
	 * 用来创建指定地址
	 * ${filepath}/${userId}/fileName
	 * @param filepath2
	 * @param i
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	private String GetLocation(String filepath, int userId, MultipartFile file) {
		File folder=new File(filepath);
		//创建目录
		if(!folder.exists()) {
			System.out.println(folder.getPath()+" "+(folder.mkdir()?"创建成功":"创建失败"));//创建目录
		}
		
		//创建用户目录
		folder=new File(filepath+"//"+userId);
		if(!folder.exists()) {
			System.out.println(folder.getPath()+" "+(folder.mkdir()?"创建成功":"创建失败"));//创建目录
		}
		//String type = Files.probeContentType();

		folder=new File(folder.getPath()+"//"+file.getOriginalFilename());
		//按照类别分类
		try {
			file.transferTo(folder);
			return folder.getPath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	


	@RequestMapping("/toFile")
	public String toFile(){

		return "util/file";
	}
}
