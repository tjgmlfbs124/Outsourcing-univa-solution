package com.tathink.univa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "question")
public class Question {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "visible", nullable = false)
	private String visible;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name="nickname", nullable = false)
	private String nickname;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "upload_date") @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime upload_date;
	
	@Column(name ="limit_date") @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime limit_date;
	
	@Column(name = "manager_id")
	private Long manager_id;
	
	@Column(name = "state", nullable = false)
	private int state;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "review")
	private String review;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(LocalDateTime upload_date) {
		this.upload_date = upload_date;
	}
	public LocalDateTime getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(LocalDateTime limit_date) {
		this.limit_date = limit_date;
	}
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
