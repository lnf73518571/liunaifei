<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="608" height="290" border="0" align="center" cellpadding="0" cellspacing="0" background="images/2.jpg">
		<tr>
			<td width="224" height="131">&nbsp;</td>
			<td width="384" valign="bottom">
				<b>${requestScope.message}</b>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<input name="Button" type="button" class="btn_bg" value="返回" onClick="window.location.href='${requestScope.url}';">
			</td>
		</tr>
	</table>
</body>
</html>