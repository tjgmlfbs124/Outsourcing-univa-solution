package com.tathink.univa.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.controller.LoginForm;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.repository.UserRepository;

@SpringBootTest
@Transactional
public class UserServiceTest {
	
	@Autowired UserService uService;
	@Autowired UserRepository uRepository;
	
	@Test
	void 유저로그인() {
		LoginForm form = new LoginForm();
		form.setUsername("test");
		form.setPassword("testpass");
		
		//Manager manager = uService.login(form).get();
		
		//assertThat("테스트").isEqualTo(manager.getName());
	}
	
	@Test
	void 없는유저로그인() {
		LoginForm form = new LoginForm();
		form.setUsername("test1");
		form.setPassword("testpass");
		
		//Manager manager = uService.login(form).orElse(new Manager());
		
		//assertThat("").isEqualTo(manager.getName());
	}
}
