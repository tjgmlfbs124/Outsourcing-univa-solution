package com.tathink.univa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.tathink.univa.domain.Question;
import com.tathink.univa.domain.QuestionState;

public class QuestionRepository {
	
	private final EntityManager em;
	
	public QuestionRepository(EntityManager em) {
		this.em = em;
	}
	
	public Question save(Question question) {
		em.persist(question);
		return question;
	}
	
	public Optional<Question> findById(int id) {
		Question question = em.find(Question.class, id);
		return Optional.ofNullable(question);
	}
	
	public List<Question> findAll() {
		return em.createQuery("select q from question q", Question.class).getResultList();
		//return new ArrayList<Question>();
	}
	
	public List<Question> findRecently(int amount) {
		 TypedQuery<Question> mQuery = em.createQuery(
				 "select q from question q order by q.id desc", Question.class);
		 mQuery.setMaxResults(amount);
		 //mQuery.setParameter("amount", amount);
		 
		 return mQuery.getResultList();
	}
	
	public List<Question> findLimitAndState(int lLimit, int hLimit, QuestionState state) {
		TypedQuery<Question> mQuery;
		mQuery = em.createQuery("select q from question q where state = :state order by q.id desc", Question.class)
				.setParameter("state", state)
				.setFirstResult(lLimit)
				.setMaxResults(hLimit);
		return mQuery.getResultList();
	}
	
	public List<Question> findLimit(int lLimit, int hLimit) {
		TypedQuery<Question> mQuery;
		mQuery = em.createQuery("select q from question q order by q.id desc", Question.class)
				.setFirstResult(lLimit)
				.setMaxResults(hLimit);
		return mQuery.getResultList();
	}
}
