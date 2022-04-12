<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cart</title>
</head>
<body>
	<%@ include file="common/header2.jsp"%>
	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Check out</li>
				</ol>
			</div>
			<!--/breadcrums-->

			<div class="review-payment">
				<h2>Review & Payment</h2>
			</div>
			<form:form action="generateBill" method="post" modelAttribute="cartForm">
				<div class="table-responsive cart_info">
					<table class="table table-condensed">
						<thead>
							<tr class="cart_menu">
								<td class="image">Product</td>
								<td class="description"></td>
								<td class="price">Price</td>
								<td class="quantity">Quantity</td>
								<td class="total">Total</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cartList}" var="cart">
								<input type="hidden" name="productId" value="${cart.product.id}"/>
								<tr>
									<td class="cart_product"><a href=""><img
											src="resources/images/cart/${cart.product.img}" alt=""></a></td>
									<td class="cart_description">
										<h4>
											<a href="">${cart.product.name}</a>
										</h4>
										<p>Web ID: ${cart.product.id}</p>
									</td>
									<td class="cart_price">
										<p>${cart.product.price}</p>
									</td>
									<td class="cart_quantity">
										<div class="cart_quantity_button">
											<input type="hidden" name="cartId" value="${cart.id}"/>
											<a class="cart_quantity_up" href=""> + </a> 
											<input
												class="cart_quantity_input" type="text" name="quantity"
												value="1" autocomplete="off" size="2"> <a
												class="cart_quantity_down" href=""> - </a>
										</div>
									</td>
									<td class="cart_total">
										<p class="cart_total_price">${cart.product.price}</p>
									</td>
									<td class="cart_delete"><a class="cart_quantity_delete"
										href=""><i class="fa fa-times"></i></a></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4">&nbsp;</td>
								<td colspan="2">
									<button type="submit" class="btn btn-default">Submit</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form:form>
			<div class="payment-options">
				<span> <label><input type="checkbox"> Direct
						Bank Transfer</label>
				</span> <span> <label><input type="checkbox"> Check
						Payment</label>
				</span> <span> <label><input type="checkbox"> Paypal</label>
				</span>
			</div>
		</div>
	</section>
	<!--/#cart_items-->


	<%@ include file="common/footer.jsp"%>
</body>
</html>