package com.revature.ERS.Data;

/**
 * User JavaBeans, table ERS_USERS from ERS Database
 * 
 * @author kevgu
 *
 */
public class User {
	private int userId;
	private String username;
	private String password;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private Role userRoleId;

	public User() {

	}

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Role getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Role userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", userPassword=" + password + ", userFirstName=" + userFirstName 
				+ ", userLastName=" + userLastName + ", userEmail=" + userEmail + ", userRoleId=" + userRoleId + "]";
	}
}
