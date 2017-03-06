package com.revature.ERS;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

public class TestAddReimbursement {

	@Test
	public void addingSpecificDataToTheReimbursementTableAndItShouldResultInShowingNewDataInTheDataBase() {
		String sql = "INSERT INTO ERS_REIMBURSEMENT "
				+ "(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_RECEIPT,"
				+ "REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setInt(1, 1600);														// AMOUNT
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));			// TIME SUBMITTED
			stmt.setString(3, "Where do you live?");	// DESCRIPTION
			stmt.setNull(4, java.sql.Types.BLOB);										// RECEIPT
			stmt.setInt(5, 10);															// AUTHOR
			stmt.setInt(6, 1);															// STATUS ID
			stmt.setInt(7, 2);															// TYPE ID
			stmt.executeUpdate();
			System.out.println("New Reimbursement Sucessful!");
		} catch (SQLException se) {
			System.out.println("Insertion fail!");
			se.printStackTrace();
		} 
	}
	
	//@Test
	public void addingDataToTheReimbursementTableShouldResultDataShowingInDatabase() {
		String sql = "INSERT INTO ERS_REIMBURSEMENT "
				+ "(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT,"
				+ "REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int reimbAmount = 2;
		Timestamp reimbSubmitted = new Timestamp(System.currentTimeMillis());
		Timestamp reimbResolved = null;
		String reimbDesc = null;
		Blob reimReceipt = null;
		int reimbAuth = 1;
		int reimbResolver = 11;
		int reimbStatusId = 1;
		int reimbTypeId = 3;
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reimbAmount);
			stmt.setTimestamp(2, reimbSubmitted);
			stmt.setTimestamp(3, reimbResolved);
			stmt.setString(4, reimbDesc);
			stmt.setBlob(5, reimReceipt);			
			stmt.setInt(6, reimbAuth);
			stmt.setInt(7, reimbResolver);
			stmt.setInt(8, reimbStatusId);
			stmt.setInt(9, reimbTypeId);
			stmt.executeUpdate();
			System.out.println("Reimbursement insertion successful!");
		} catch (SQLException se) {
			System.out.println("Reimbursement insertion unsuccessful!");
			se.printStackTrace();
		}
	}
}
