<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<title>我的订单页面</title>
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<jsp:include page="top.jsp"></jsp:include>
<div class="container cart">
		<div class="span24">	
			<div class="step step1">
				<ul>
					<li  class="current"></li>
					<li  ><h3>我的订单列表</h3></li>
				</ul>		
			</div>
			<c:forEach items="${pageInfo.list }" var="order">
				<table>
					<tbody>
						<tr>
							<th colspan="5">订单编号:${order.oid }&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
								color="red">￥${order.total }
							</font>
							&nbsp;&nbsp;&nbsp;&nbsp;
							订单状态:
								<c:if test="${order.state == 1 }">
									<a href="find_order_by_oid.do?oid=${order.oid}" /><font color="red">付款</font></a>
								</c:if>
								<c:if test="${order.state == 2 }">
									已付款
								</c:if>
								<c:if test="${order.state == 3 }">
									<font color="red">
									<a href="update_state.do?oid=${order.oid }"><font color="blue">确认收货</font></a>
									</font>
								</c:if>
								<c:if test="${order.state == 4 }">
									交易成功
								</c:if>
							</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
					<c:forEach items="${order.orderItems }" var="orderItem">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${orderItem.product.image }"/>
							</td>
							<td>
								<a target="_blank"><s:property value="product.pname"/>${orderItem.product.pname }</a>
							</td>
							<td>
								${orderItem.product.shop_price }
							</td>
							<td class="quantity" width="60">
								${orderItem.count }
								
							</td>
							<td width="140">
								<span class="subtotal">￥${orderItem.subtotal}</span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>	
		</div><br>
		共【${pageInfo.total }】个订单			
	<div class="pagination">
		当前${pageInfo.pageNum }/${pageInfo.pages }页
			<c:if test="${pageInfo.isFirstPage }">
				<span class="firstPage">&nbsp;</span>
				<span class="previousPage">&nbsp;</span>
			</c:if>
			<c:if test="${!pageInfo.isFirstPage }">
				<a class="firstPage" href="goto_orderList.do?page=1">&nbsp;</a>
				<a class="previousPage" href="goto_orderList.do?page=${pageInfo.pages-1 }">&nbsp;</a>
			</c:if>
				<c:forEach items="${pageInfo.navigatepageNums }" var="num">
					<c:if test="${pageInfo.pageNum == num }">
						<span class="currentPage">${num }</span>
					</c:if>
					<c:if test="${pageInfo.pageNum != num }">
						<a href="goto_orderList.do?page=${num }">${num }</a>
					</c:if>
				</c:forEach>
			<c:if test="${pageInfo.isLastPage }">
				<span class="nextPage">&nbsp;</span>
				<span class="lastPage">&nbsp;</span>
			</c:if>	
			<c:if test="${!pageInfo.isLastPage }">
				<a class="nextPage" href="goto_orderList.do?page=${pageInfo.pageNum+1 }">&nbsp;</a>
				<a class="lastPage" href="goto_orderList.do?page=${pageInfo.pages }">&nbsp;</a>
			</c:if>
			
	</div>
	</div>	
	
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>