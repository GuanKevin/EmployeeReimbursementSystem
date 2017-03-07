package com.revature.ERS.Servlets;

import java.io.IOException;

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
		req.setAttribute("Username", req.getParameter("Username"));
		try {
			Facade facade = new Facade();
			User user = facade.login(new User(req.getParameter("Username"), req.getParameter("Password")));

			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("UserID", user.getUserId());
				session.setAttribute("Username", user.getUsername());
				session.setAttribute("FirstName", user.getUserFirstName());
				session.setAttribute("LastName", user.getUserLastName());
				session.setAttribute("Email", user.getUserEmail());
				session.setAttribute("RoleID", user.getUserRoleId());

				// Check if the user is an employee or a manager to forward them
				// to a different page, employee/manager page
				if (user.getUserRoleId().getErsUserRoleId() == 1) {
					session.setAttribute("ERSReim", facade.getAllTickets());
					resp.sendRedirect("UserAccess/EmployeeView.jsp");
				} else {
					session.setAttribute("ERSReim", facade.getPastTickets(user.getUserId()));
					resp.sendRedirect("UserAcess/ManagerView.jsp");
				}
			} else {
				//If the user and password does not match, deny user login access
				req.setAttribute("access", "deny");
				//Forward the user back to login page
				req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
