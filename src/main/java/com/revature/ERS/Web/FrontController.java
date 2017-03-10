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
				resp.sendRedirect("/ers/UserAccess/EmployeeView.jsp");
				break;
			}
			case "/ers/UserAccess/ReimbursementSubmissionForm.do": {
				new ReimbursementSubmissionForm().doPost(req, resp);
				break;
			}
			case "/ers/UserAccess/EmployeeView.jsp": {
				req.getRequestDispatcher("EmployeeReimbursementRequest.do").forward(req, resp);
				break;
			}
			case "/ers/UserAccess/CueController.do": {
				new CueController().doPost(req, resp);
				break;
			}
			case "/ers/UserAccess/ReimbursementStatusFilterDisplay.do": {
				new ReimbursementStatus().doPost(req, resp);
				break;
			}
			default: {
				System.out.println(requestURI);
				throw new IllegalArgumentException();
			}
		}
	}
}
