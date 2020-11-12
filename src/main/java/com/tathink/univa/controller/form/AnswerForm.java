package com.tathink.univa.controller.form;

import java.util.List;

public class AnswerForm {
	private int solution_id;
	private String content;
	private List<AnswerSubForm> answerSub;
	
	public int getSolution_id() {
		return solution_id;
	}
	public void setSolution_id(int solution_id) {
		this.solution_id = solution_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<AnswerSubForm> getAnswerSub() {
		return answerSub;
	}
	public void setAnswerSub(List<AnswerSubForm> answerSub) {
		this.answerSub = answerSub;
	}
}
