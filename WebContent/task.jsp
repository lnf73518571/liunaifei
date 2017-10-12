<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td{
		color:#46A3FF;
		font-size:25px
	}
</style>
</head>
<body>
	<table width="100%" height="200">
		<tr>
			<td><img src="images/04.jpeg" width="100%" height="100%"></td>
		</tr>
	</table>
	<table align="right" >
		<tr>
			<td><a href="UserServlet?action=exit">用户退出</a></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td align="center"><img src="images/6.jpg" width="30" height="30">step1<hr align="center" color="yellow"></td>	
			<td align="center"><img src="images/6.jpg" width="30" height="30">step2<hr align="center" color="yellow"></td>
			<td align="center"><img src="images/6.jpg" width="30" height="30">step3<hr align="center" color="yellow"></td>
			
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center">&nbsp;<a href="CardServlet?action=catchnum">挂号等待</a></td>
			<td align="center">&nbsp;<a href="CardServlet?action=catchcard">取卡</a></td>
			<td align="center">&nbsp;<a href="medicinetype2.jsp">查询</a></td>
			
		</tr>
	</table>
</body>
</html>