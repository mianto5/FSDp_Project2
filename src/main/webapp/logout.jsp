<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LA Logout</title>
</head>
<body>
	<%
	session.removeAttribute("id");
	session.invalidate();
	response.sendRedirect("index.jsp");
	%>
</body>
</html>