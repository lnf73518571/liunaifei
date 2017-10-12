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
	<h2>您查询的类别药品列表如下：</h2>
	<c:choose>
		<c:when test="${not empty sessionScope.list}">
			<c:forEach items="${sessionScope.list}" var="list" varStatus="vst">
				<p>序号：${vst.index+1},药品名：${list.medicinename},药品价格：${list.price},数量:${list.num}
				<form method="post" action="UserServlet?action=buymedicine&medicinename=${list.medicinename}">
					<table>
						<tr>
							<td>请输入要购买的数量:(注意药品的数量哟，不要买超了)</td>
							<td><input type="text" name="num"></td>
							<td><input type="submit" value="购买">
						</tr>
					</table>
				</form>
			</c:forEach>
		</c:when>
		<c:when test="${empty sessionScope.userName}">
			您还没有进行登录！
			<a href="index.jsp">返回</a>
		</c:when>
		<c:otherwise>
			该类别下药品没有查询到，不好意思！
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<a href="task.jsp">进行其他业务</a><br>
	<a href="UserServlet?action=exit">直接退出</a>
</body>
</html>