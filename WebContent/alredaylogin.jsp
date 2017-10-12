<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>亲爱的${sessionScope.userName}</h2><br>
	您已经获得过卡号了，您的卡号是${sessionScope.cardnum}<br>
	您可以直接利用该卡进行业务操作了。<br>
	<a href="task.jsp">进行其他业务</a><br>
	<a href="UserServlet?action=exit">直接退出</a>
</body>
</html>