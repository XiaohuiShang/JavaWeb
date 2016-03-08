<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  	<jsp:include page="head.jsp"/>
  	<br><br>
	<center>
		<table border="1" cellpadding="5" cellspacing="0">
			<thead>
				<h3>主题回复</h3>
			</thead>
			<tbody>
				<tr>
					<th>主题</th>
					<td>${requestScope.topic.title }</td>
				</tr>
				<tr>
					<th>内容</th>
					<td>${requestScope.topic.content }</td>
				</tr>
			</tbody>
		</table>
		<br><br>
		<c:choose>
			<c:when test="${!empty sessionScope.user }">
				<a href="${pageContext.request.contextPath }/BbsServlet?method=toReplyJsp&topicId=${requestScope.topic.id}">回复</a>
	    	</c:when>
		</c:choose>
		<br><br>
		<table border="1" cellpadding="5" cellspacing="0">
			<tbody>
				<tr>
					<th></th>
					<th>回复标题</th>
					<th>回复内容</th>
					<th>回复作者</th>
					<th>回复时间</th>
				</tr>
				<c:forEach var="reply" items="${requestScope.replyList }"
					varStatus="status">
					<tr>
						<td><c:if test="${status.first }">最新</c:if></td>
						<td>${reply.title }</td>
						<td>${reply.content}</td>
						<td width="80">${reply.name }</td>
						<td><fmt:formatDate value="${reply.time}" type="both" dateStyle="full" timeStyle="default" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</center>
	<br><br>
	<a href="${pageContext.request.contextPath }/BbsServlet?method=showAllTopic&typeId=${requestScope.typeId}">返回</a>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
