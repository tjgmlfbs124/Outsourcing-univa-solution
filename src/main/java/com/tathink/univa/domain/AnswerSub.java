package com.tathink.univa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

@Entity(name="problem_answer")
@DynamicInsert
public class AnswerSub {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSIGNED INT")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="answer_id")
	private Answer answer;
	
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

	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
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
