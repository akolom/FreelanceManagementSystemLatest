<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/import/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employer's Profile</title>
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
					<li><a href="#">Projects</a></li>
					<li><a
						href="<spring:url value="/freelancer/profile.html?id=5"/>">Profile</a></li>
					<li><a
						href="<spring:url value="/projects/freelancer_project.html"/>">Applied
							Projects</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_EMP')">
					<li class="active"><a href='<spring:url value="/employer/profile.html"/>'>Profile</a></li>
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
		<div class="row">
			<h1>Personal Information</h1>
			<div class="col-lg-8">
				<div class="row">
					<div class="col-md-4 text-primary">First Name</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">${currentUser.firstName }</div>
				</div>
				<div class="row">
					<div class="col-md-4 text-primary">Last Name</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">${currentUser.lastName }</div>
				</div>
				<div class="row">
					<div class="col-md-4 text-primary">Contact</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">${currentUser.contact }</div>
				</div>
				<div class="row">
					<div class="col-md-4 text-primary">Email</div>
					<div class="col-md-4"></div>
					<div class="col-md-4">${currentUser.email }</div>
				</div>
				<div class="row">
					<h1>Professional Profile</h1>
					<div class="row">
						<div class="col-lg-4 text-primary">Professional Headline</div>
						<div class="col-lg-8">${currentUser.profile.professionalHeadLine }</div>
					</div>
					<div class="row">
						<div class="col-lg-4 text-primary">Profile Summary</div>
						<div class="col-lg-8">${currentUser.profile.profileSummary }</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="row center-block">
					<%-- <a href='<spring:url value="/employer/editProfile.html"/>'
						class="btn btn-primary">Edit Profile</a> --%>
				</div>
				<br> <br>
				<div class="row">
					<div class="col-lg-6 text-primary">Project Completed</div>
					<div class="col-lg-6">${currentUser.projectCompleted }</div>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<c:if test="${error }">
				<div class="alert alert-warning" role="alert">Email sending
					failed!</div>
			</c:if>
			${error }
			<c:if test="${(not empty error) && (not error)}">
				<div class="alert alert-success" role="alert">Email sent
					successfully to ${f.firstName }!</div>
			</c:if>
		</div>
		<br>
		<div class="row">
			<div>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#addProject"
						aria-controls="addProject" role="tab" data-toggle="tab">Add
							Project</a></li>
					<li role="presentation"><a href="#viewProjectList"
						aria-controls="viewProjectList" role="tab" data-toggle="tab">View
							Project List</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="addProject">
						<br>
						<form:form commandName="newProject" method="post"
							action="/FreelanceManagementSystem/employer/addProject.html"
							cssClass="form-horizontal">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Project
									Name</label>
								<div class="col-sm-10">
									<form:input path="name" cssClass="form-control"
										placeholder="Project1" />
										<form:errors path="name" />
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label">Project
									Description</label>
								<div class="col-sm-10">
									<form:textarea path="description" cssClass="form-control"
										placeholder="This project deals with ...." />
										<form:errors path="description" />
								</div>
							</div>
							<div class="form-group">
								<label for="budget" class="col-sm-2 control-label">Project
									Budget</label>
								<div class="col-sm-10">
									<form:input path="budget" cssClass="form-control"
										placeholder="100.00 $" />
										<form:errors path="budget" />
								</div>
							</div>
							<div class="form-group">
								<label for="category" class="col-sm-2 control-label">Project
									Category</label>
								<div class="col-sm-10">
									<form:select cssClass="selectpicker" path="category"
										items="${categories }" itemLabel="categoryTitle.text"
										itemValue="id" />
										<form:errors path="category"/>
								</div>
							</div>
							<div class="form-group">
								<label for="skills" class="col-sm-2 control-label">Project
									Skills</label>
								<div class="col-sm-10">
									<form:select path="skills" multiple="true"></form:select>
									<form:errors path="skills" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default">Add
										Project</button>
								</div>
							</div>
						</form:form>
					</div>
					<div role="tabpanel" class="tab-pane" id="viewProjectList">
						<p class="h3">Your Projects</p>
						<c:forEach items="${currentUser.project}" var="project">
							<div class="row panel-body">
								<blockquote>
									<div class="row">
										<div class="col-lg-8">
											<div class="row">
												<div class="text-primary">${project.name}</div>
											</div>
											<div class="row">
												<div class="col-md-3">Description</div>
												<div class="col-md-9 text-center">${project.description}</div>
											</div>
											<div class="row">
												<div class="col-md-3">Category</div>
												<div class="col-md-9 text-center">${project.category.categoryTitle.text}</div>
											</div>
											<div class="row">
												<div class="col-md-3">Skills</div>
												<div class="col-md-9 text-center">
													<c:forEach items="${project.skills }" var="skill">
													${skill.skillTitle.text}
													&nbsp;&nbsp;
												</c:forEach>
												</div>
											</div>
											<div class="row">
												<div class="col-md-3">Budget</div>
												<div class="col-md-9 text-center">$ ${project.budget}</div>
											</div>
										</div>
										<div class="col-lg-4">
											<button type="button"
												class="btn btn-primary editProjectTrigger"
												value="${project.id}">Edit Project</button>
										</div>
									</div>
									<br>
									<div class="row">
										<c:forEach items="${project.freelancers }" var="freelancer">
											<div class="col-lg-2"></div>
											<div class="col-lg-6">
												<a
													href='<spring:url value="/freelancer/profile.html?id=${freelancer.id}"/>'>${freelancer.firstName }</a>
											</div>
											<div class="col-lg-4">
												<c:if
													test="${(project.status.projectStatus == 'PENDING') && (project.status.projectStatus != 'ACCEPTED')}">
													<a
														href='<spring:url value="/email/forInterview.html?f_id=${freelancer.id}&&p_id=${project.id}"/>'
														class="btn btn-info">Email for interview</a>
												</c:if>
												<c:if
													test="${(project.status.projectStatus == 'PENDING') || (project.status.projectStatus == 'CALL_FOR_INTERVIEW')}">
													<a
														href='<spring:url value="/projects/hireFreelancer.html?f_id=${freelancer.id}&&p_id=${project.id}"/>'
														class="btn btn-primary">Hire</a>
												</c:if>
											</div>
										</c:forEach>
									</div>
								</blockquote>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="editProject" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit Project</h4>
				</div>
				<div class="modal-body">
					<form:form commandName="newProject" method="post"
						action="/FreelanceManagementSystem/employer/addProject.html"
						cssClass="form-horizontal">
						<input type="hidden" id="id" name="id" class="hidden-id">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Project
								Name</label>
							<div class="col-sm-10">
								<form:input path="name" cssClass="form-control edit-name"
									placeholder="Project1" />
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-2 control-label">Project
								Description</label>
							<div class="col-sm-10">
								<form:textarea path="description"
									cssClass="form-control edit-description"
									placeholder="This project deals with ...." />
							</div>
						</div>
						<div class="form-group">
							<label for="budget" class="col-sm-2 control-label">Project
								Budget</label>
							<div class="col-sm-10">
								<form:input path="budget" cssClass="form-control edit-budget"
									placeholder="100.00 $" />
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">Project
								Category</label>
							<div class="col-sm-10">
								<form:select cssClass="selectpicker edit-category"
									path="category" items="${categories }"
									itemLabel="categoryTitle.text" itemValue="id" />
							</div>
						</div>
						<div class="form-group">
							<label for="skills" class="col-sm-2 control-label">Project
								Skills</label>
							<div class="col-sm-10">
								<form:select class="edit-skills" path="skills" multiple="true"></form:select>
								<%-- <form:select cssClass="selectpicker" path="category"
										items="${categories }" itemLabel="categoryTitle"
										itemValue="id" /> --%>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Update
									Project</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#myTabs a').click(function(e) {
								e.preventDefault()
								$(this).tab('show')
							});
							$("#category")
									.on(
											"change",
											function() {
												$('#skills').children()
														.remove();
												$
														.ajax({
															"url" : '<spring:url value="/rest/getSkills/"/>'
																	+ $(this)
																			.val(),
															"type" : "GET",
															"dataType" : "json",
															"success" : successFunction,
															"error" : failureFunction
														});
												function successFunction(json) {
													$
															.each(
																	json,
																	function(i,
																			value) {
																		$(
																				'#skills')
																				.append(
																						$(
																								'<option>')
																								.text(
																										value.skillTitle)
																								.attr(
																										'value',
																										value.id));
																	});
												}
												function failureFunction(data) {
													alert("error");
												}
											});
							$(".editProjectTrigger")
									.click(
											function() {
												var status_id = $(this).val();
												$(".hidden-id").val(status_id);
												$
														.ajax({
															"url" : '<spring:url value="/rest/getProject/"/>'
																	+ status_id,
															"type" : "GET",
															"dataType" : "json",
															"success" : successEditFunction,
															"error" : failureEditFunction
														});
												function successEditFunction(
														json) {
													$(".edit-name").val(
															json.name);
													$(".edit-description").val(
															json.description);
													$(".edit-budget").val(
															json.budget);
												}
												function failureEditFunction(
														data) {
													alert("error");
												}
												$("#editProject").modal("show");
											});
							$(".edit-category")
									.on(
											"change",
											function() {
												$('.edit-skills').children()
														.remove();
												$
														.ajax({
															"url" : '<spring:url value="/rest/getSkills/"/>'
																	+ $(this)
																			.val(),
															"type" : "GET",
															"dataType" : "json",
															"success" : successEditCategoryFunction,
															"error" : failureEditCategoryFunction
														});
												function successEditCategoryFunction(
														json) {
													$
															.each(
																	json,
																	function(i,
																			value) {
																		$(
																				'.edit-skills')
																				.append(
																						$(
																								'<option>')
																								.text(
																										value.skillTitle)
																								.attr(
																										'value',
																										value.id));
																	});
												}
												function failureEditCategoryFunction(
														data) {
													alert("error");
												}
											});
						});
	</script>

</body>
</html>