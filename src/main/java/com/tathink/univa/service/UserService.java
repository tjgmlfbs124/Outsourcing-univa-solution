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
		UserLoginForm userForm = (UserLoginForm) session.getAttribute("user");
		if(userForm != null) {
			session.removeAttribute("user");
		}
		Manager manager = new Manager();
		manager.setUsername(form.getUsername());
		manager.setPassword(form.getPassword());
		Manager managerObj = userRepository.findByManagerObj(manager).orElse(null);
		if(managerObj != null) {
			session.setAttribute("user", form);
			return managerObj;
		} else {
			return null;
		}
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
				tempForm.setIdx(solutionUser.getId());
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
			if(user == null) return null;
			UserLoginForm tempForm = new UserLoginForm();
			tempForm.setName(user.getNickname());
			tempForm.setUsername(user.getUsername());
			tempForm.setPassword(user.getPassword());
			tempForm.setIdx(user.getId());
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
	
	public Optional<User> findOne(int id) {
		Optional<User> user = userRepository.findByUserId(id);
		return user;
	}
}
