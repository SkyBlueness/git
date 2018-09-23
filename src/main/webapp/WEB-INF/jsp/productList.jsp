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
<title>网上商城</title>
<link href="./css/common.css" rel="stylesheet" type="text/css"/>
<link href="./css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="top.jsp"></jsp:include>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<c:forEach items="${cList }" var="c">
						<dl>
							<dt>
								<a href="find_product_by_cid.do?cid=${c.cid }"">${c.cname }</a>
							</dt>
								<c:forEach items="${c.csList }" var="cs">
									<dd>
										<a href="find_product_by_csid.do?csid=${cs.csid }">${cs.csname }</a>
									</dd>
								</c:forEach>	
						</dl>	
				</c:forEach>	
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<div id="result" class="result table clearfix">
						<ul>
							<c:forEach items="${pageList.list }" var="p">
								<li>
									<a href="find_product_by_pid.do?pid=${p.pid }">
										<img src="${p.image }" width="170" height="170"  style="display: inline-block;">
												   
										<span style='color:green'>
												${p.pname }
										</span>
												 
										<span class="price">
											商城价： ￥${p.shop_price }/份
										</span>		 
									</a>
								</li>
							</c:forEach>		
						</ul>
				</div><br>
	<c:if test="${cid!=null }">			
	共【${pageList.total }】件商品			
	<div class="pagination">
		当前${pageList.pageNum }/${pageList.pages }页
			<c:if test="${pageList.isFirstPage }">
				<span class="firstPage">&nbsp;</span>
				<span class="previousPage">&nbsp;</span>
			</c:if>
			<c:if test="${!pageList.isFirstPage }">
				<a class="firstPage" href="find_product_by_cid.do?cid=${cid }&page=1">&nbsp;</a>
				<a class="previousPage" href="find_product_by_cid.do?cid=${cid }&page=${pageList.pages-1 }">&nbsp;</a>
			</c:if>
				<c:forEach items="${pageList.navigatepageNums }" var="num">
					<c:if test="${pageList.pageNum == num }">
						<span class="currentPage">${num }</span>
					</c:if>
					<c:if test="${pageList.pageNum != num }">
						<a href="find_product_by_cid.do?cid=${cid }&page=${num }">${num }</a>
					</c:if>
				</c:forEach>
			<c:if test="${pageList.isLastPage }">
				<span class="nextPage">&nbsp;</span>
				<span class="lastPage">&nbsp;</span>
			</c:if>	
			<c:if test="${!pageList.isLastPage }">
				<a class="nextPage" href="find_product_by_cid.do?cid=${cid }&page=${pageList.pageNum+1 }">&nbsp;</a>
				<a class="lastPage" href="find_product_by_cid.do?cid=${cid }&page=${pageList.pages }">&nbsp;</a>
			</c:if>
			
	</div>
	</c:if>
	<c:if test="${csid!=null }">
		共【${pageList.total }】件商品			
	<div class="pagination">
		当前${pageList.pageNum }/${pageList.pages }页
			<c:if test="${pageList.isFirstPage }">
				<span class="firstPage">&nbsp;</span>
				<span class="previousPage">&nbsp;</span>
			</c:if>
			<c:if test="${!pageList.isFirstPage }">
				<a class="firstPage" href="find_product_by_csid.do?csid=${cid }&page=1">&nbsp;</a>
				<a class="previousPage" href="find_product_by_csid.do?csid=${cid }&page=${pageList.pages-1 }">&nbsp;</a>
			</c:if>
				<c:forEach items="${pageList.navigatepageNums }" var="num">
					<c:if test="${pageList.pageNum == num }">
						<span class="currentPage">${num }</span>
					</c:if>
					<c:if test="${pageList.pageNum != num }">
						<a href="find_product_by_cid.do?csid=${csid }&page=${num }">${num }</a>
					</c:if>
				</c:forEach>
			<c:if test="${pageList.isLastPage }">
				<span class="nextPage">&nbsp;</span>
				<span class="lastPage">&nbsp;</span>
			</c:if>	
			<c:if test="${!pageList.isLastPage }">
				<a class="nextPage" href="find_product_by_cid.do?cid=${cid }&page=${pageList.pageNum+1 }">&nbsp;</a>
				<a class="lastPage" href="find_product_by_cid.do?cid=${cid }&page=${pageList.pages }">&nbsp;</a>
			</c:if>
			
	</div>
	</c:if>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>