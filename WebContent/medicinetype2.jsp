<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
	<c:when test="${not empty sessionScope.cardnum}">
		<h2>请选择你要查询的药品种类：</h2>
		<a href="MedicineServlet?action=selectmedicine&typename='ganmao'">1.感冒类</a>
		<a href="MedicineServlet?action=selectmedicine&typename='ache'">2.疼痛类</a>
		<a href="MedicineServlet?action=selectmedicine&typename='pifu'">3.皮肤类</a>
	</c:when>
	<c:otherwise>
		您必须返回上一页进行挂号取卡步骤才能来买药哟！
	</c:otherwise>
	</c:choose>
</body>
</html>