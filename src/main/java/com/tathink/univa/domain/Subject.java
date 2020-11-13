package com.tathink.univa.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSIGNED INT")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "subject")
	private Set<QuestionSubject> questions;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<QuestionSubject> getQuestions() {
		return questions;
	}
}
