<%@page import="com.bean.LAassign"%>
<%@page import="com.bean.LAclass"%>
<%@page import="com.bean.LAsubject"%>
<%@page import="com.bean.LAstudent"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LA Edit Assignment</title>
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
		<h5>Update the assignment</h5>
	</div>
	<div class="container">
		<form action="editAssign" method="post">

			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="form-group">
						<label for="aid">Assignment ID: </label> <input type="text" readonly="readonly"
							class="form-control" id="aid" name="aid" value=${requestScope.assign.aid }>
					</div>
					<div class="form-group">
						<label for="cid">Class ID: </label> <input type="text" readonly="readonly"
							class="form-control" id="cid" name="cid" value=${requestScope.assign.cid }>
					</div>
					<div class="form-group">
						<label for="sbid">Subject ID: </label> 
						<select class="form-control" id="sbid" name="sbid">
							<c:forEach var="sb" items="${requestScope.subjectList }">
								<option value="${sb.sbid}">${sb.sname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="tid">Teacher ID: </label> 
						<select class="form-control" id="tid" name="tid">
							<c:forEach var="te" items="${requestScope.teacherList }">
								<option value="${te.tid}">${te.lname} ${te.fname}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</div>
			</div>
		</form>
	</div>
	</body>
</html>