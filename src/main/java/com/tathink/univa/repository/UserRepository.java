package com.tathink.univa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.tathink.univa.domain.Manager;
import com.tathink.univa.domain.User;

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
	
	public Optional<User> findByUserObj(User user) {
		TypedQuery<User> mQuery;
		mQuery = em.createQuery("select u from user u where username = :username AND password = :password", User.class)
				.setParameter("username", user.getUsername())
				.setParameter("password", user.getPassword());
		List<User> result = mQuery.getResultList();
		return result.stream().findAny();
	}
	
	public Optional<Manager> findByMangerId(int id) {
		Manager manager = em.find(Manager.class, id);
		return Optional.ofNullable(manager);
	}
	
	public Optional<User> findByUserId(int id) {
		User user = em.find(User.class, id);
		return Optional.ofNullable(user);
	}
	
}
