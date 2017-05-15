<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="t" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="js/time.js"></script>
<link rel="stylesheet" type="text/css" href="css/welcome.css" />
</head>

<body>
	<h1>loginSuccess page</h1>
	<div id="div1">test</div>
	<button id="btn1" onclick="pageTest()">change div</button>
	<br>
	<br>
	<header>
		<h1>测试的用户管理系统</h1>
		<span><a href="rest/user/logout">注销</a><br></span>
		<div id="welDiv">
			欢迎:${sessionScope.currentUser.name} , <span id="time"></span>
		</div>
	</header>
	<section class="section">
		<menu>
			<ul>
				<li><a href="rest/page/query" id="aquery">用户列表</a></li>
				<li><a href="javascript:void" id="aquery">浏览图片</a></li>
				<li><a href="javascript:void" id="aquery">修改密码</a></li>
				<li><a href="javascript:void" id="aquery">XXXX</a></li>
				<li><a href="javascript:void" id="aquery">XXXX</a></li>
			</ul>
		</menu>

	</section>


	



</body>
</html>