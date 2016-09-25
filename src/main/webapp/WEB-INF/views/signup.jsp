<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/import/taglib.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SinUp</title>
<%@ include file="/WEB-INF/import/links.jsp"%>

</head>
<body>

	<h2>Sin up Form</h2>



	<form:form modelAttribute="user" method="post" cssClass="form-signup">
		<fieldset>
			<h2 class="form-signup-heading">Please sign up</h2>
			<label for="inputEmail" class="">Email address</label>
			<form:input path="email" cssClass="form-control"
				placeholder="you@Example.com" />

			<label for="firstName" class="">First Name</label>
			<form:input type="text" path="firstName" cssClass="form-control"
				placeholder="first Name" />

			<label for="lastName" class="">Last Name</label>
			<form:input type="text" path="lastName" cssClass="form-control"
				placeholder="last Name" />

			<label for="Username" class="">UserName</label>
			<form:input path="credentials.userName" cssClass="form-control"
				placeholder="Username" />

			<label for="password" class="">password</label>
			<form:input type="password" path="credentials.password"
				cssClass="form-control" placeholder="password" />

			<label for="verfiPassword" class="">virifyPassword</label>
			<form:input type="password" path="credentials.verifyPassword"
				cssClass="form-control" placeholder="verfiyPassword" />

			<label for="authority" class="">Sign up as:
				Employer/Freelancer</label>

			<form:select path="credentials.authority.name" items="${authority}"
				itemLabel="name" itemValue="name"></form:select>





			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				up</button>
		</fieldset>
	</form:form>


</body>
</html>




<%--  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign UP</title>
<!-- <style type="text/css">@import url(css/signup.css);</style> -->

<%@ include file="/WEB-INF/import/links.jsp"%>
<%@ include file="/WEB-INF/import/taglib.jsp"%>

</head>
<body>
	<div id="global">

		<!-- <form action="signupPost.html" method="post"> -->
		<form action="login.html" method="get">
			<p>
			<h1>SIGN UP PAGE</h1>
			</p>
			<p>
				<input type="text" name="email" placeholder="Email Address"><br>
				<input type="text" name="fname" placeholder="First name"><br>
				<input type="text" name="lname" placeholder="Last name"><br>

				<input type="text" name="username" placeholder="User Name"><br>
				<input type="password" name="password" placeholder="Password"><br>
				<input type="password" name="verifypassword"
					placeholder="Verify Password"><br> <input type="text"
					name="contact" placeholder="Phone Number"><br>
			</p>



			<select name="role">
				<option value="ROLE_ADMIN">Employer</option>
				<option value="ROLE_USER">Freelancer</option>
			</select>
			
			 <!-- <input id="reset" type="reset" tabindex="4">  -->
			  <input	type="submit" tabindex="5" value="Create Account"> 
			 
			 
		</form>

	</div>
</body>
</html> --%>