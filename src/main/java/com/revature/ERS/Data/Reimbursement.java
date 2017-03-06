package com.revature.ERS.Data;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Reimbursement Java Bean Class 
 * 
 * @author kevgu
 *
 */
public class Reimbursement {
	private int reimbId;
	private int reimbAmount;
	private Timestamp timeSubmitted;		// Using SQL time stamp
	private Timestamp timeResolved;			// Using SQL time stamp
	private String desc;
	private Blob receipt;
	private User author;					// Person requesting reimbursement
	private User resolver;					// Manager that approve/deny reimbursement
	private Status statusId;				// Whether it is approved, denied or pending
	private Type typeId;					// Lodging, Travel, Food, Other
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}
	
	public Reimbursement(int reimbId, int reimbAmount, Timestamp timeSubmitted, Timestamp timeResolved, String desc,
			Blob receipt, User author, User resolver, Status statusId, Type typeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.desc = desc;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}
	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}
	public Timestamp getTimeResolved() {
		return timeResolved;
	}
	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Blob getReceipt() {
		return receipt;
	}
	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public User getResolver() {
		return resolver;
	}
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	public Status getStatusId() {
		return statusId;
	}
	public void setStatusId(Status statusId) {
		this.statusId = statusId;
	}
	public Type getTypeId() {
		return typeId;
	}
	public void setTypeId(Type typeId) {
		this.typeId = typeId;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", timeSubmitted=" + timeSubmitted
				+ ", timeResolved=" + timeResolved + ", desc=" + desc + ", receipt=" + receipt + ", author=" + author
				+ ", resolver=" + resolver + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}
}
