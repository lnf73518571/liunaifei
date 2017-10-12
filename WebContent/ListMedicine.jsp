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
	<h2>需要抓紧采购的药品列表如下：</h2>
	<c:choose>
		<c:when test="${not empty sessionScope.medicine}">
			<c:forEach items="${sessionScope.medicine}" var="medicineList" varStatus="vst">
				<p>序号：${vst.index+1},药品名：${medicineList.medicinename},药品数量：${medicineList.num},药品种类：${medicineList.typeID}
			</c:forEach>
		</c:when>
		<c:otherwise>
			药品数量都够哟，您还不需要采购任何药品，请做其他工作吧！
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<a href="work.jsp">进行其他业务</a><br>
	<a href="WorkUserServlet?action=exit">直接退出</a>
</body>
</html>