package com.revature.ERS;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.revature.ERS.DAO.ReimbursementDAO;
import com.revature.ERS.Data.User;

public class TestGetAllUsers {

	@Test
	public void queryDatabaseAndDisplayUserInfo() throws SQLException {
		List<User> user = new ReimbursementDAO().getAllUsers();
		
		for (User list: user) {
			System.out.println(list);
		}
	}

}
