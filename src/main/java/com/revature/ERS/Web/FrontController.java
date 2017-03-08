package com.revature.ERS.Web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		
		switch (requestURI) {
			case "/ers/UserAccess/EmployeeView.do": {
				resp.sendRedirect("/ers/UserAccess/EmployeePastTickets.jsp");
				break;
			}
			case "/ers/UserAccess/EmployeeReimbursementRequest.jsp": {
				
			}
			default: {
				throw new IllegalArgumentException();
			}
		}
	}
}
