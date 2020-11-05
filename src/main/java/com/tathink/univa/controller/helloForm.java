package com.tathink.univa.controller;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;

public class helloForm {
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime limit_date;
	//private Collection<ProblemForm> problems;

	/*public Collection<ProblemForm> getProblems() {
		return problems;
	}
	public void setProblems(Collection<ProblemForm> problems) {
		this.problems = problems;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getLimit_date() {
		return limit_date;
	}

	public void setLimit_date(LocalDateTime limit_date) {
		this.limit_date = limit_date;
	}
	
}
