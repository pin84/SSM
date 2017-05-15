<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="resources/jquery-3.1.1.min.js"></script>
<script src="resources/layer.js"></script>
<script type="text/javascript" src="js/query.js"></script>
<link rel="stylesheet" type="text/css" href="css/query.css" />
<title>Insert title here</title>

</head>
<body>
	<h1 id="h1">用户控制页面</h1>
	<div id="div1">test</div>
	<button id="btn1" onclick="pageTest()">change div</button>

	<br>
	<br>
	<br>

	<a href="rest/page/register">Create New</a>
	<br>
	<br>
	<br>
	<input type="text" placeholder="请输入名字" class="iname">
	<button type="button" class="qButton">查询</button>
	<button type="button" class="listButton" onclick="GoToFirstPage()">显示所有用户</button>
	<br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>pwd</th>
				<th>email</th>
				<th>province</th>
				<th>city</th>
				<th>operation</th>
			</tr>
		</thead>
		<tbody id="seachResult">
		</tbody>
	</table>
	<div id="userPage"></div>
	<br>
	<a href="rest/page/welcome">返回主页面</a>




</body>
</html>