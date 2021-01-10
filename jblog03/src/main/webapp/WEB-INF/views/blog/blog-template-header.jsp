<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="com.bitacademy.jblog.vo.UserVo"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<Link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jblog.css">
</head>
<body>

<div id="header">
	<h1>Spring 이야기</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a
					href="${pageContext.request.contextPath }/user/login">로그인</a></li>
				<li><a
					href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a
					href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/${authUser.name}/basic">블로그 관리</a></li>
				<li>${authUser.name}</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

</body>
</html>