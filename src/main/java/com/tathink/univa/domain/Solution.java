package com.tathink.univa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity(name = "question")
@DynamicInsert
public class Solution {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "UNSGINED INT")
	private int id;
	
	@Column(name = "visible")
	private int visible = 1;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name="nickname", nullable = false)
	private String nickname;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "upload_date")
	private LocalDateTime upload_date;
	
	@Column(name ="limit_date")
	private LocalDateTime limit_date;
	
	@ManyToOne(targetEntity=Manager.class, fetch=FetchType.EAGER)
	@JoinColumn(name="manager_id")
	private Manager manager_id;
	
	@OneToOne
	@JoinColumn(name = "state")
	private SolutionState state;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "review", columnDefinition = "TEXT")
	private String review;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
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
	
	public Manager getManager_id() {
		return manager_id;
	}
	public void setManager_id(Manager manager_id) {
		this.manager_id = manager_id;
	}
	
	public SolutionState getState() {
		return state;
	}
	public void setState(SolutionState state) {
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
