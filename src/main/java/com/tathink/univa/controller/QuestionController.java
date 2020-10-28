package com.tathink.univa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tathink.univa.domain.Question;
import com.tathink.univa.service.QuestionService;

@Controller
public class QuestionController {
	private final QuestionService qService;
	
	@Autowired
	public QuestionController(QuestionService qService) {
		this.qService = qService;
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
	
	@GetMapping("/question")
	public String QuestionList(Model model) {
		List<Question> questions = qService.findAllQuestions();
		model.addAttribute("questions", questions);
		
		return "question/list";
	}
	
	@GetMapping("/test")
	public String QuestionTest(Model model) {
		return "redirect:/";
	}
}
