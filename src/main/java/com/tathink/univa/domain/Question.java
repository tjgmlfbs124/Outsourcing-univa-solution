package com.tathink.univa.domain;

import java.time.LocalDateTime;

public class Question {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime upload_date;
	private LocalDateTime limit_date;
	private Long manager_id;
	private int state;
	private int score;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
