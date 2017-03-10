package com.revature.ERS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.ERS.ConnectionFactory;
import com.revature.ERS.Data.Role;
import com.revature.ERS.Data.User;

/**
 * User Data Access Object Request for username and password Check if database
 * find matching username. If matching username is found, match password
 * 
 * @author kevgu
 *
 */
public class UserDAO {
	private Connection conn;

	public UserDAO() {
		this.conn = ConnectionFactory.getConnection();
	}

	public UserDAO(Connection conn) {
		setConn(conn);
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Return an user object with all the users credential and information
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public User login(User user) throws SQLException {
		String sql = "SELECT "
				+ "U.ERS_USERS_ID, U.USER_FIRST_NAME, U.USER_LAST_NAME, U.USER_EMAIL, "
				+ "R.ERS_USER_ROLE_ID, R.USER_ROLE "
				+ "FROM ERS_USERS U INNER JOIN ERS_USER_ROLES R "
				+ "ON U.USER_ROLE_ID = R.ERS_USER_ROLE_ID WHERE U.ERS_USERNAME = ?"; 

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			user.setUserId(rs.getInt(1));
			user.setUserFirstName(rs.getString(2));
			user.setUserLastName(rs.getString(3));
			user.setUserEmail(rs.getString(4));
			user.setUserRole(new Role(rs.getInt(5), rs.getString(6)));
		}

		return user;
	}

	/**
	 * If user-name is not found, return false
	 * 
	 * @param username
	 * @return
	 */
	public boolean validateUsername(String username) {
		if (username == null)
			return false;
		return true;
	}

	/**
	 * Check password with the hashed password stored in the database
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean validatePassword(User user) throws SQLException {
		String sql = "SELECT ERS_PASSWORD FROM ERS_USERS WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		ResultSet rs = stmt.executeQuery();

		String hashedPassword = null;

		if (rs.next())
			hashedPassword = rs.getString(1);

		if (BCrypt.checkpw(user.getPassword(), hashedPassword))
			return true;
		else {
			System.out.println(
					"Something went wrong at validatePassword(): " + hashedPassword + " " + user.getPassword());
			return false;
		}
	}

	/**
	 * Reset user's password NoT important Just test method to encrypt password
	 * Using JBCRYPT https://mvnrepository.com/artifact/org.mindrot/jbcrypt/0.3m
	 * 
	 * @param user
	 * @param newPassword
	 * @throws SQLException
	 */
	public void resetPassword(User user, String newPassword) throws SQLException {
		String sql = "UPDATE ERS_USERS SET ERS_PASSWORD = ? WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		String hashedPassword = hashPassword(newPassword);
		stmt.setString(1, hashedPassword);
		stmt.setString(2, user.getUsername());
		stmt.executeQuery();
	}

	/**
	 * Return a hashed string of password parameter
	 * 
	 * @param password
	 * @return
	 */
	private String hashPassword(String password) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		return hashedPassword;
	}

	@Override
	public String toString() {
		return "UserDAO [conn=" + conn + "]";
	}
}