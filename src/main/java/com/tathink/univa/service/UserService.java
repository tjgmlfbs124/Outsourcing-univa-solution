package com.tathink.univa.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.tathink.univa.controller.ManagerForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.repository.UserRepository;

@Transactional
public class UserService {
	
	private final UserRepository uRepository;
	
	public UserService(UserRepository uRepository) {
		this.uRepository = uRepository;
	}
	
	public Optional<Manager> login(ManagerForm form) {
		Manager manager = new Manager();
		manager.setUsername(form.getUsername());
		manager.setPassword(form.getPassword());
		
		return uRepository.findByManagerObj(manager);
		//session.setAttribute("user", value);
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("user");
	}
}
