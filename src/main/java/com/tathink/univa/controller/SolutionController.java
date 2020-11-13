package com.tathink.univa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tathink.univa.controller.form.ReviewForm;
import com.tathink.univa.controller.form.SolutionForm;
import com.tathink.univa.controller.form.SolutionLoginForm;
import com.tathink.univa.domain.Answer;
import com.tathink.univa.domain.AnswerSub;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.QuestionSubject;
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
	
	@GetMapping("/solution/apply")
	public String SolutionApplyPage() {
		
		return "solution/apply";
	}
	
	@PostMapping("/solution/apply")
	public String SolutionApply(SolutionForm form) {
		sService.apply(form);
		System.out.println(form.getTitle());
		
		return "redirect:/solution/list";
	}
	
	@GetMapping("/solution/all")
	public String SolutionAllList(Model model) {
		List<Solution> solutions = sService.findAllQuestions();
		model.addAttribute("solutions", solutions);
		
		return "solutoin/list";
	}
	
	/*@GetMapping("/solution/solution")
	@ResponseBody
	public String SolutionId(
			@RequestParam("id") int id,
			Model model) {
		Boolean solution_pass = (Boolean)model.getAttribute("solution_pass");
		model.addAttribute("solution_pass", false);
		
		if(solution_pass) {
			Solution solution = sService.findOne(id).get();
			model.addAttribute("solution", solution);
			
			return "solution/solution";
		} else {
			//
			return "stop!";
		}
	}*/
	
	@GetMapping("/solution/detail")
	public String SolutionDetail(
			@RequestParam("id") int id,
			Model model,
			HttpSession session) {
		SolutionLoginForm solutionInfo = (SolutionLoginForm)session.getAttribute("solution_user");
		Manager userInfo = (Manager)session.getAttribute("user");
		if( (solutionInfo != null && solutionInfo.getId() == id) || userInfo != null) {
			Solution solution = sService.findOne(id).get();
			model.addAttribute("solution", solution);
			return "/solution/detail";
		} else {
			return "redirect:/solution/";
		}
	}
	
	@PostMapping("/solution/login")
	@ResponseBody
	public Boolean SolutionLoginViaAjax(
			@RequestBody SolutionLoginForm form, 
			HttpSession session) {
		if(sService.loginSolutionUser(form.getId(), form.getPassword()) ) {
			session.setAttribute("solution_user", form);
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("/solution/review")
	public String SolutionReviewApply(
			ReviewForm form) {
		int idx = sService.reviewApply(form);
		
		String redirectUrl = "redirect:/solution/detail?id="+idx; 
		return redirectUrl;
	}
	
	@GetMapping("/solution/img")
	public ResponseEntity<Resource> imageView(@RequestParam("id") String img) throws IOException {
		Path path = Paths.get("uploads/imgs/"+img);
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/solution/test")
	@ResponseBody
	public String SolutionTest(Model model) {
		Solution solution = sService.findOne(190).get();
		for( QuestionSubject data : solution.getSubjects() ) {
			System.out.println("name: "+data.getSubject().getName());
		}
		
		
		/* 답변의 보조답변들 출력
		List<AnswerSub> answers = sService.findOne(190).get().getAnswer().getAnswer_sub();
		Answer answer = sService.findOne(190).get().getAnswer();
		for(AnswerSub val : answers) {
			System.out.println(val.getText());
		}
		System.out.println(answer.getQuestion().getNickname());
		*/
		return "hello";
	}
	
}