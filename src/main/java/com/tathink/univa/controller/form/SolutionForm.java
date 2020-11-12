package com.tathink.univa.controller.form;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.tathink.univa.domain.Problem;

public class SolutionForm {
	private String title;
	private String nickname;
	private String password;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime limit_date;
	private List<ProblemForm> problems;
	
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
}
