<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/import/taglib.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<spring:url value="/"/>'>NEON</a>
			</div>
			<div id="navbar">
				<ul class="nav navbar-nav">
					<security:authorize access="hasRole('ROLE_FL')">
						<li><a href="<spring:url value="/projects/all.html"/>">Projects</a></li>
						<li><a
							href="<spring:url value="/freelancer/profile.html"/>">Profile</a></li>
						<li><a
							href="<spring:url value="/projects/freelancer_project.html"/>">Applied
								Projects</a></li>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_EMP')">
						<li><a href='<spring:url value="/employer/profile.html"/>'>Profile</a></li>
					</security:authorize>
				</ul>
				<security:authorize access="isAnonymous()">
					<form class="navbar-form navbar-right"
						action='<spring:url value="/login"/>' method="post">
						<div class="form-group">
							<input name="userName" type="text" placeholder="Username"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="password" name="password" placeholder="Password"
								class="form-control">
						</div>
						<button type="submit" class="btn btn-success">Sign in</button>
						
						<a href='<spring:url value="/signup.html"/>' class="btn btn-info">Sign
							Up</a>
					</form>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<ul class="nav navbar-nav navbar-right">
						<li><a href='<spring:url value="/logout"/>'>Log Out</a></li>
					</ul>
				</security:authorize>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>NEON</h1>
			<p>A Freelance Management System (FMS) is a web-based workforce
				solution that helps businesses manage their independent contractor
				and freelancer workforces. FMS, empowers businesses of all sizes to
				manage the end-to-end freelance workstream, all from one centralized
				dashboard.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn
					more &raquo;</a>
			</p>
		</div>
	</div>
	<%@ include file="/WEB-INF/import/footer.jsp"%>
</body>
</html>
