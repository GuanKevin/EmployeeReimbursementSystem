package com.revature.ERS.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerViewServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("ApproveDenyButton") != null)
			resp.sendRedirect("UserAccess/ManagerUpdateReimbursementStatus.jsp");
		else if (req.getParameter("FilterRequestButton") != null)
			resp.sendRedirect("UserAccess/Manager.jsp");
		else
			resp.sendRedirect("UserAccess/ManagerView");
	}
}
