package com.tathink.univa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

@Entity(name="question_subject")
@DynamicInsert
public class SolutionSubject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSIGNED INT")
	private int id;
	
	@ManyToOne(targetEntity=Solution.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Solution solution;
	
	@ManyToOne(targetEntity=Subject.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
