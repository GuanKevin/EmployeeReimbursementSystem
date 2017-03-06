<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LoginPage</title>

</head>
<body>
	<form action="loginERS.do" method="post">
		<input type="text" name="Username"/> 
		<input type="password" name="pass"/><br />
		<input type="submit" value="Log In"/><br />
	</form>
	<c:if test = "${not empty loginToERS}">
		<c:out value = "${loginToERS }"/>
	</c:if>
	<c:choose>
		<c:when test = "${not empty val}">
			
		</c:when>
	</c:choose>
</body>
</html>