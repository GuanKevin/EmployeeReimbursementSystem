package com.revature.ERS;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.revature.ERS.DAO.ReimbursementDAO;
import com.revature.ERS.Data.Reimbursement;
import com.revature.ERS.Data.User;

public class TestReimReq {

	@Test
	public void thisShouldReturnAListOfPreviousReimbursementRequest() throws SQLException {
		List<Reimbursement> reimList = new ReimbursementDAO().getAllTickets();
		
		System.out.println(reimList);
	}
	
	//@Test
	public void reimbursementUpdateShouldShowInDatabase() throws SQLException {
		new ReimbursementDAO().changeStatus(3 , 6);
	}
}
