<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="ReimbursementSubmissionForm.do" method = "post">
		Reimbursement Request Amount: <input type="text" name="ReimAmountBox" value="42"> <br> 
		Request Description: <input type="text" name="DescBox" value="Reimbursement Test"> <br> 
		Reimbursement Type [1 2 3 4]: <input type="text" name="TypeBox" value="3"> <br>
		<input type = "submit" name = "submitBox" value = "Submit"> <br>
	</form>
</body>
</html>