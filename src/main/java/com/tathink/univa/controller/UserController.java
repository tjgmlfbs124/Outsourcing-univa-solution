package com.tathink.univa.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.tathink.univa.domain.Manager;
import com.tathink.univa.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/mamager/login")
	public void login(ManagerForm form, HttpSession session, Model model) {
		Manager manager = userService.login(form).get();
		if(manager != null) {
			ManagerForm mManager = new ManagerForm();
			mManager.setName(manager.getName());
			mManager.setUsername(manager.getUsername());
			mManager.setPassword(manager.getPassword());
			model.addAttribute(mManager);
		}
		
	}
	
	@PostMapping("/manager/logout")
	public void doLogout(HttpSession session, Model model) {
		//userService.doLogout(session);
	}
	
}
