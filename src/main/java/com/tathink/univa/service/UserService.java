package com.tathink.univa.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.repository.UserRepository;

@Transactional
public class UserService {
	
	private final UserRepository uRepository;
	
	public UserService(UserRepository uRepository) {
		this.uRepository = uRepository;
	}
	
	public Manager login(UserLoginForm form, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("user");
		if(manager != null) {
			Manager rManager = uRepository.findByManagerObj( manager ).orElse(null);
			if(rManager != null) {
				return rManager;
			}
		}
		
		manager = new Manager();
		manager.setUsername(form.getUsername());
		manager.setPassword(form.getPassword());
		
		Manager managerResult = uRepository.findByManagerObj(manager).orElse(null); 
		if(managerResult != null) {
			session.setAttribute("user", managerResult);
		}
		return managerResult;
		
		//session.setAttribute("user", value);
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("user");
	}
}
