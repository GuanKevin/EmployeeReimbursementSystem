package com.revature.ERS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import com.revature.ERS.Data.User;

public class TestPassword {
	private Connection conn = ConnectionFactory.getConnection();
	private User user = new User("KEVING", "PASSWORD2");
	
	//@Test
	public void changePassword() throws SQLException {
		String sql = "UPDATE ERS_USERS SET ERS_PASSWORD = ? WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		String newPassword = "test";
		System.out.println("Running change password method"
				+ "\nUsername: " + user.getUsername()
				+ "\nNew password: " + user.getPassword());
		
		String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		stmt.setString(1, hashedPassword);
		stmt.setString(2, user.getUsername());
		stmt.executeQuery();
	}
}
