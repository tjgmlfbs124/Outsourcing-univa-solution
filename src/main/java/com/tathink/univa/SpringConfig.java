package com.tathink.univa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tathink.univa.repository.SolutionRepository;
import com.tathink.univa.service.SolutionService;

@Configuration
public class SpringConfig {
	
	private EntityManager em;
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	@Bean
	public SolutionService questionService() {
		return new SolutionService(questionRepository());
	}
	
	@Bean
	public SolutionRepository questionRepository() {
		return new SolutionRepository(em);
	}
}
