package com.tathink.univa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.univa.domain.Question;
import com.tathink.univa.service.QuestionService;

@Controller
public class QuestionController {
	private final QuestionService qService;
	
	@Autowired
	public QuestionController(QuestionService qService) {
		this.qService = qService;
	}
	
	@GetMapping("/question/")
	public String QuestionIndex(Model model) {
		//model.addAttribute("list")
		List<Question> questions = qService.findRecently(10);  
		model.addAttribute(questions);
		
		return "index";
	}
	
	@GetMapping("/question/list")
	public String QuestionList(
			@RequestParam("min") int min,
			@RequestParam("max") int max,
			@RequestParam("state") int state,
			Model model
			) {
		List<Question> questions = qService.findList(min, max, state);
		model.addAttribute(questions);
		
		return "questions/list";
	}
	
	@GetMapping("/question/cnt")
	@ResponseBody
	public String QuestionCnt(Model model) {
		int count = qService.length();
		model.addAttribute("count", count);
		return Integer.toString(count);
	}
	
	@PostMapping("/question/apply")
	public String QuestionApply(QuestionForm form) {
		Question question = new Question();
		question.setTitle(form.getTitle());
		question.setNickname(form.getNickname());
		question.setPassword(form.getPassword());
		question.setLimit_date(form.getLimit_date());
		qService.apply(question);
		
		return "quenstion/list";
	}
	
	@GetMapping("/question/all")
	public String QuestionAllList(Model model) {
		List<Question> questions = qService.findAllQuestions();
		model.addAttribute("questions", questions);
		
		return "question/list";
	}
	
	@GetMapping("/test")
	public String QuestionTest(Model model) {
		return "redirect:/";
	}
	
}
