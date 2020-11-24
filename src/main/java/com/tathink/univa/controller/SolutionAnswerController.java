package com.tathink.univa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/solution/myAnswer")
	public String SolutionMyAnswerList(
			@RequestParam(value = "min", defaultValue = "0") int first,
			@RequestParam(value = "max", defaultValue = "10") int number,
			@RequestParam("id") int id,
			HttpSession session,
			Model model) {
		//TODO 나의 답변 보여줄 페이지
		return "??";	
	}
}
