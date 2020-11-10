package com.tathink.univa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.tathink.univa.domain.Manager;

public class UserRepository {
	
	private final EntityManager em;
	
	public UserRepository(EntityManager em) {
		this.em = em;
	}
	
	public Optional<Manager> findByManagerObj(Manager manager) {
		TypedQuery<Manager> mQuery;
		mQuery = em.createQuery("select m from manager m where username = :username AND password = :password", Manager.class)
				.setParameter("username", manager.getUsername())
				.setParameter("password", manager.getPassword());
		List<Manager> result = mQuery.getResultList();
		
		return result.stream().findAny();
	}
	
}
