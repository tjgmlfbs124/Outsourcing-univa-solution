package com.tathink.univa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	public Optional<Solution> findById(int id) {
		Solution question = em.find(Solution.class, id);
		return Optional.ofNullable(question);
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
	
	public List<Solution> findLimitAndState(int lLimit, int hLimit, SolutionState state) {
		TypedQuery<Solution> mQuery;
		mQuery = em.createQuery("select q from question q where state = :state order by q.id desc", Solution.class)
				.setParameter("state", state)
				.setFirstResult(lLimit)
				.setMaxResults(hLimit);
		return mQuery.getResultList();
	}
	
	public List<Solution> findLimit(int lLimit, int hLimit) {
		TypedQuery<Solution> mQuery;
		mQuery = em.createQuery("select q from question q order by q.id desc", Solution.class)
				.setFirstResult(lLimit)
				.setMaxResults(hLimit);
		return mQuery.getResultList();
	}
	
	public List<Problem> findByIdForProblem(Solution sol) {
		TypedQuery<Problem> mQuery;
		mQuery = em.createQuery("select p from problem p where question_id = :id order by p.number", Problem.class)
				.setParameter("id", sol);
		return mQuery.getResultList();
	}
}
