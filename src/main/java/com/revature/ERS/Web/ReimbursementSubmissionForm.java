package com.revature.ERS.Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ERS.Data.Facade;
import com.revature.ERS.Data.Reimbursement;
import com.revature.ERS.Data.Status;
import com.revature.ERS.Data.Type;
import com.revature.ERS.Data.User;

public class ReimbursementSubmissionForm extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reimbursement reim = new Reimbursement();
		reim.setReimbAmount(Double.parseDouble(req.getParameter("ReimAmountBox")));
		reim.setDesc(req.getParameter("DescBox").toString());
		reim.setReceipt(null);
		User user = new User();
		user.setUserId(Integer.parseInt(req.getSession().getAttribute("empId").toString()));
		reim.setAuthor(user);
		reim.setStatusId(new Status());
		reim.setTypeId(new Type(Integer.parseInt(req.getParameter("TypeBox").toString())));
		
		try {
			new Facade().addReim(reim);
			req.getSession().setAttribute("getReim", new Facade().getPastTickets(user.getUserId()));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		resp.sendRedirect("/ers/UserAccess/EmployeeView.jsp");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HEY");
		this.doPost(req, resp);
	}
}
