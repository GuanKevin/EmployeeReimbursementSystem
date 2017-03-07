<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LoginPage</title>

</head>
<body>
<form action = "LoginPage.do" method = "Post">
	Username: <br>
	<input type="text" name="UsernameTextBox" value="Username"> <br> 
	Password: <br>
	<input type="password" name="PasswordTextBox" value="Password"> <br>
	<input type="submit" name="SubmitTextBox" value="Submit"> <br>
</form>	
</body>
</html>