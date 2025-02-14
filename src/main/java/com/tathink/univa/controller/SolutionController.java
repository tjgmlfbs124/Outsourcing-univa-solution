package com.tathink.univa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.univa.controller.form.ReviewForm;
import com.tathink.univa.controller.form.SolutionForm;
import com.tathink.univa.controller.form.SolutionLoginForm;
import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Answer;
import com.tathink.univa.domain.AnswerSub;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.SolutionSubject;
import com.tathink.univa.domain.Subject;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.service.SolutionService;
import com.tathink.univa.service.UserService;

@Controller
public class SolutionController {
	private final SolutionService sService;
	private final UserService userService;

	@Autowired
	public SolutionController(SolutionService qService, UserService userService) {
		this.sService = qService;
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String IndexPage() {
		return "redirect:/solution";
	}

	@GetMapping("/solution")
	public String SolutionIndex(Model model, HttpSession session) {
		// model.addAttribute("list")
		//List<Solution> solutions = sService.findRecently(10);
		List<Solution> solutions = sService.findList(0, 7, 5, session);
		model.addAttribute("solutions", solutions);

		return "solution/index";
	}
	@GetMapping("/solution/help/{path}")
	public String helpPage(
			@PathVariable(value="path") String path) {
		return "solution/help/"+path;
	}

	@GetMapping("/solution/list")
	public String SolutionList(@RequestParam(value = "min", defaultValue = "0") int min,
			@RequestParam(value = "max", defaultValue = "10") int max,
			@RequestParam(value = "state", defaultValue = "0") int state, 
			Model model,
			HttpSession session) {
		List<Solution> solutions = sService.findList(min, max, state, session);
		model.addAttribute("solutions", solutions);

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
	public String SolutionApplyPage(Model model, HttpSession session) {
		model.addAttribute("subject_list", sService.findAllSubjectOrderByName());
		UserLoginForm userForm = (UserLoginForm) session.getAttribute("user");
		
		if (userForm != null && userForm.getType() == 0) {
			userService.logout(session);
		}
		return "solution/apply";
	}

	@PostMapping("/solution/apply")
	public String SolutionApply(SolutionForm form, HttpSession session) {
		sService.apply(form, session);
		
		return "redirect:/solution/list?state=0";
	}

	@GetMapping("/solution/all")
	public String SolutionAllList(Model model) {
		List<Solution> solutions = sService.findAllQuestions();
		model.addAttribute("solutions", solutions);

		return "solutoin/list";
	}

	@GetMapping("/solution/detail")
	public String SolutionDetail(@RequestParam("id") int id, Model model, HttpSession session) {
		Solution solution = sService.findOne(id).get();
		UserLoginForm userInfo = (UserLoginForm) session.getAttribute("user");
		
		if(userInfo != null) {			
			if (userInfo.getSol_idx() == id && userInfo.getType() == 0 ) {
				model.addAttribute("solution", solution);
				return "solution/detail";
			} else if ( userInfo.getType() == 1 && solution.getUser().getUsername().equals(userInfo.getUsername())) {
				model.addAttribute("solution", solution);
				return "solution/detail";
			} else if( userInfo.getType() == 2) {
				model.addAttribute("solution", solution);
				return "solution/detail";
			}
			return "redirect:/solution/";
		} else {
			return "redirect:/solution/";
		}
	}

//	@PostMapping("/solution/login")
//	@ResponseBody
	public Boolean SolutionLoginViaAjax(@RequestBody SolutionLoginForm form, HttpSession session) {
//		if (sService.loginSolutionUser(form.getId(), form.getPassword())) {
//			session.setAttribute("solution_user", form);
//			return true;
//		} else {
//			return false;
//		}
		return false;
	}

	@PostMapping("/solution/review")
	public String SolutionReviewApply(ReviewForm form) {
		int idx = sService.reviewApply(form);

		String redirectUrl = "redirect:/solution/detail?id=" + idx;
		return redirectUrl;
	}

	@GetMapping("/solution/img")
	public ResponseEntity<Resource> imageView(@RequestParam("id") String img) throws IOException {
		System.out.println("s/img?id="+img);
		Path path = Paths.get("uploads/imgs/" + img);
		String contentType = Files.probeContentType(path);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = null;
		try {
			resource = new InputStreamResource(Files.newInputStream(path));
		} catch (Exception e) {
			e.getStackTrace();
			// 이미지가 없을 경우 발생
		}
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/solution/subject/list")
	@ResponseBody
	public List<Map<String, String>> SubjectList(Model model) {
		List<Subject> subs = sService.findAllSubject(); 
		List<Map<String, String>> maps = new ArrayList<>();
		
		for(Subject sub : subs) {
			//subStrs.add(sub.getName());
			Map<String, String> map = new HashMap<>();
			map.put("id", Integer.toString(sub.getId()));
			map.put("name", sub.getName());
			maps.add(map);
		}
		return maps;
	}
	
	@PostMapping("/solution/subject/name")
	@ResponseBody
	public List<Map<String, String>> SubjectFindByName(@RequestParam String name) {
		List<Subject> subs = sService.findBySubjectName(name);
		List<Map<String, String>> maps = new ArrayList<>();
		for(Subject sub : subs) {
			Map<String, String> map = new HashMap<>();
			map.put("id", Integer.toString(sub.getId()));
			map.put("name", sub.getName());
			maps.add(map);
		}
		return maps;
	}
	
	/*TODO 해당 주제의 질문 찾기  */
	@PostMapping("/solution/list/subject")
	@ResponseBody
	public String SolutionFindBySubject(@RequestParam int subject_id) {
		Subject subject = sService.findSubjectOne(subject_id).orElse(null);
		if(subject != null) {
			//System.out.println(subject.getSoluions().toString());
			for(SolutionSubject sol : subject.getSoluions()) {
				System.out.println(Integer.toString(sol.getSolution().getId()));
			}
		}
		return "hello?";
	}

	@PostMapping("/solution/test")
	@ResponseBody
	public String SolutionTest(Model model) {
//		Solution solution = sService.findOne(190).get();
//		for (SolutionSubject data : solution.getSubjects()) {
//			System.out.println("name: " + data.getSubject().getName());
//		}

		/*
		 * 답변의 보조답변들 출력 List<AnswerSub> answers =
		 * sService.findOne(190).get().getAnswer().getAnswer_sub(); Answer answer =
		 * sService.findOne(190).get().getAnswer(); for(AnswerSub val : answers) {
		 * System.out.println(val.getText()); }
		 * System.out.println(answer.getQuestion().getNickname());
		 */
		return "hello";
	}

}