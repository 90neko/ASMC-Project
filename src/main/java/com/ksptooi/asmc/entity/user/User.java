package com.ksptooi.asmc.entity.user;

import com.ksptooi.asmc.common.Common;

public class User {

	

	private Integer id = null;
	private String userName = null;
	private String userPassword = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public boolean isCorrect(String password) {
		
		String selfPwd = this.getUserPassword();
		String inputPwd = Common.md5(this.getUserName()+"|"+password);
		
		if(selfPwd.equals(inputPwd)) {
			return true;
		}
		
		return false;	
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}
	
	
}
