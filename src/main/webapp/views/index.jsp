<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" uri="http://www.springframework.org/tags"%>

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
<script type="text/javascript" src="resources/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>
	<h1 class="h1">login page</h1>

	<br>
	<div id="div1">test</div>
	<button id="btn1" onclick="pageTest()">index.jsp</button>
	<br>
	<br>

	<div>
		<form>
			<table>
				<tr>
					<td><input id="user" type="text" name="lname"
						placeholder="用户名"></td>
				</tr>
				<tr>
					<td><input id="password" type="password" name="password"
						placeholder="密码"></td>
				</tr>
			</table>
			<div id="msg">
				<span></span>
			</div>
			<div id="autoLogin">
				<input type="checkbox" id="isLogin"><span>下次自动登录</span>
			</div>
			<button type="submit" class="btn_loging">登录</button>

			<a href="rest/page/register">注册</a>

		</form>
	</div>







</body>
</html>