package com.tathink.univa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.tathink.univa.domain.Answer;
import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.domain.SolutionState;

public class SolutionRepository {
	
	private final EntityManager em;
	
	public SolutionRepository(EntityManager em) {
		this.em = em;
	}
	
	public Solution save(Solution solution) {
		em.persist(solution);
		return solution;
	}
	
	public Answer save(Answer answer) {
		em.persist(answer);
		return answer;
	}
	
	public Optional<Solution> findById(int id) {
		Solution question = em.find(Solution.class, id);
		return Optional.ofNullable(question);
	}
	
	public Optional<SolutionState> findStateById(int id) {
		SolutionState state = em.find(SolutionState.class, id);
		return Optional.ofNullable(state);
	}
	
	public List<Solution> findAll() {
		return em.createQuery("select q from question q", Solution.class).getResultList();
		//return new ArrayList<Question>();
	}
	
	public List<Solution> findRecently(int amount) {
		 TypedQuery<Solution> mQuery = em.createQuery(
				 "select q from question q order by q.id desc", Solution.class);
		 mQuery.setMaxResults(amount);
		 //mQuery.setParameter("amount", amount);
		 
		 return mQuery.getResultList();
	}
	
	public List<Solution> findLimitAndState(int firstResult, int maxNumber, SolutionState state) {
		TypedQuery<Solution> mQuery;
		mQuery = em.createQuery("select q from question q where state = :state order by q.id desc", Solution.class)
				.setParameter("state", state)
				.setFirstResult(firstResult)
				.setMaxResults(maxNumber);
		return mQuery.getResultList();
	}
	
	public List<Solution> findLimit(int firstResult, int maxNumber) {
		TypedQuery<Solution> mQuery;
		mQuery = em.createQuery("select q from question q order by q.id desc", Solution.class)
				.setFirstResult(firstResult)
				.setMaxResults(maxNumber);
		return mQuery.getResultList();
	}
	
	public List<Problem> findByIdForProblem(Solution sol) {
		TypedQuery<Problem> mQuery;
		mQuery = em.createQuery("select p from problem p where question_id = :id order by p.number", Problem.class)
				.setParameter("id", sol);
		return mQuery.getResultList();
	}

	public List<Solution> findByManager(int firstResult, int maxNumber, Manager manager) {
		TypedQuery<Solution> mQuery;
		mQuery = em.createQuery("select q from question q where manager = :manager order by q.id desc", Solution.class)
				.setParameter("manager", manager)
				.setFirstResult(firstResult)
				.setMaxResults(maxNumber);
		return mQuery.getResultList();
	}
}
