package com.revature.ERS.Data;

public class Status {
	private int reimbStatusId;
	private String reimbStatus;
	
	public Status() {
		// TODO Auto-generated constructor stub
	}

	public Status(int reimbStatusId, String reimbStatus) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	@Override
	public String toString() {
		return "Status [reimbStatusId=" + reimbStatusId + ", reimbStatus=" + reimbStatus + "]";
	}
}
