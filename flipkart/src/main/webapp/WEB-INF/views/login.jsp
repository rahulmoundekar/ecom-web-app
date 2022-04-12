<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color:red;
	}
</style>
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<section id="form">
		<!--form-->
		<div class="container">
			<div class="row">
				<c:if test="${not empty success}">
					<div class="alert alert-success">
						<strong>Success!</strong> ${success}
					</div>
				</c:if>

				<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<strong>Danger!</strong> ${error}
					</div>
				</c:if>
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form">
						<!--login form-->
						<h2>Login to your account</h2>
						<form:form action="login" method="post" modelAttribute="userForm">
							<form:input type="email" placeholder="Email Address"
								path="username" />
							<form:input type="password" placeholder="Password"
								path="password" />
							<button type="submit" class="btn btn-default">Login</button>
						</form:form>
					</div>
					<!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form">
						<!--sign up form-->
						<h2>New User Signup!</h2>
						<form:form modelAttribute="customerForm" action="userRegistration"
							method="post">
							<form:input type="text" placeholder="Name" path="name" />
							<form:errors path="name" cssClass="error"/>
							
							<form:input type="text" placeholder="Email Id"
								path="username" />
							<form:errors path="username" cssClass="error"/>
							
							<form:input type="password" placeholder="Password"
								path="password" />
							<form:errors path="password" cssClass="error"/>
							
							<form:input type="text" placeholder="Address" path="address" />
							<form:errors path="address" cssClass="error"/>
							
							<form:input type="text" placeholder="Mobile Number"
								path="mobileNo" />
							<form:errors path="mobileNo"cssClass="error" />
							<button type="submit" class="btn btn-default">Signup</button>
						</form:form>
					</div>
					<!--/sign up form-->
				</div>
			</div>
		</div>
	</section>
	<!--/form-->
	<%@ include file="common/footer.jsp"%>
</body>
</html>