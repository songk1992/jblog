<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	
		<!-- header -->
		<c:import url="/WEB-INF/views/blog/blog-template-header.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
		
		<c:if test="${ not empty map.category }">
			<c:if test="${ not empty map.post }">	
				<h4>${map.postList[map.post].title}</h4>
				<p>
					${map.postList[map.post].contents}
				<p>
			</c:if>
			
			<c:if test="${ empty map.post }">	
				<h4>${map.catList[map.category].name}</h4>
				<p>
					${map.catList[map.category].desc}
				<p>
			</c:if>
		</c:if>
		
		<c:if test="${ empty map.category }">
			<c:forEach items='${map.postList }' var='postVo' varStatus='i'>
				<h4>${postList.title}</h4>
				<c:if test="${i.last}">
					<h4>${postVo.title}</h4>
					<p>${postVo.contents}</p>
				</c:if>
			</c:forEach>
			</c:if>
			
	










				

				
				</div>
				<ul class="blog-list">
				<c:forEach items='${map.postList }' var='postVo' varStatus='status'>
					<li><a href="${pageContext.request.contextPath }/${id}/${postVo.categoryNo }/${postVo.no - 2 }">
					${postVo.title }</a> <span>${postVo.regDate }</span>	</a></li>
    			</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			
				<c:forEach items='${map.catList }' var='catVo' varStatus='loop'>
					<li><a href="${pageContext.request.contextPath }/${id}/${loop.index }">${catVo.name }</a></li>
    			</c:forEach>
			</ul>
		</div>
		
		<!-- footer -->
		<c:import url="/WEB-INF/views/blog/blog-template-footer.jsp" />
	</div>
</body>
</html>