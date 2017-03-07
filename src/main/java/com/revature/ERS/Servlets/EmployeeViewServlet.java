package com.revature.ERS.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeViewServlet extends HttpServlet{
	@Override
	/**
	 * Allows user to make a reimbursement
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("PastTicketBox") != null)
			resp.sendRedirect("UserAccess/EmployeePastTickets.jsp");
		else if (req.getParameter("ReimbursementBox") != null)
			resp.sendRedirect("UserAccess/EmployeeReimbursementRequest.jsp");
		else
			resp.sendRedirect("UserAccess/EmployeeView");
	}
}
