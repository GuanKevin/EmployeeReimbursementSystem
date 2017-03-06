package com.revature.ERS.Data;

public class Role {
	private int ersUserRoleId;
	private String userRole;
	
	public Role() {
	
	}
	
	public Role(int ersUserRoleId, String userRole) {
		this.ersUserRoleId = ersUserRoleId;
		this.userRole = userRole;
	}

	public int getErsUserRoleId() {
		return ersUserRoleId;
	}

	public void setErsUserRoleId(int ersUserRoleId) {
		this.ersUserRoleId = ersUserRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "Role [ersUserRoleId=" + ersUserRoleId + ", userRole=" + userRole + "]";
	}
}
