package com.revature.ERS;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.ERS.DAO.UserDAO;
import com.revature.ERS.Data.User;

public class TestLogin {

	@Test
	public void login() {
		User user = new User("KEVING", "PASS");
		try {
			new UserDAO(ConnectionFactory.getConnection()).login(user);
			//ID should start with 1. 
			//0 is auto generated variable in object
			if (user.getUserId() == 0)	
				System.out.println("Login test fail!");
			else
				System.out.println("Login test successful!" 
						+ "\n" + user);
		} catch (SQLException e) {
			System.out.println("???");
			e.printStackTrace();
		}
		
	}

}
