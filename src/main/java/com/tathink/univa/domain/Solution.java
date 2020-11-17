package com.tathink.univa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
	
	@OneToOne(targetEntity=Answer.class, mappedBy="question", cascade = CascadeType.PERSIST)
	private Answer answer;
	
	@OneToMany(mappedBy = "solution") // 오브젝트의 프로퍼티 명!
	private Set<SolutionSubject> subjects;
	
	@OneToMany(targetEntity=SolutionChat.class, mappedBy = "solution", cascade = CascadeType.PERSIST)
	private Set<SolutionChat> chats;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
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
	
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	public Set<SolutionSubject> getSubjects() {
		if( this.subjects == null ) {
			subjects = new HashSet<SolutionSubject>();
		}
		return subjects;
	}
	public void setSubjects(Set<SolutionSubject> subjects) {
		this.subjects = subjects;
	}
	public void addSubject(SolutionSubject subject) {
		this.subjects.add(subject);
	}
	
	public Set<SolutionChat> getChats() {
		if( this.chats == null) {
			this.chats = new HashSet<SolutionChat>();
		}
		return chats;
	}
	public void setChats(Set<SolutionChat> chats) {
		this.chats = chats;
	}
	public void addChat(SolutionChat chat) {
		this.chats.add(chat);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
