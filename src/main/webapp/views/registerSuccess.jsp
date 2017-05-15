<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Register Success</h3>
	<h1>
		<b>欢迎新用户</b>
	</h1>
	<br> 

	<table border="0">
		<tr>
			<td>用户名:</td>
			<td>${requestScope.usermap.name}</td>
		</tr>
		<tr>
			<td>密 码:</td>
			<td>${requestScope.usermap.pwd}</td>
		</tr>
		<tr>
			<td>邮 箱:</td>
			<td>${requestScope.usermap.email}</td>
		</tr>
	</table>
	<br>
	<a href="rest/index">返回登录页面</a>   
</body>
</html>