<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"  %>
<script type="text/javascript">
	
</script>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="image/logo.gif" alt=""/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<c:if test="${not empty user }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
					${user.name }|
					</li>
					<li id="headerLogout" >
						<a href="goto_orderList.do?uid=${user.uid }">我的订单</a>|
					</li>
					<li id="headerLogout" >
						<a href="quit.do">退出</a>|
					</li>
				</c:if>
				<c:if test="${empty user }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="goto_login.do">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="goto_regist.do">注册</a>|
					</li>
				</c:if>
				<li id="headerUsername" class="headerUsername"></li>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="goto_cart.do">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="index.do">首页</a>
						|
					</li>
					<c:forEach items="${clist }" var="category">
						<li>
							<a href="find_product_by_cid.do?cid=${category.cid }">${category.cname }</a>|
						</li>
					</c:forEach>	
		</ul>
	</div>


</div>	
