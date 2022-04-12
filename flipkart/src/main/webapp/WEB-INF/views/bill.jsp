<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<li class="active">Bill</li>
				</ol>
			</div>
			<!--/breadcrums-->

			<div class="review-payment">
				<h2>Bill</h2>
			</div>
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
									<p>${cart.qty}</p>
								</td>
								<c:set var="qty" value="${cart.qty}" />
								<c:set var="price" value="${cart.product.price}" />

								<td class="cart_total">
									<p class="cart_total_price">${qty*price}</p>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4">&nbsp;</td>
							<td colspan="2">

								<table class="table table-condensed total-result">
									<c:forEach items="${grandBill}" var="entry">
										<c:choose>
											<c:when test="${entry.key == 'Total'}">
												<tr><td><hr></td></tr>
											</c:when>
										</c:choose>
										<tr>
											<c:choose>
												<c:when test="${entry.key == 'Total'}">
													<td><strong><c:out value="${entry.key}" /></strong></td>
													<td><strong><c:out value="${entry.value}" /></strong></td>
												</c:when>
												<c:otherwise>
													<td><c:out value="${entry.key}" /></td>
													<td><c:out value="${entry.value}" /></td>
												</c:otherwise>

											</c:choose>

										</tr>
									</c:forEach>
								</table>

							</td>
						</tr>
					</tbody>
				</table>
			</div>
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