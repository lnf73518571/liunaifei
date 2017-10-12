<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户确认信息界面</title>
</head>
<body>
	<c:choose>
	<c:when test="${not empty sessionScope.userName}">
	亲爱的:${sessionScope.userName}<br>
	您要购买的药品是:${sessionScope.medicinename2}<br>
	数量是:${sessionScope.num2}<br>
	价格是:${sessionScope.price2}<br>
	总价是:${sessionScope.num2*sessionScope.price2}<br>
	<form action="UserServlet?action=confirm" method="post">
		<input type="submit" value="确认">
	</form>
	<br>
	<form action="UserServlet?action=cancel" method="post">
		<input type="submit" value="取消">
	</form>
	</c:when>
	<c:otherwise>
		您必须登录才能进行业务操作。
		<a href="index.jsp">返回</a>
	</c:otherwise>
	</c:choose>
</body>
</html>