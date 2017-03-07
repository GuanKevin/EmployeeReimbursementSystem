package com.revature.ERS.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ERS.Data.Facade;
import com.revature.ERS.Data.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	@Override
	/**
	 * Login page for the user
	 * If user is an employee the user will be forwarded to the employee page
	 * The employee will be shown all past reimbursement request 
	 * and will be able to make reimbursement request
	 * If the user is a manager, the user will be forwarded to the manager page
	 * The manager will be shown all reimbursement request and will be able
	 * to approve, deny or ignore reimbursement requests
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User user = new Facade().login(new User(req.getParameter("UsernameTextBox"), req.getParameter("PasswordTextBox")));
			PrintWriter out = resp.getWriter();
			
			if (user.getUserRole() == null) {
				req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
			}
			else if (user.getUserRole().getErsUserRoleId() == 1) {				
				HttpSession session = req.getSession();
				session.setAttribute("empId", user.getUserId());
				session.setAttribute("empUserName", req.getParameter("name"));
				session.setAttribute("empFirstName", user.getUserFirstName());
				session.setAttribute("empLastName", user.getUserLastName());
				session.setAttribute("getReim", new Facade().filterReimbursement(user.getUserId()));
				resp.sendRedirect("UserAccess/EmployeeView.jsp");
			} else if (user.getUserRole().getErsUserRoleId() == 2) {
				HttpSession session = req.getSession();
				session.setAttribute("managerId", user.getUserId());
				session.setAttribute("managerUserName", req.getParameter("name"));
				session.setAttribute("managerFirstName", user.getUserFirstName());
				session.setAttribute("managerLastName", user.getUserLastName());
				session.setAttribute("getReim", new Facade().filterReimbursement(user.getUserId()));
				resp.sendRedirect("UserAccess/ManagerView.jsp");
			} else {
				out.println("Something went wrong!\nMessage system admin systemAdmin@ERS.com ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
