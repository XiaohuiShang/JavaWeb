<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <br>
  	<jsp:include page="head.jsp"></jsp:include>
    <center>
		<form action="${pageContext.request.contextPath }/BbsServlet?method=register" method="post">
			<table border="1" cellpadding="5" cellspacing="0">
				<thead>
					<h3>用户注册界面</h3>
				</thead>
				<tbody>
					<tr>
						<th>用户名</th>
						<td><input type="text" name="username" />
						${requestScope.registerForm.errors.username}
						${requestScope.usermessage}
						<br/><font size="1" >&nbsp;&nbsp;用户名必须是中文</font>
						</td>
					</tr>
					<tr>
						<th>密码</th>
						<td><input type="password" name="password" />
							${requestScope.registerForm.errors.password}
							<br/>
						    <font size="1" >&nbsp;&nbsp;密码必须是6位数字或大小写字母</font>
					    </td>
					</tr>
					<tr>
						<th>再次输入</th>
						<td><input type="password" name="password2" />
							${requestScope.registerForm.errors.password2}
							${requestScope.passmessage}</td>
					</tr>
					<tr>
						<th>性别</th>
						<td><input type="radio" name="gender" value="男"/>男
							<input type="radio" name="gender" value="女"/>女</td>
					</tr>
					<tr>
						<th>邮箱</th>
						<td><input type="text" name="email" />
							${requestScope.registerForm.errors.email}</td>
					</tr>
					<tr>
						<th>验证码</th>
						<td><input type="text" name="checkCode" />
							<img src="${pageContext.request.contextPath }/CheckCodeServlet">
							${requestScope.checkmessage}
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="注册" />
						</td>
					</tr>
				</tbody>
			</table>
			${requestScope.message}
		</form>
	</center>
	<jsp:include page="back.jsp"></jsp:include>
  </body>
</html>
