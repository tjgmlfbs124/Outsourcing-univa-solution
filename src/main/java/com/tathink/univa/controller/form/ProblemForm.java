package com.tathink.univa.controller.form;

import org.springframework.web.multipart.MultipartFile;

public class ProblemForm {
	private int number;
	private String text;
	private MultipartFile file;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
