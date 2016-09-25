<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/import/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Projects</title>
<%@ include file="/WEB-INF/import/links.jsp"%>
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
						<li class="active"><a href="<spring:url value="/projects/all.html"/>">Projects</a></li>
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
	<br>
	<br>
	<br>
	<br>
	<span id="a"></span>
	<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-3">
			<div class="row">
				<form:form commandName="projectSearch" method="post"
					action='/FreelanceManagementSystem/projects/filterSearch.html'>
					<form:select path="category" items="${category}"
						itemLabel="categoryTitle.text" itemValue="id"
						cssClass="selectpicker">
					</form:select>
					<br>
					<br>
					<form:select path="skills" items="${skill}"
						itemLabel="skillTitle.text" itemValue="id" multiple="true"
						cssClass="selectpicker">
						
					</form:select>
					<br>
					<br>
					<h4>
						Fixed Price <span class="label label-default">Less Than</span>
					</h4>
					<form:select path="maxBudget" cssClass="selectpicker">
						<form:option value="100">&#36; 100</form:option>
						<form:option value="1000">&#36; 1000</form:option>
						<form:option value="10000">&#36; 10000</form:option>
						<form:option value="100000">&#36; 100000</form:option>
						<form:option value="1000000">&#36; 1000000</form:option>
					</form:select>
					<br>
					<br>
					<input type="submit" class="btn btn-default" value="Search">
				</form:form>

			</div>
		</div>
		<div class="col-lg-7">
			<div class="row">
				<div class="col-lg-8">
					<form action="<spring:url value="/projects/search.html" />"
						method="get">
						<div class="input-group">
							<input type="text" name="search" class="form-control"
								placeholder="Search for Projects"> <span
								class="input-group-btn"> <input type="submit"
								class="btn btn-default" value="Go!">
							</span>

						</div>
					</form>
				</div>
				<div class="col-lg-4">
					<br>
				</div>
			</div>
			<hr>
			<c:if test="${!empty listProject}">

				<c:forEach items="${listProject}" var="project">
					<div class="row">
						<div class="col-lg-8">
							${project.name} <br> ${project.description}<br>
							${project.category.categoryTitle.text}<br>
							<c:forEach items="${project.category.skills}" var="skills">
					${skills.skillTitle.text}<br>
							</c:forEach>
							<br>
						</div>
						<div class="col-lg-4">


							<a
								href='<spring:url value="/projects/applyProject.html?id=${project.id}"/>'
								class="btn btn-primary"> Apply </a>


								<br>&#36; ${project.budget}<br>

						</div>
						</div>
						<hr>
				</c:forEach>

			</c:if>

		</div>
		<div class="col-lg-2"></div>
	</div>

	<%@ include file="/WEB-INF/import/footer.jsp"%>
</body>
</html>