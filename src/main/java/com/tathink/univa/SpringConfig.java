package com.tathink.univa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tathink.univa.repository.QuestionRepository;
import com.tathink.univa.service.QuestionService;

@Configuration
public class SpringConfig {
	
	private EntityManager em;
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	@Bean
	public QuestionService questionService() {
		return new QuestionService(questionRepository());
	}
	
	@Bean
	public QuestionRepository questionRepository() {
		return new QuestionRepository(em);
	}
}
