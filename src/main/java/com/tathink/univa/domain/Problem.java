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

@Entity(name="problem")
@DynamicInsert
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSGINED INT")
	private int id;
	
	@ManyToOne(targetEntity=Solution.class, fetch=FetchType.EAGER)
	@JoinColumn(name="question_id")
	private Solution question_id;
	
	@Column(name="number", nullable=false)
	private int number;
	
	@Column(name="text", columnDefinition="TEXT")
	private String text;
	
	@Column(name="image_url")
	private String image_url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Solution getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Solution question_id) {
		this.question_id = question_id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}
