package com.tathink.univa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Column(columnDefinition = "UNSIGNED INT")
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
	private Manager manager;
	
	@OneToOne
	@JoinColumn(name = "state")
	private SolutionState state;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "review", columnDefinition = "TEXT")
	private String review;
	
	@OneToMany(targetEntity=Problem.class, mappedBy="question_id", cascade = CascadeType.PERSIST)
	private Collection<Problem> problem;
	
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
		return manager;
	}
	public void setManager_id(Manager manager) {
		this.manager = manager;
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
	
	public Collection<Problem> getProblem() {
		if( problem == null ) {
			problem = new ArrayList<Problem>();
		}
		return problem;
	}
	public void addProblem(Problem problem) {
		Collection<Problem> mProblem = getProblem();
		mProblem.add(problem);
	}
	public void setProblem(Collection<Problem> problem) {
		this.problem = problem;
	}
	
}
