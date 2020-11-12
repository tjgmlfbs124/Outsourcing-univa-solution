package com.tathink.univa.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSIGNED INT")
	private int id;
	
	@OneToOne
	@JoinColumn(name="question_id", nullable=false)
	private Solution question;
	
	@Column(name="content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name="answer_date")
	private LocalDateTime answer_date;
	
	@OneToMany(targetEntity=AnswerSub.class, mappedBy="answer")
	private List<AnswerSub> answer_sub;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Solution getQuestion() {
		return question;
	}
	public void setQuestion(Solution question) {
		this.question = question;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getAnswer_date() {
		return answer_date;
	}
	public void setAnswer_date(LocalDateTime answer_date) {
		this.answer_date = answer_date;
	}

	public List<AnswerSub> getAnswer_sub() {
		return answer_sub;
	}
	public void setAnswer_sub(List<AnswerSub> answer_sub) {
		this.answer_sub = answer_sub;
	}
	public void addAnsser_sub(AnswerSub answerSub) {
		this.answer_sub.add(answerSub);
	}
}
