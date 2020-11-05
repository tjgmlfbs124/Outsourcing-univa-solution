package com.tathink.univa.controller;

import com.tathink.univa.utils.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tathink.univa.utils.FileUtil;

@Controller
public class helloController {
	
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello";
	}
	
	@ResponseBody
	@PostMapping("hello/upload")
	public String imageUpload(@RequestParam("img") MultipartFile file) {
		//File targetFile = new File("/image/"+file.getOriginalFilename());
		//Path mPath = Paths.get("uploads/",file.getOriginalFilename());
		String savePath = "uploads/imgs/"+StringUtil.RandomString(10)+"/";
		if(!new File(savePath).exists()) { // 폴더가 없을 경우 생성
			try {
				//System.out.println(new File(savePath).canWrite());
				File mFile = new File(savePath);
				mFile.mkdirs();
				//System.out.println("not exist!");
				//System.out.println(mFile.getAbsolutePath());
				//System.out.println(mFile.getCanonicalPath());
			}
			catch(Exception e) {
				e.getStackTrace();
			}
		}
		
		//String filePath = savePath+file.getOriginalFilename();
		FileUtil.FileWrite(file, savePath);
		
		return "hello";
	}
	
	@GetMapping("hello/img")
	public ResponseEntity<Resource> imageView(@RequestParam("id") String img) throws IOException {
		Path path = Paths.get("uploads/imgs/"+img+"/img.png");
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("hello/obj")
	public String objectReceive(@RequestBody helloForm form) {
		System.out.println(form.getLimit_date());
		
		return "object";
	}
}
