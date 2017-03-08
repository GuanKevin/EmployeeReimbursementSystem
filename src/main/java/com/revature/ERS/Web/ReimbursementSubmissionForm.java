package com.revature.ERS.Web;

import java.io.IOException;
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
		System.out.println("Test: " + Integer.parseInt(req.getParameterValues("ReimAmountBox").toString()));
		reim.setReimbAmount(Integer.parseInt(req.getParameterValues("ReimAmountBox").toString()));
		reim.setTimeResolved(new Timestamp(System.currentTimeMillis()));
		reim.setDesc(req.getParameter("DescBox").toString());
		reim.setReceipt(null);
		User user = new User();
		user.setUserId(Integer.parseInt(req.getSession().getAttribute("empId").toString()));
		reim.setAuthor(user);
		reim.setStatusId(new Status());
		reim.setTypeId(new Type(Integer.parseInt(req.getParameter("TypeBox").toString())));
		
		try {
			new Facade().addReim(reim);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.sendRedirect("/ers/UserAccess/EmployeeView.jsp");
	}
}
