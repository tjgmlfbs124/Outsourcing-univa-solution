package com.tathink.univa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.service.SolutionService;

@Controller
public class SolutionController {
	private final SolutionService sService;
	
	@Autowired
	public SolutionController(SolutionService qService) {
		this.sService = qService;
	}
	
	@GetMapping("/solution")
	public String SolutionIndex(Model model) {
		//model.addAttribute("list")
		List<Solution> solutions = sService.findRecently(10);  
		model.addAttribute("solutions",solutions);
		
		return "solution/index";
	}
	
	@GetMapping("/solution/list")
	public String SolutionList(
			@RequestParam(value="min", defaultValue="0") int min,
			@RequestParam(value="max", defaultValue="10") int max,
			@RequestParam(value="state", defaultValue="0") int state,
			Model model
			) {
		List<Solution> solutions = sService.findList(min, max, state);
		model.addAttribute("solutions",solutions);
		
		return "solution/list";
	}
	
	@GetMapping("/solution/cnt")
	@ResponseBody
	public String SolutionCnt(Model model) {
		int count = sService.length();
		model.addAttribute("count", count);
		return Integer.toString(count);
	}
	
	@PostMapping("/solution/apply")
	public String SolutionApply(@RequestBody SolutionForm form) {
		Solution solution = new Solution();
		solution.setTitle(form.getTitle());
		solution.setNickname(form.getNickname());
		solution.setContent(form.getContent());
		solution.setPassword(form.getPassword());
		solution.setLimit_date(form.getLimit_date());
		//solution.setProblem(form.getProblems());
		for (ProblemForm mForm : form.getProblems()) {
			System.out.println(mForm.getNumber());
			Problem problem = new Problem();
			problem.setQuestion_id(solution);
			problem.setNumber(mForm.getNumber());
			problem.setText(mForm.getText());
			solution.addProblem(problem);
		}
		
		sService.apply(solution);
		
		
		return "solution/list";
	}
	
	@GetMapping("/solution/all")
	public String SolutionAllList(Model model) {
		List<Solution> solutions = sService.findAllQuestions();
		model.addAttribute("solutions", solutions);
		
		return "solutoin/list";
	}
	
	@GetMapping("/solution/solution")
	@ResponseBody
	public String SolutionId(
			@RequestParam("id") int id,
			Model model) {
		Solution solution = sService.findOne(id).get();
		model.addAttribute("solution", solution);
		
		return "solution/solution";
	}
	
	@GetMapping("/test")
	public String SolutionTest(Model model) {
		return "redirect:/";
	}
	
}
