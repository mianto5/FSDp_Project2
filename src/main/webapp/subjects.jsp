<%@page import="com.bean.LAsubject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LA Subjects</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
	<style>
	.message{
		color:red;
	}
	</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">LA</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="classReport">Class Report</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="classes">Classes</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="subjects">Subjects</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="teachers">Teachers</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="students">Students</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="assign">Assignments</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a>
					</li>
					
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h5>Add a new subject</h5>
	</div>
	<div class="container">
		<%
		String message = (String) request.getAttribute("message");
		if(message !=  null){%>
			<div class="mesagge"><%= message %></div>
		<%} %>
		<form action="subjects" method="post">

			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="form-group">
						<label for="sname">Subject Name: </label> <input type="text"
							class="form-control" id="sname" placeholder="Enter Subject Name" name="sname">
					</div>
					<div>
						<input type="submit" class="btn btn-primary" value="Add subject" />
					</div>
				</div>
			</div>
		</form>
	</div>
	<br>
    <div align="center" class="container">
		<h5>Added subjects</h5>
	</div>
    <div align="center">
        <table class="table" border="1" cellpadding="5">
            <tr>
                <th>Subject Name</th>
                <th>Subject ID</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="sb" items="${requestScope.subjectList }">
				<tr>
					<td>${ sb.sname}</td>
					<td>${ sb.sbid}</td>
					<td><a href="delete?sbid=${ sb.sbid}">Delete</a> </td>
				</tr>
			</c:forEach>
        </table>
    </div>
</body>
</html>