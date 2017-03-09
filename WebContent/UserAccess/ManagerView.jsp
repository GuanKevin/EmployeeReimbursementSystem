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
			name="ApproveDenyButton" value="approveDeny"> <br>
	</form>
	<form action="" method="Post">
		Filter Request by Status <input type="submit"
			name="FilterRequestButton" value="filterRequest"> <br>
	</form>
</body>
</html>