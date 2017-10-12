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
	<h2>查询用户列表如下：</h2>
	<c:choose>
		<c:when test="${not empty sessionScope.medicineuser}">
			<c:forEach items="${sessionScope.medicineuser}" var="medicineuser" varStatus="vst">
				<p>序号：${vst.index+1},药品名：${medicineuser.medicineID},用户名：${medicineuser.username},药品种类：${medicineuser.typeID}
			</c:forEach>
		</c:when>
		<c:otherwise>
			没有查询到购买该药品的用户！
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<a href="work.jsp">进行其他业务</a><br>
	<a href="WorkUserServlet?action=exit">直接退出</a>
</body>
</html>