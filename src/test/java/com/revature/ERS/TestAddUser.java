package com.revature.ERS;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class TestAddUser {

	@Test
	public void addingDataToTheUserTableShouldResultDataShowingInDatabase() {
		String sql = "INSERT INTO ERS_USERS "
				+ "(ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String username = "ManagerTest";
		String password = BCrypt.hashpw("ManagerTest", BCrypt.gensalt());
		String firstName = "Manager";
		String lastName = "Test";
		String email = "Manager@Test.com";
		int userRoleId = 2;
		
		try {
			PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);	
			stmt.setString(4, lastName);
			stmt.setString(5, email);
			stmt.setInt(6, userRoleId);
			stmt.executeUpdate();
			System.out.println("User insertion successful!");
		} catch (SQLException e) {
			System.out.println("User insertion unsuccessful!");
			e.printStackTrace();
		}
	}

}
