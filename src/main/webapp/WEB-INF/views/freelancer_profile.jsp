<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/import/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Freelancer's Profile</title>
<%@ include file="/WEB-INF/import/links.jsp"%>
</head>
<body>

	<br>

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
					<li class="active"><a
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

	<div class="container">
		<br> <br>
		<div class="row">

			<h3>Personal Information</h3>
			<div class="col-lg-8">
				<div class="row">
					<div class="col-lg-2 text-primary">Name</div>${currentUser.firstName }
					&nbsp; ${currentUser.lastName }<br>
					<div class="col-lg-2 text-primary">Contact</div>${currentUser.contact }
					<br>
					<div class="col-lg-2 text-primary">Email</div>${currentUser.email }
				</div>
				<hr>
				<div class="row">
					<h3>Education</h3>
					<div class="row">
						<div class="col-lg-2 text-primary">Country</div>
						<div class="col-lg-2 text-primary">Degree</div>
						<div class="col-lg-2 text-primary">Start Year</div>
						<div class="col-lg-2 text-primary">End Year</div>
					</div>

					<div class="row">
						<c:forEach items="${currentUser.educations}" var="edu">
							<div class="col-lg-2">${edu.country}</div>
							<div class="col-lg-2">${edu.degree}</div>
							<div class="col-lg-2">${edu.startYear}</div>
							<div class="col-lg-2">${edu.endYear}</div>
						</c:forEach>
					</div>

				</div>
				<hr>
				<div class="row">
					<h3>Experiance</h3>
					<div class="row">
						<div class="col-lg-2 text-primary">CompanyName</div>
						<div class="col-lg-2 text-primary">Title</div>
						<div class="col-lg-2 text-primary">Years</div>
						<div class="col-lg-2 text-primary">Summary</div>
					</div>

					<div class="row">
						<c:forEach items="${currentUser.experiances}" var="edu">
							<div class="col-lg-2">${edu.companyName}</div>
							<div class="col-lg-2">${edu.title}</div>
							<div class="col-lg-2">${edu.years}</div>
							<div class="col-lg-2">${edu.summary}</div>
						</c:forEach>
					</div>

				</div>
				<hr>
				<div class="row">
					<h3>Certificates</h3>
					<div class="row">
						<div class="col-lg-2 text-primary">Award</div>
						<div class="col-lg-2 text-primary">Organization</div>
					</div>

					<div class="row">
						<c:forEach items="${currentUser.certifications}" var="edu">
							<div class="col-lg-2">${edu.award}</div>
							<div class="col-lg-2">${edu.organization}</div>
						</c:forEach>
					</div>

				</div>
				<hr>
				<div class="row">
					<h3>Skills</h3>


					<div class="row">
						<c:forEach items="${currentUser.skills}" var="skills">
							<c:forEach items="${skills.skillTitle}" var="skillTitle">
									${skillTitle.text}
								</c:forEach>
						</c:forEach>
					</div>

				</div>
			</div>
			<div class="col-lg-4">
				<div class="row center-block">
					<%-- <a href='<spring:url value="/employer/editProfile.html"/>'
						class="btn btn-primary">Edit Profile</a> <br> <br> --%>
				</div>
				<div class="row center-block">
					<a href='<spring:url value="/projects/freelancer_project.html"/>'
						class="btn btn-primary">Applied Projects</a>
				</div>
				<br>
				<div class="row">
					<div class="col-lg-6">
						<h4>
							<c:if test="${empty currentUser.jobCompleted}">
								0
					</c:if>
							${currentUser.jobCompleted} <span class="label label-default">
								Job Completed </span>
						</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<h4>
							<c:if test="${empty currentUser.charge}">
						NA
					</c:if>
							${currentUser.charge} <span class="label label-default">
								USD/Hr </span>
						</h4>
					</div>
				</div>

			</div>


		</div>

	</div>
	<br>


	<%@ include file="/WEB-INF/import/footer.jsp"%>



</body>
</html>