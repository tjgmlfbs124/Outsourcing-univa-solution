package com.tathink.univa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.univa.domain.Solution;
import com.tathink.univa.service.SolutionService;

@Controller
public class SolutionController {
	private final SolutionService sService;
	
	@Autowired
	public SolutionController(SolutionService qService) {
		this.sService = qService;
	}
	
	@GetMapping("/question/")
	public String SolutionIndex(Model model) {
		//model.addAttribute("list")
		List<Solution> questions = sService.findRecently(10);  
		model.addAttribute(questions);
		
		return "index";
	}
	
	@GetMapping("/question/list")
	public String SolutionList(
			@RequestParam("min") int min,
			@RequestParam("max") int max,
			@RequestParam("state") int state,
			Model model
			) {
		List<Solution> questions = sService.findList(min, max, state);
		model.addAttribute(questions);
		
		return "questions/list";
	}
	
	@GetMapping("/question/cnt")
	@ResponseBody
	public String SolutionCnt(Model model) {
		int count = sService.length();
		model.addAttribute("count", count);
		return Integer.toString(count);
	}
	
	@PostMapping("/question/apply")
	public String SolutionApply(SolutionForm form) {
		Solution question = new Solution();
		question.setTitle(form.getTitle());
		question.setNickname(form.getNickname());
		question.setPassword(form.getPassword());
		question.setLimit_date(form.getLimit_date());
		sService.apply(question);
		
		return "quenstion/list";
	}
	
	@GetMapping("/question/all")
	public String SolutionAllList(Model model) {
		List<Solution> questions = sService.findAllQuestions();
		model.addAttribute("questions", questions);
		
		return "question/list";
	}
	
	@GetMapping("/test")
	public String SolutionTest(Model model) {
		return "redirect:/";
	}
	
}
