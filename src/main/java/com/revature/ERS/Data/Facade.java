package com.revature.ERS.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.ERS.ConnectionFactory;
import com.revature.ERS.DAO.ReimbursementDAO;
import com.revature.ERS.DAO.UserDAO;

public class Facade {
	private Connection conn;
	private UserDAO user;
	private ReimbursementDAO reim;

	public Facade() throws SQLException {
		this.conn = ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		this.user = new UserDAO(conn);
		this.reim = new ReimbursementDAO(conn);
	}

	/**
	 * Calling ReimbursementDAO method
	 * 
	 * @param reim
	 */
	public void addReim(Reimbursement reim) {
		this.reim.addReim(reim);
	}

	/**
	 * Calling ReimbursementDAO method
	 * 
	 * @return
	 */
	public List<Reimbursement> getAllTickets() {
		try {
			return reim.getAllTickets();
		} catch (SQLException e) {
			System.out.println("Facade Class, getAllTickets()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Calling ReimbursementDAO method
	 * 
	 * @param userId
	 * @return
	 */
	public List<Reimbursement> getPastTickets(int userId) {
		try {
			return reim.getPastTickets(userId);
		} catch (SQLException e) {
			System.out.println("Facade Class, getPastTickets()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Calling ReimbursementDAO method
	 * 
	 * @param statusId
	 * @return
	 */
	public List<Reimbursement> filterReimbursement(int statusId) {
		try {
			return reim.filterReimbursement(statusId);
		} catch (SQLException e) {
			System.out.println("Facade Class, filterReimbursement()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Calling ReimbursementDAO method
	 * 
	 * @param reimbStatusId
	 * @param reimbId
	 */
	public void changeStatus(int reimbStatusId, int reimbId, int reimbResolver) {
		try {
			reim.changeStatus(reimbStatusId, reimbId, reimbResolver);
		} catch (SQLException e) {
			System.out.println("Facade Class, changeStatus()");
			e.printStackTrace();
		}
	}

	/**
	 * Calling UserDAO method
	 * 
	 * @param user
	 */
	public User login(User user) {
		try {
			this.user.login(user);
			return user;
		} catch (SQLException e) {
			System.out.println("User Class, login()");
			e.printStackTrace();
			return null;
		}
	}
}