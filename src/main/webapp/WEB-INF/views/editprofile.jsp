<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/import/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile</title>
<%@ include file="/WEB-INF/import/links.jsp"%>
</head>
<body>
	<p>This is Edit Page</p>
	<form:form modelAttribute="userEdit" action="/FreelanceManagementSystem/editprofile.html" method="post" cssClass="">
		<fieldset>
			<input type="hidden" id="id" name="id" value="${userE.id }">
			<label for="email" class="">Email Address</label>
			<form:input path="Email" cssClass="form-control"
				placeholder="${userE.getEmail()}" />

			<label for="firstName" class="">First Name</label>
			<form:input path="firstName" cssClass="form-control"
				placeholder="${userE.getFirstName()}" />

			<label for="lastName" class="">Last Name</label>
			<form:input path="lastName" cssClass="form-control"
				placeholder="${userE.getLastName()}" />

			 <label for="Username" class="">UserName</label>
			<form:input path="credentials.userName" cssClass="form-control"
				placeholder="${userE.getCredentials().getUserName()}" /> 

		 <label for="password" class="">password</label>
			<form:input type="password" path="credentials.password"
				cssClass="form-control"
				placeholder="${userE.getCredentials().getPassword()}" />

			<label for="verfiPassword" class="">virifyPassword</label>
			<form:input type="password" path="credentials.verifyPassword"
				cssClass="form-control"
				placeholder="${userE.getCredentials().getVerifyPassword() }" /> 
 





			<button class="btn btn-lg btn-primary btn-block" type="submit">Edit
				NOw</button>






		</fieldset>

	</form:form>

</body>
</html>