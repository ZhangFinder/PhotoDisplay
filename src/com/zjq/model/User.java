package com.zjq.model;

public class User {
	private Integer id = -1;  //设置数据库自动编号对象
	private String username = "";//设置用户名称对象
	private String password = ""; //设置用户登录密码对象
	private String email = "";   //设置用户email地址对象
	private String question = "";  //设置用户问题对象
	private String answer = "";  //设置用户答案对象
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
