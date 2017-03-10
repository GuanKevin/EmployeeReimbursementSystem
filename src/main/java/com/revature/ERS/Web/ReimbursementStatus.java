package com.revature.ERS.Web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ERS.Data.Facade;

public class ReimbursementStatus extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int typeId = Integer.parseInt(req.getParameter("FilterRequestButton"));
		System.out.println("Type ID: " + typeId);
		
		try {
			req.getSession().setAttribute("getReim", new Facade().filterReimbursement(typeId));
		} catch (SQLException e) {
			System.out.println("ReimbursementStatus Class Error");
			e.printStackTrace();
		}
		
		resp.sendRedirect("ReimbursementStatusFilterDisplay.jsp");
	}
}
