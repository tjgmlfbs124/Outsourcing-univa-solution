package com.tathink.univa.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.User;
import com.tathink.univa.repository.UserRepository;

@Transactional
public class UserService {
	
	private final UserRepository uRepository;
	
	public UserService(UserRepository uRepository) {
		this.uRepository = uRepository;
	}
	
	public Manager managerLogin(UserLoginForm form, HttpSession session) {
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
	
	public User userLogin(UserLoginForm form, HttpSession session) {
		// 세션 로그인 상태 검사
		UserLoginForm userForm = (UserLoginForm) session.getAttribute("user");
		User mUser = new User();
		mUser.setUsername(form.getUsername());
		mUser.setPassword(form.getPassword());
		
		if(userForm != null) {
			User rUser = uRepository.findByUserObj(mUser).orElse(null);
			if(rUser != null) {
				return rUser;
			}
		}
		
		User userResult = uRepository.findByUserObj(mUser).orElse(null);
		if(userResult != null) {
			session.setAttribute("user", form);
		}
		System.out.println(userResult.getId());
		return userResult;
	}
	
	
	public void logout(HttpSession session) {
		session.removeAttribute("user");
	}
}
