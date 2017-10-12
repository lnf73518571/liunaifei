<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>请填写您需要增加的药品的详细信息：(工作人员已经分好类了哟)</h2>
	<form method="post" action="MedicineServlet?action=add">
	药品名：<input type="text" name="medicinename"><br><br><br>
	价&nbsp;&nbsp;&nbsp;&nbsp;格：<input type="text" name="price"><br><br><br>
	总&nbsp;&nbsp;&nbsp;&nbsp;数：<input type="text" name="num"><br><br><br>
	类&nbsp;&nbsp;&nbsp;&nbsp;型：<input type="text" name="typeID"><br><br><br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交">
	</form>
</body>
</html>