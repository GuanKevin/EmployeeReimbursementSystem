package com.revature.ERS.Web;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ERS.Data.Facade;

public class CueController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String response = req.getParameter("cueButton").toString();
		int reimbId = Integer.parseInt(req.getParameter("status").substring(1, req.getParameter("status").length() - 1));
		System.out.println("Reimbursement ID: " + reimbId
				+ "\nManager ID: " + Integer.parseInt(req.getSession().getAttribute("managerId").toString()));
		
		if (req.getParameterMap().containsKey("status")) {	
			if (req.getParameter("cueButton").equals("Approve"))
				try {
					new Facade().changeStatus(2, reimbId, Integer.parseInt(req.getSession().getAttribute("managerId").toString())); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				try {
					new Facade().changeStatus(3, reimbId, Integer.parseInt(req.getSession().getAttribute("managerId").toString())); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} else {
			System.out.println("Deny approve went wrong");
		}
		
		resp.sendRedirect("/ers/UserAccess/CueController.jsp");
	}
}
