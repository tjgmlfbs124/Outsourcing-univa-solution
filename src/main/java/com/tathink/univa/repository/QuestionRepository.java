package com.tathink.univa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.tathink.univa.domain.Question;

public class QuestionRepository {
	
	private final EntityManager em;
	
	public QuestionRepository(EntityManager em) {
		this.em = em;
	}
	
	public Question save(Question question) {
		em.persist(question);
		return question;
	}
	
	public Optional<Question> findById(Long id) {
		Question question = em.find(Question.class, id);
		return Optional.ofNullable(question);
	}
	
	public List<Question> findAll() {
		return new ArrayList<Question>();
	}
}
