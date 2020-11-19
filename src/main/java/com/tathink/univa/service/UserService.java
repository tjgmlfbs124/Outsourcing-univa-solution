package com.tathink.univa.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.controller.form.UserLoginForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.domain.User;
import com.tathink.univa.repository.SolutionRepository;
import com.tathink.univa.repository.UserRepository;

@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	private final SolutionRepository solutionRepository;
	
	public UserService(UserRepository uRepository, SolutionRepository solutionRepository) {
		this.userRepository = uRepository;
		this.solutionRepository = solutionRepository;
		
	}
	
	public Manager managerLogin(UserLoginForm form, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("user");
		if(manager != null) {
			Manager rManager = userRepository.findByManagerObj( manager ).orElse(null);
			if(rManager != null) {
				return rManager;
			}
		}
		
		manager = new Manager();
		manager.setUsername(form.getUsername());
		manager.setPassword(form.getPassword());
		
		Manager managerResult = userRepository.findByManagerObj(manager).orElse(null); 
		if(managerResult != null) {
			session.setAttribute("user", managerResult);
		}
		return managerResult;
		
		//session.setAttribute("user", value);
	}
	
	public User userLogin(UserLoginForm form, HttpSession session) {
		// 세션 로그인 상태 검사
		UserLoginForm userForm = (UserLoginForm) session.getAttribute("user");
		
		if(userForm != null) {
			session.removeAttribute("user");
		}
		
		if(form.getType() == 0) {
			if(form.getPassword() == null) return null;
			Solution solution = solutionRepository.findById(form.getSol_idx()).orElse(null);
			if(solution != null) {
				if( !solution.getUser().getPassword().equals(form.getPassword()) ) return null;
				User solutionUser = solution.getUser();
				UserLoginForm tempForm = new UserLoginForm();
				tempForm.setSol_idx(solution.getId());
				tempForm.setName(solutionUser.getNickname());
				tempForm.setPassword(solutionUser.getPassword());
				tempForm.setType(0);
				session.setAttribute("user", tempForm);
				return solutionUser;
			} else {
				return null;
			}
		} else { // 
			if(form.getUsername() == null || form.getPassword() == null) {
				return null;
			}
			
			User tempUser = new User();
			tempUser.setUsername(form.getUsername());
			tempUser.setPassword(form.getPassword());
			User user = userRepository.findByUserObj(tempUser).orElse(null);
			UserLoginForm tempForm = new UserLoginForm();
			tempForm.setName(tempUser.getNickname());
			tempForm.setUsername(tempUser.getUsername());
			tempForm.setPassword(tempUser.getPassword());
			tempForm.setType(1);
			session.setAttribute("user", tempForm);
			return user;
		}
	}
	
	public User userSignup(UserLoginForm form) {
		User mUser = new User();
		mUser.setNickname(form.getName());
		mUser.setUsername(form.getUsername());
		mUser.setPassword(form.getPassword());
		mUser.setType(1);
		if(validateDuplicateUser(mUser)) {
			return userRepository.save(mUser);
		} else {
			return null;
		}
	}
	
	public Boolean validateDuplicateUser(User user) {
		/*userRepository.findByUsername(user.getUsername()).ifPresent(m-> {
			throw new IllegalStateException("이미 존재하는 회원");
		});*/
		System.out.println(!userRepository.findByUsername(user.getUsername()).isPresent());
		return !userRepository.findByUsername(user.getUsername()).isPresent();
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("user");
	}
}
