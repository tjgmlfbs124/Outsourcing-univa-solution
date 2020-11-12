package com.tathink.univa.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService uService) {
		this.userService = uService;
	}
	
	@GetMapping("/solution/user/login")
	public String loginPage() {
		return "/user/login/index";
	}
	
	@PostMapping("/solution/user/login")
	public Boolean login(UserLoginForm form, HttpSession session, Model model) {
		Manager manager = userService.login(form, session);
		if(manager != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("/user/logout")
	public void doLogout(HttpSession session, Model model) {
		session.removeAttribute("user");
	}
	
	@GetMapping("/user/test")
	@ResponseBody
	public String test(HttpSession session) {
		Manager manager = (Manager)session.getAttribute("user");
		return manager.getName();
	}
}
