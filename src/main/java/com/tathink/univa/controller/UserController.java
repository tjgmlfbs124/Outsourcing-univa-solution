package com.tathink.univa.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.User;
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
		return "user/login/index";
	}

	@PostMapping(value = {"/solution/user/login", "/solution/login"})
	@ResponseBody
	public Boolean login(
			@RequestBody UserLoginForm form,
			HttpSession session,
			Model model) {
		if(form.getType() == 2) {
			Manager manager = userService.managerLogin(form, session);
			if(manager != null) {
				return true;
			} else {
				return false;
			}
		} else {
			User user = userService.userLogin(form, session);
			if(user != null) {
				return true;
			} else {
				return false;
			}
		}
	}

	@GetMapping("/solution/user/logout")
	public String doLogout(HttpSession session, Model model) {
		userService.logout(session);
		return "redirect:/solution";
	}
	
	@GetMapping("/solution/user/signup")
	public String userSignupPage() {
		return "user/login/signup";
	}
	
	@PostMapping("/solution/user/signup")
	@ResponseBody
	public Boolean userJoin(
			@RequestBody UserLoginForm form,
			Model model) {
		if( userService.userSignup(form) == null ) {
			return false;
		} else {
			return true;
		}
	}
	
	@PostMapping("/solution/user/identify")
	@ResponseBody
	public Boolean userIdentify(
			@RequestBody UserLoginForm form) {
		User user = new User();
		user.setUsername(form.getUsername());
		return userService.validateDuplicateUser(user); 
	}

	@GetMapping("/user/test")
	@ResponseBody
	public String test(HttpSession session) {
		Manager manager = (Manager)session.getAttribute("user");
		return manager.getName();
	}
}
