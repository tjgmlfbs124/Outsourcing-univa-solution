package com.tathink.univa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.tathink.univa.controller.form.AnswerForm;
import com.tathink.univa.service.SolutionService;

@Controller
public class SolutionAnswerController {
	private final SolutionService sService;
	
	@Autowired
	public SolutionAnswerController(SolutionService sService) {
		this.sService = sService;
	}
	
	@PostMapping("/solution/answer")
	public String SolutionAnswerApply(AnswerForm form) {
		
		sService.answerApply(form);
		
		String redirectUrl = "redirect:/solution/detail?id="+form.getSolution_id(); 
		return redirectUrl;
	}
}
