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

public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSGINED INT")
	private int id;
	
	@ManyToOne(targetEntity=Question.class, fetch=FetchType.EAGER)
	@JoinColumn(name="question_id")
	private Question question_id;
	
	@Column(name="number", nullable=false)
	private int number;
	
	@Column(name="text", columnDefinition="TEXT")
	private String text;
	
	@Column(name="image_url")
	private String image_url;
	
}
