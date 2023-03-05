<%@page import="com.bean.LAstudent"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learner's Academy Students</title>
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
					<li class="nav-item"><a class="nav-link" href="classes">Classes</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="teachers.jsp">Teachers</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="students.jsp">Students</a>
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
		<h5>Assign subjects and teachers to classes</h5>
	</div>
	<div class="container">
		<%
		String message = (String) request.getAttribute("message");
		if(message !=  null){%>
			<div class="mesagge"><%= message %></div>
		<%} %>
		<form action="assign" method="post">

			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="form-group">
						<label for="cid">Class ID: </label> <input type="text"
							class="form-control" id="cid" placeholder="Enter Class ID" name="cid">
					</div>

					<div class="form-group">
						<label for="sbid">Subject ID: </label> <input type="text"
							class="form-control" id="sbid" placeholder="Enter Subject ID" name="sbid">
					</div>
					<div class="form-group">
						<label for="tid">Teacher ID: </label> <input type="text"
							class="form-control" id="tid" placeholder="Enter Teacher ID" name="tid">
					</div>
					<div>
						<input type="submit" class="btn btn-primary" value="Assign" />
					</div>
				</div>
			</div>
		</form>
	</div>
	<br>
	<div align="center" class="container">
		<h5>Assigned subjects and teachers</h5>
	</div>
    <div align="center">
        <table class="table" border="1" cellpadding="5">
            <tr>
                <th>Class ID</th>
                <th>Subject ID</th>
                <th>Teacher ID</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="assign" items="${requestScope.assignList }">
				<tr>
					<td>${ assign.cid}</td>
					<td>${ assign.sbid}</td>
					<td>${ assign.tid}</td>
					<td><a href="delete?stid=${ assign.aid}">Delete</a> </td>
				</tr>
			</c:forEach>
        </table>
    </div>
</body>
</html>