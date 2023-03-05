<%@page import="com.bean.LAassign"%>
<%@page import="com.bean.LAclass"%>
<%@page import="com.bean.LAstudent"%>
<%@page import="com.bean.LAsubject"%>
<%@page import="com.bean.LAteacher"%>
<%@page import="com.hibernate.DBcommunication"%>
<%@page import="java.util.List"%>
<%@page import="java.util.SortedSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LA Class Report</title>
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
	.error{
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
	<%
		List<LAassign> assignList = (List<LAassign>) request.getAttribute("assignList");
		List<LAclass> classList = (List<LAclass>) request.getAttribute("classList");
		List<LAstudent> studentList = (List<LAstudent>) request.getAttribute("studentList");
		List<LAsubject> subjectList = (List<LAsubject>) request.getAttribute("subjectList");
		List<LAteacher> teacherList = (List<LAteacher>) request.getAttribute("teacherList");
		DBcommunication dbCom = new DBcommunication();
	%>
	<div class="container">
		<h3>Class Report</h3>
	</div>
	<% for(LAclass c:classList){
		String cname = c.getCname();
		%><h4><%= cname %></h4>
		<h5>Students</h5>
		<ul><%
		for(LAstudent st:studentList){
			if(st.getCid().equals(c.getCid())){
				String stLname = st.getLname();
				String stFname = st.getFname();
				%><li><%= stLname+" "+stFname %></li><%
			}
		}
		%></ul>
		<h5>Teachers</h5>
		<ul><%
		for(LAassign as:assignList){
			if(as.getCid().equals(c.getCid())){
				%><li><%= dbCom.getNameOfTeachById(as.getTid())+" ("+dbCom.getNameOfSubById(as.getSbid())+")" %></li><%
			}
		}
		%></ul>
		<h5>Subjects</h5>
		<ul><%
		for(LAassign as:assignList){
			if(as.getCid().equals(c.getCid())){
				%><li><%= dbCom.getNameOfSubById(as.getSbid())+" ("+dbCom.getNameOfTeachById(as.getTid())+")" %></li><%
			}
		}
		%></ul><%
	}%>
</body>
</html>