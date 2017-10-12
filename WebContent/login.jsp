<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	 function check(form){
	  if(form.userName.value==""){
		alert("用户名为空！");
		form.userName.focus();
		return false;
	 }
	  if(form.password.value==""){
		  alert("密码为空！");
		  form.password.focus();
		  return false;
	  }
}
</script>
</head>
<body>
	<table width="100%" height="200">
		<tr>
			<td><img src="images/04.jpeg" width="100%" height="100%"></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td width="30%">
				<table>
					<tr>
						<td>
							<img src="images/07.jpg" width="100%">
						</td>
					</tr>
				</table>
			</td>
			<td width="70%">
				<table width="100%" border="0" align="center" background="images/2.jpg">
					<tr>
						<td align="center">
							<form name="form1" action="UserServlet?action=login" method="post" onSubmit="return check(this)">
								姓&nbsp;&nbsp;&nbsp;&nbsp;名:&nbsp;<input type="text" name="userName"><br><br><br>
								密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input type="password" name="password"><br><br><br>
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交">
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>