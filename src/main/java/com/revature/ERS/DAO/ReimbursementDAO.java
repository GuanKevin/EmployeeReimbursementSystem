package com.revature.ERS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ERS.ConnectionFactory;
import com.revature.ERS.Data.Reimbursement;
import com.revature.ERS.Data.Status;
import com.revature.ERS.Data.Type;
import com.revature.ERS.Data.User;

public class ReimbursementDAO {
	private Connection conn;
	
	public ReimbursementDAO() {
		this.conn = ConnectionFactory.getConnection();
	}

	public ReimbursementDAO(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Store reimbursement request made by the employee into the database
	 * False return type means the request was unsuccessful depending on
	 * the constraint that was not satisfied
	 * 
	 * @param user
	 * @return
	 */
	public void addReim(Reimbursement reim) {
		String sql = "INSERT INTO ERS_REIMBURSEMENT "
				+ "(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_RECEIPT,"
				+ "REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reim.getReimbAmount());
			stmt.setTimestamp(2, reim.getTimeSubmitted());
			stmt.setString(3, reim.getDesc());
			stmt.setBlob(4, reim.getReceipt());			
			stmt.setInt(5, reim.getAuthor().getUserId());
			stmt.setInt(6, reim.getStatusId().getReimbStatusId());
			stmt.setInt(7, reim.getTypeId().getReimbTypeId());
			stmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} 
	}
	
