package com.jdbc.entity;

import java.io.Serializable;

/*author xdchen
 date 2016/09/25*/

public class User implements Serializable {

	private static final long serialVersionUID = 345678;
	private int id;
	private String username;
	private String password;
	private int flag;// 用来区分是注册还是登陆

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
