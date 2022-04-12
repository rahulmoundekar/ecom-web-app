<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
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
				<div class="col-sm-4">
					<div class="signup-form">
						<!--sign up form-->
						<h2>Add Product!</h2>
						<form:form modelAttribute="productForm" action="saveProduct"
							method="post">
							<form:input type="text" placeholder="Product Name" path="name" />
							<form:input type="text" placeholder="Quantity" path="qty" />
							<form:input type="text" placeholder="Price" path="price" />
							<form:select path="brandId">
								<form:option value="0">--Select--</form:option>
								<c:forEach items="${brandList}" var="brand">
									<form:option value="${brand.id}">${brand.name}</form:option>
								</c:forEach>
							</form:select>
							<br>
							<br>
							<button type="submit" class="btn btn-default">Add</button>
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