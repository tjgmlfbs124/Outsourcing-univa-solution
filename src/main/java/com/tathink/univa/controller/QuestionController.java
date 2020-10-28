package com.tathink.univa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tathink.univa.service.QuestionService;

@Controller
public class QuestionController {
	private final QuestionService qService;
	
	@Autowired
	public QuestionController(QuestionService qService) {
		this.qService = qService;
	}
	
	@PostMapping("/question/apply")
	public String QuestionApply(Model model) {
		//qService.apply()
		return "quenstion/list";
	}
	
	@GetMapping("/question")
	public String QuestionList(Model model) {
		
		
		return "question/list";
	}
	
	@GetMapping("/test")
	public String QuestionTest(Model model) {
		return "redirect:/";
	}
}
