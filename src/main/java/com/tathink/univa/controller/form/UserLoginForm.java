package com.tathink.univa.controller.form;

public class UserLoginForm {
	private int idx;
	private String name;
	private String username;
	private String password;
	private int type;
	private int sol_idx;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("password?:"+password);
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("name?:"+name);
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		System.out.println("type?:"+type);
		this.type = type;
	}
	
	public int getSol_idx() {
		return sol_idx;
	}
	public void setSol_idx(int id) {
		System.out.println("id?:"+id);
		this.sol_idx = id;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		System.out.println("idx?:"+idx);
		this.idx = idx;
	}
}
