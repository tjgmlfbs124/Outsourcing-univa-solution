package com.tathink.univa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tathink.univa.repository.QuestionRepository;
import com.tathink.univa.service.QuestionService;

@Configuration
public class SpringConfig {
	
	@Bean
	public QuestionService questionService() {
		return new QuestionService(questionRepository());
	}
	
	@Bean
	public QuestionRepository questionRepository() {
		return new QuestionRepository();
	}
}
