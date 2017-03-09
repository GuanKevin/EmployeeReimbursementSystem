package com.revature.ERS.Web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CueController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String response = req.getParameter("approveButton").toString();
		if (req.getParameterMap().containsKey("status")) {
			System.out.println(req.getParameter("status"));
			
			if (response.equals("Deny")) {
				System.out.println("Denied");
			} else if (response.equals("Approve")) {
				System.out.println("Approved");
			} else {
				PrintWriter out = resp.getWriter();
				out.println("Something went wrong with Approve or Deny buttons");
			}
		}
		

		resp.sendRedirect("/ers/UserAccess/CueController.jsp");
	}
}
