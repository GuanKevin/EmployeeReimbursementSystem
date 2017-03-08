package com.revature.ERS.Data;

/**
 * Type Java Bean, table from 
 * 
 * @author kevgu
 *
 */
public class Type {
	private int reimbTypeId;
	private String reimbType;
	
	public Type() {
		
	}
	
	public Type(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public Type(int reimbTypeId, String reimbType) {
		super();
		this.reimbTypeId = reimbTypeId;
		this.reimbType = reimbType;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public String toString() {
		return "Type [reimbTypeId=" + reimbTypeId + ", reimbType=" + reimbType + "]";
	}
}
