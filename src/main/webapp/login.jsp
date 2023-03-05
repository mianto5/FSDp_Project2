<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learner's Academy Log In</title>
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
					<%
					String id = (String) session.getAttribute("id");
					if (id == null) {
					%>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a>
					</li>
					<%
					}
					else if (id == "admin") {
					%>
					<li class="nav-item"><a class="nav-link" href="classes.jsp">Classes</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="teachers.jsp">Teachers</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="students">Students</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a>
					</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container">
		<div>
			<div class="wrapper fadeInDown">
				<div id="formContent">
					<div class="fadeIn first">
						<h2 class='sign'>Log In</h2>
					</div>
					<form action="login" method="POST">
						<input type="text" id="login" class="fadeIn second" name="user"
							placeholder="User"/> 
						<input type="password" id="password" class="fadeIn third" name="password"
							placeholder="Password" /> 
						<input type="submit" class="fadeIn fourth" value="Log In" />
					</form>
					<%
					String error = request.getParameter("error");
					if(error !=  null){
					%>
					<div class="error"><%= error %></div>
					<%} %>
				</div>
			</div>
		</div>
	</div>

</body>
</html>