	/**
	 * Manager is able to view all the tickets that are available
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public List<Reimbursement> getAllTickets() throws SQLException {
		String sql = "SELECT "
				+ "R.REIMB_ID AS REIMBID, R.REIMB_AMOUNT AS REIMBAMOUNT, R.REIMB_SUBMITTED AS TIMESUB, R.REIMB_RESOLVED AS TIMERES, R.REIMB_RESOLVER AS RESOLV, R.REIMB_DESCRIPTION AS DESCRIPTION, R.REIMB_RECEIPT AS RECEIPT, "
				+ "U.ERS_USERS_ID AS USERID, U.ERS_USERNAME AS USERNAME, U.ERS_PASSWORD AS PASS, U.USER_FIRST_NAME AS USERFIRSTNAME, U.USER_LAST_NAME AS USERLASTNAME, U.USER_EMAIL AS EMAIL, "
				+ "UR.ERS_USER_ROLE_ID AS USERROLEID, UR.USER_ROLE AS USERROLE, "
				+ "S.REIMB_STATUS AS REIMBSTATUS, S.REIMB_STATUS_ID AS STATUSID, "
				+ "T.REIMB_TYPE AS REIMBTYPE, T.REIMB_TYPE_ID AS TYPEID, "
				+ "RU.ERS_USERS_ID AS RESOLVERUSERSID, RU.ERS_USERNAME AS RESOLVERUSERNAME, RU.ERS_PASSWORD AS RESOLVERPASSWORD, RU.USER_FIRST_NAME AS RESOLVERUSERFIRSTNAME, RU.USER_LAST_NAME AS RESOLVERLASTNAME, RU.USER_EMAIL AS RESOLVEREMAIL, "
				+ "RR.ERS_USER_ROLE_ID AS RESOLVERROLEID, RR.USER_ROLE AS RESOLVERROLE "
				+ "FROM ERS_REIMBURSEMENT R  "
				+ "INNER JOIN ERS_USERS U ON R.REIMB_AUTHOR = U.ERS_USERS_ID  "
				+ "INNER JOIN ERS_REIMBURSEMENT_STATUS S ON S.REIMB_STATUS_ID = R.REIMB_STATUS_ID "
				+ "INNER JOIN ERS_REIMBURSEMENT_TYPE T ON T.REIMB_TYPE_ID = R.REIMB_TYPE_ID "
				+ "INNER JOIN ERS_USER_ROLES UR ON UR.ERS_USER_ROLE_ID = U.USER_ROLE_ID "
				+ "LEFT JOIN ERS_USERS RU ON RU.ERS_USERS_ID = R.REIMB_RESOLVER "
				+ "LEFT JOIN ERS_USER_ROLES RR ON RR.ERS_USER_ROLE_ID = RU.USER_ROLE_ID ";
		
		List<Reimbursement> listReim = new ArrayList<Reimbursement>();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Reimbursement reim;
		User author, resolver;
		Status status;
		Type type;
		
		while (rs.next()) {
			//Creating a new Reimbursement object 
			reim = new Reimbursement();
			reim.setReimbId(rs.getInt("REIMBID"));
			reim.setReimbAmount(rs.getInt("REIMBAMOUNT"));
			reim.setTimeSubmitted(rs.getTimestamp("TIMESUB"));
			reim.setTimeResolved(rs.getTimestamp("TIMERES"));
			reim.setDesc(rs.getString("DESCRIPTION"));
			reim.setReceipt(rs.getBlob("RECEIPT"));
			
			//Creating a new User (author) object to add to Reimbursement
			author = new User();
			author.setUserId(rs.getInt("USERID"));
			author.setUsername(rs.getString("USERNAME"));
			author.setPassword(rs.getString("PASS"));
			author.setUserFirstName(rs.getString("USERFIRSTNAME"));
			author.setUserLastName(rs.getString("USERLASTNAME"));
			author.setUserEmail(rs.getString("EMAIL"));
			reim.setAuthor(author);
			
			//Creating a new User (resolver) object to add to Reimbursement
			resolver = new User();
			resolver.setUserId(rs.getInt("RESOLVERUSERSID"));
			resolver.setUsername(rs.getString("RESOLVERUSERNAME"));
			resolver.setPassword(rs.getString("RESOLVERPASSWORD"));
			resolver.setUserFirstName(rs.getString("RESOLVERUSERFIRSTNAME"));
			resolver.setUserLastName(rs.getString("RESOLVERLASTNAME"));
			resolver.setUserEmail(rs.getString("RESOLVEREMAIL"));
			reim.setResolver(resolver);
					
			//Creating new Status object
			status = new Status();
			status.setReimbStatus(rs.getString("REIMBSTATUS"));
			status.setReimbStatusId(rs.getInt("STATUSID"));
			reim.setStatusId(status);
		
			//Creating a new Type object
			type = new Type();
			type.setReimbType(rs.getString("REIMBTYPE"));
			type.setReimbTypeId(rs.getInt("TYPEID"));
			reim.setTypeId(type);
			
			//Adding Reimbursement object to the list
			listReim.add(reim);
		}
		
		return listReim;
	}
	
	/**
	 * Returns a list of all reimbursement made by the employee
	 * 
	 * R = ERS_REIMBURSEMENT
	 * U = ERS_USERS
	 * S = ERS_REIMBURSEMENT_STATUS
	 * T = ERS_REIMBURSEMENT_TYPE
	 * RU = ERS_USERS Resolver
	 * UR = ERS_USERS Resolver
	 * RR = ERS_REIMBURSEMENT Resolver
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<Reimbursement> getPastTickets(int userId) throws SQLException {
		String sql = "SELECT "
				+ "R.REIMB_ID AS REIMBID, R.REIMB_AMOUNT AS REIMBAMOUNT, R.REIMB_SUBMITTED AS TIMESUB, R.REIMB_RESOLVED AS TIMERES, R.REIMB_RESOLVER AS RESOLV, R.REIMB_DESCRIPTION AS DESCRIPTION, R.REIMB_RECEIPT AS RECEIPT, "
				+ "U.ERS_USERS_ID AS USERID, U.ERS_USERNAME AS USERNAME, U.ERS_PASSWORD AS PASS, U.USER_FIRST_NAME AS USERFIRSTNAME, U.USER_LAST_NAME AS USERLASTNAME, U.USER_EMAIL AS EMAIL, "
				+ "UR.ERS_USER_ROLE_ID AS USERROLEID, UR.USER_ROLE AS USERROLE, "
				+ "S.REIMB_STATUS AS REIMBSTATUS, S.REIMB_STATUS_ID AS STATUSID, "
				+ "T.REIMB_TYPE AS REIMBTYPE, T.REIMB_TYPE_ID AS TYPEID, "
				+ "RU.ERS_USERS_ID AS RESOLVERUSERSID, RU.ERS_USERNAME AS RESOLVERUSERNAME, RU.ERS_PASSWORD AS RESOLVERPASSWORD, RU.USER_FIRST_NAME AS RESOLVERUSERFIRSTNAME, RU.USER_LAST_NAME AS RESOLVERLASTNAME, RU.USER_EMAIL AS RESOLVEREMAIL, "
				+ "RR.ERS_USER_ROLE_ID AS RESOLVERROLEID, RR.USER_ROLE AS RESOLVERROLE "
				+ "FROM ERS_REIMBURSEMENT R  "
				+ "INNER JOIN ERS_USERS U ON R.REIMB_AUTHOR = U.ERS_USERS_ID  "
				+ "INNER JOIN ERS_REIMBURSEMENT_STATUS S ON S.REIMB_STATUS_ID = R.REIMB_STATUS_ID "
				+ "INNER JOIN ERS_REIMBURSEMENT_TYPE T ON T.REIMB_TYPE_ID = R.REIMB_TYPE_ID "
				+ "INNER JOIN ERS_USER_ROLES UR ON UR.ERS_USER_ROLE_ID = U.USER_ROLE_ID "
				+ "LEFT JOIN ERS_USERS RU ON RU.ERS_USERS_ID = R.REIMB_RESOLVER "
				+ "LEFT JOIN ERS_USER_ROLES RR ON RR.ERS_USER_ROLE_ID = RU.USER_ROLE_ID "
				+ "WHERE U.ERS_USERS_ID = ?";
		
		List<Reimbursement> listReim = new ArrayList<Reimbursement>();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		Reimbursement reim;
		User author, resolver;
		Status status;
		Type type;
		
		while (rs.next()) {
			//Creating a new Reimbursement object 
			reim = new Reimbursement();
			reim.setReimbId(rs.getInt("REIMBID"));
			reim.setReimbAmount(rs.getInt("REIMBAMOUNT"));
			reim.setTimeSubmitted(rs.getTimestamp("TIMESUB"));
			reim.setTimeResolved(rs.getTimestamp("TIMERES"));
			reim.setDesc(rs.getString("DESCRIPTION"));
			reim.setReceipt(rs.getBlob("RECEIPT"));
			
			//Creating a new User (author) object to add to Reimbursement
			author = new User();
			author.setUserId(rs.getInt("USERID"));
			author.setUsername(rs.getString("USERNAME"));
			author.setPassword(rs.getString("PASS"));
			author.setUserFirstName(rs.getString("USERFIRSTNAME"));
			author.setUserLastName(rs.getString("USERLASTNAME"));
			author.setUserEmail(rs.getString("EMAIL"));
			reim.setAuthor(author);
			
			//Creating a new User (resolver) object to add to Reimbursement
			resolver = new User();
			resolver.setUserId(rs.getInt("RESOLVERUSERSID"));
			resolver.setUsername(rs.getString("RESOLVERUSERNAME"));
			resolver.setPassword(rs.getString("RESOLVERPASSWORD"));
			resolver.setUserFirstName(rs.getString("RESOLVERUSERFIRSTNAME"));
			resolver.setUserLastName(rs.getString("RESOLVERLASTNAME"));
			resolver.setUserEmail(rs.getString("RESOLVEREMAIL"));
			reim.setResolver(resolver);
					
			//Creating new Status object
			status = new Status();
			status.setReimbStatus(rs.getString("REIMBSTATUS"));
			status.setReimbStatusId(rs.getInt("STATUSID"));
			reim.setStatusId(status);
		
			//Creating a new Type object
			type = new Type();
			type.setReimbType(rs.getString("REIMBTYPE"));
			type.setReimbTypeId(rs.getInt("TYPEID"));
			reim.setTypeId(type);
			
			//Adding Reimbursement object to the list
			listReim.add(reim);
		}
		
		return listReim;
	}
	
	/**
	 * Filter reimbursement by status id which refers 
	 * approve, deny, pending
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public List<Reimbursement> filterReimbursement(int statusId) throws SQLException {
		String sql = "SELECT "
				+ "R.REIMB_ID AS REIMBID, R.REIMB_AMOUNT AS REIMBAMOUNT, R.REIMB_SUBMITTED AS TIMESUB, R.REIMB_RESOLVED AS TIMERES, R.REIMB_RESOLVER AS RESOLV, R.REIMB_DESCRIPTION AS DESCRIPTION, R.REIMB_RECEIPT AS RECEIPT, "
				+ "U.ERS_USERS_ID AS USERID, U.ERS_USERNAME AS USERNAME, U.ERS_PASSWORD AS PASS, U.USER_FIRST_NAME AS USERFIRSTNAME, U.USER_LAST_NAME AS USERLASTNAME, U.USER_EMAIL AS EMAIL, "
				+ "UR.ERS_USER_ROLE_ID AS USERROLEID, UR.USER_ROLE AS USERROLE, "
				+ "S.REIMB_STATUS AS REIMBSTATUS, S.REIMB_STATUS_ID AS STATUSID, "
				+ "T.REIMB_TYPE AS REIMBTYPE, T.REIMB_TYPE_ID AS TYPEID, "
				+ "RU.ERS_USERS_ID AS RESOLVERUSERSID, RU.ERS_USERNAME AS RESOLVERUSERNAME, RU.ERS_PASSWORD AS RESOLVERPASSWORD, RU.USER_FIRST_NAME AS RESOLVERUSERFIRSTNAME, RU.USER_LAST_NAME AS RESOLVERLASTNAME, RU.USER_EMAIL AS RESOLVEREMAIL, "
				+ "RR.ERS_USER_ROLE_ID AS RESOLVERROLEID, RR.USER_ROLE AS RESOLVERROLE "
				+ "FROM ERS_REIMBURSEMENT R  "
				+ "INNER JOIN ERS_USERS U ON R.REIMB_AUTHOR = U.ERS_USERS_ID  "
				+ "INNER JOIN ERS_REIMBURSEMENT_STATUS S ON S.REIMB_STATUS_ID = R.REIMB_STATUS_ID "
				+ "INNER JOIN ERS_REIMBURSEMENT_TYPE T ON T.REIMB_TYPE_ID = R.REIMB_TYPE_ID "
				+ "INNER JOIN ERS_USER_ROLES UR ON UR.ERS_USER_ROLE_ID = U.USER_ROLE_ID "
				+ "LEFT JOIN ERS_USERS RU ON RU.ERS_USERS_ID = R.REIMB_RESOLVER "
				+ "LEFT JOIN ERS_USER_ROLES RR ON RR.ERS_USER_ROLE_ID = RU.USER_ROLE_ID "
				+ "WHERE R.REIMB_STATUS_ID = ?";
		
		List<Reimbursement> listReim = new ArrayList<Reimbursement>();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, statusId);
		ResultSet rs = stmt.executeQuery();
		Reimbursement reim;
		User author, resolver;
		Status status;
		Type type;
		
		while (rs.next()) {
			//Creating a new Reimbursement object 
			reim = new Reimbursement();
			reim.setReimbId(rs.getInt("REIMBID"));
			reim.setReimbAmount(rs.getInt("REIMBAMOUNT"));
			reim.setTimeSubmitted(rs.getTimestamp("TIMESUB"));
			reim.setTimeResolved(rs.getTimestamp("TIMERES"));
			reim.setDesc(rs.getString("DESCRIPTION"));
			reim.setReceipt(rs.getBlob("RECEIPT"));
			
			//Creating a new User (author) object to add to Reimbursement
			author = new User();
			author.setUserId(rs.getInt("USERID"));
			author.setUsername(rs.getString("USERNAME"));
			author.setPassword(rs.getString("PASS"));
			author.setUserFirstName(rs.getString("USERFIRSTNAME"));
			author.setUserLastName(rs.getString("USERLASTNAME"));
			author.setUserEmail(rs.getString("EMAIL"));
			reim.setAuthor(author);
			
			//Creating a new User (resolver) object to add to Reimbursement
			resolver = new User();
			resolver.setUserId(rs.getInt("RESOLVERUSERSID"));
			resolver.setUsername(rs.getString("RESOLVERUSERNAME"));
			resolver.setPassword(rs.getString("RESOLVERPASSWORD"));
			resolver.setUserFirstName(rs.getString("RESOLVERUSERFIRSTNAME"));
			resolver.setUserLastName(rs.getString("RESOLVERLASTNAME"));
			resolver.setUserEmail(rs.getString("RESOLVEREMAIL"));
			reim.setResolver(resolver);
					
			//Creating new Status object
			status = new Status();
			status.setReimbStatus(rs.getString("REIMBSTATUS"));
			status.setReimbStatusId(rs.getInt("STATUSID"));
			reim.setStatusId(status);
		
			//Creating a new Type object
			type = new Type();
			type.setReimbType(rs.getString("REIMBTYPE"));
			type.setReimbTypeId(rs.getInt("TYPEID"));
			reim.setTypeId(type);
			
			//Adding Reimbursement object to the list
			listReim.add(reim);
		}
		
		return listReim;
	}
	
	/**
	 * Update reimbursement record on reimbursement status on approved or denied 
	 * 
	 * @param reimbStatusId
	 * @param reimbId
	 * @throws SQLException
	 */
	public void changeStatus(int reimbStatusId, int reimbId) throws SQLException {
		String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ? WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimbStatusId);
		stmt.setInt(2, reimbId);
		stmt.executeUpdate();
	}
	
	/**
	 * Return all users from the database
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getAllUsers() throws SQLException {
		String sql = "SELECT * FROM ERS_USERS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<User> userList = new ArrayList<User>();
		
		while (rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt(1));
			userList.add(user);
		}
		
		return userList;
	}
}
