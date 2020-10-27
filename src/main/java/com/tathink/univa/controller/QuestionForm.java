package com.tathink.univa.controller;

import java.time.LocalDateTime;

public class QuestionForm {
	private String title;
	private String content;
	private LocalDateTime limit_date;
	
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
	public LocalDateTime getLimit_date() {
		return limit_date;
	}
	public void setLimit_date(LocalDateTime limit_date) {
		this.limit_date = limit_date;
	}
	
}
