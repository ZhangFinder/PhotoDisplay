package com.zjq.model;

public class User {
	private Integer id = -1;  //�������ݿ��Զ���Ŷ���
	private String username = "";//�����û����ƶ���
	private String password = ""; //�����û���¼�������
	private String email = "";   //�����û�email��ַ����
	private String question = "";  //�����û��������
	private String answer = "";  //�����û��𰸶���
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
