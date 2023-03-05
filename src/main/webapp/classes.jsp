<%@page import="com.bean.LAassign"%>
<%@page import="com.bean.LAclass"%>
<%@page import="com.bean.LAstudent"%>
<%@page import="com.bean.LAsubject"%>
<%@page import="com.hibernate.DBcommunication"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
    prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learner's Academy Classes</title>
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
					<li class="nav-item"><a class="nav-link" href="classes.">Classes</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="teachers.jsp">Teachers</a>
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
		List<LAsubject> subjectList = (List<LAsubject>) request.getAttribute("subjectList");
		List<LAassign> assignList = (List<LAassign>) request.getAttribute("assignList");
		DBcommunication dbCom = new DBcommunication();
	%>
	<div class="container">
		<h3>Class Report</h3>
	</div>
    <div>
    	<c:forEach items="${requestScope.classList}" var="cl">
    		<h4>${ cl.cname}</h4>
    		<h5>Students</h5>
    		<ul>
    			<c:forEach items="${requestScope.studentList}" var="st">
    				<c:if test="${st.cid == cl.cid}">
						<li>${st.lname} ${st.fname}</li>
					</c:if>
    			</c:forEach>
    		</ul>
    		<h5>Teachers</h5>
    		<br>
    		<h5>Subjects</h5>
    		<ul>
    			<c:forEach items="${requestScope.assignList}" var="a">
    				<c:if test="${a.cid == cl.cid}">
						<li>${a.sbid}</li>
					</c:if>
    			</c:forEach>
    		</ul>
		</c:forEach>
    </div>
</body>
</html>