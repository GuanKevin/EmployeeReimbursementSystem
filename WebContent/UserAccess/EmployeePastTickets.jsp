<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee's Past Tickets</title>
</head>
<body>
	<table>
		<tr>
			<th>Reimbursement ID</th>
			<th>Emp_Username</th>
			<th>Reimbursement</th>
			<th>Time Submitted</th>
			<th>Time Resolved</th>
			<th>Description</th>
			<th>Resolver</th>
			<th>Status</th>
			<th>Type</th>
		</tr>
		<!-- for-each artist in request scoped variable, add a new row to table -->
		<c:forEach var="tickets" items="${getReim}">
			<tr>
				<td>${tickets.reimbId}</td>
				<td>${tickets.author.username}</td>
				<td>${tickets.reimbAmount}</td>
				<td>${tickets.timeSubmitted}</td>
				<td>${tickets.timeResolved}</td>
				<td>${tickets.desc}</td>
				<td>${tickets.resolver.username}</td>
				<td>${tickets.statusId.reimbStatus}</td>
				<td>${tickets.typeId.reimbType}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>