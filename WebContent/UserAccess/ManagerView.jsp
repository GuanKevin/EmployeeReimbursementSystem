<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManagerView</title>
</head>
<body>
	<form action="/ers/UserAccess/CueController.jsp" method="Post">
		Approve/Deny Reimbursement <input type="submit"
			name="ApproveDenyButton" value="check status"> <br>
	</form>
	<form action="ReimbursementStatusFilterDisplay.do"
		method="post">
		Filter Request by Status <br> Pending: 1 <br> Approve: 2 <br>
		Deny: 3 <br> <input type="text" name="FilterRequestButton">
		<input type="submit" name="submitButton" value="submit">
	</form>
</body>
</html>