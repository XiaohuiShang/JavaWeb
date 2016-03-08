<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
	<br>
	<jsp:include page="head.jsp" />
	<br>
	<center>
		<form action="${pageContext.request.contextPath }/BbsServlet?method=addReply&topicId=${requestScope.topicId}" method="post">
			<table>
				<thead>发表回复</thead>
				<tbody>
					<tr>
						<th>回复名:</th>
						<td><input type="text" name="title" width="200px"/></td>
					</tr>
					<tr>
						<th>内容:</th>
						<td><textarea rows="10" cols="30" name="content"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="发表回复"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	</center>
	<br><br>
	<jsp:include page="back.jsp"></jsp:include>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
