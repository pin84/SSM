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
<script type="text/javascript" src="resources/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<title>Register page</title>
</head>
<body>
	<br>
	<div id="div1">test</div>
	<button id="btn1" onclick="test()">change div</button>
	<br>
	<br>

	<a href="rest/index">login</a>
	<br>
	<br>
	<a href="rest/page/welcome">返回主页面</a>

	<h1>Register page</h1>
	<div>
		<form name="formUser" action="rest/register/success" method="post">
			<br>
			<table>
				<tr>
					<td align="right" width="15%"><strong>用户名:</strong></td>
					<td width="57%"><input id="username_id"
						onblur="checkUserName(this)" name="username"><span
						id="username_notice"></span></td>
				</tr>
				<tr>
					<td align="right"><span>邮箱:</span></td>
					<td><input id="email" onblur="checkEmail(this)"
						name="email_name"><span id="email_notice"></span></td>
				</tr>
				<tr>
					<td align="right"><strong>密码:</strong></td>
					<td><input id="password" onblur="checkPassword(this)"
						onchange="testChange(this.value)"
						onkeyup="checkIntensity(this.value)" type="password"
						name="password_name"><span id=password_notice></span></td>
				</tr>

				<TR>
					<TD align=right><STRONG>密码强度:</STRONG></TD>
					<TD><TABLE cellSpacing=0 cellPadding=1 width=145 border=0>
							<TBODY>
								<TR align="center">
									<TD id=pwd_lower width="33%">弱</TD>
									<TD id=pwd_middle width="33%">中</TD>
									<TD id=pwd_high width="33%">强</TD>
								</TR>
							</TBODY>
						</TABLE></TD>
				</TR>

				<tr>
					<td align="right"><strong>确认密码:</strong></td>
					<td><input id="conform_password"
						onblur="checkConformPassword(this)" type="password"
						name="conform_password_1" /> <span id="conform_password_notice"></span></td>
				</tr>

				<tr>
					<td></td>
					<td><label><input type="checkbox" id="agreement"
							onclick="checkAgreement(this)"> <b>我已看过并接受《<a
								href="#">用户协议</a>》
						</b><span id="agreement_notice"></span> </label></td>
				</tr>
				<tr>
					<td><input type="hidden" value=act_register name=act></td>
					<td><input type="submit" value="确认注册" name="Submit"
						disabled=""></td>
				</tr>
			</table>
		</form>
	</div>




</body>
</html>