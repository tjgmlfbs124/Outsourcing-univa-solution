package com.tathink.univa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tathink.univa.controller.form.ChatJsonForm;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.domain.SolutionChat;
import com.tathink.univa.service.SolutionService;
import com.tathink.univa.utils.FileUtil;

@Controller
public class SolutionChatController {
	private final SolutionService sService;
	
	@Autowired
	public SolutionChatController(SolutionService service) {
		this.sService = service;
	}
	
	/*
	@PostMapping("/solution/chat")
	@ResponseBody
	public List<ChatJsonForm> SendChat(
			ChatJsonForm form) {
		Solution solution = sService.SolutionChatSave(form);
		return sService.SolutionChatList(solution.getId());
	}
	
	@PostMapping("/solution/chat/refresh")
	@ResponseBody
	public List<ChatJsonForm> RefrechChat(
			@RequestBody ChatJsonForm form) {
		//Solution solution = sService.findOne(form.getSolution_id()).get();
		//return solution;
		List<ChatJsonForm> solutionChats = sService.SolutionChatList(form.getSolution_id());
		return solutionChats;
	}
	*/
	
	@PostMapping("/solution/img")
	@ResponseBody
	public String SendImg(
			@RequestParam MultipartFile file) {
		return sService.WriteFile(file);
	}
}
