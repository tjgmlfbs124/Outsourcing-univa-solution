package com.tathink.univa.controller.form;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.Subject;

public class SolutionForm {
	private String title;
	private String nickname;
	private String password;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime limit_date;
	private List<ProblemForm> problems;
	private List<SubjectForm> subjects;
	private int is_new;
	private int is_detail;
	private String language;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	public LocalDateTime getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(LocalDateTime limit_date) {
		this.limit_date = limit_date;
	}
	public List<ProblemForm> getProblems() {
		return problems;
	}
	public void setProblems(List<ProblemForm> problems) {
		this.problems = problems;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getIsNew() {
		return is_new;
	}
	public void setIsNew(int isNew) {
		this.is_new = isNew;
	}
	public int getIsDetail() {
		return is_detail;
	}
	public void setIsDetail(int isDetail) {
		this.is_detail = isDetail;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<SubjectForm> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectForm> subjects) {
		this.subjects = subjects;
	}
}
