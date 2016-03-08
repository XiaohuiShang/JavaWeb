<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
	<br>
	<jsp:include page="head.jsp" />
	<br><br><br>
	<center>
		<table border="1" cellpadding="5" cellspacing="0">
			<thead>
				<h3>论坛系统</h3>	
			<c:choose>
				<c:when test="${!empty sessionScope.user }">
					<a href="${pageContext.request.contextPath}/BbsServlet?method=toNewTopicJsp&typeId=${requestScope.typeId}" >新话题</a>
		    	</c:when>
			</c:choose>
			<tbody>
				<tr>
					<th></th>
					<th>主题</th>
					<th>作者</th>
					<th>回复数</th>
					<th>最后更新时间</th>
				</tr>
				<c:forEach var="topic" items="${requestScope.topicList }" varStatus="status">
					<tr>
						<td><c:if test="${status.first }"><font color="green">最新</font></c:if></td>
						<td><a href="${pageContext.request.contextPath}/BbsServlet?method=showReply&topicId=${topic.id}&typeId=${requestScope.typeId}">${topic.title }</a></td>
						<td>${topic.name }</td>
						<td>${topic.replyCnt }</td>
						<td><fmt:formatDate value="${topic.time }" type="both"
								dateStyle="full" timeStyle="default" /></td>
					    <td>
					    	<c:if test="${sessionScope.user.username == topic.name }">
					    		<a href="${pageContext.request.contextPath}/BbsServlet?method=toeditTopicJsp&topicId=${topic.id}&typeId=${requestScope.typeId}">编辑</a>
					    	</c:if>
					    </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</center>
	<br><br>
	<jsp:include page="back.jsp"></jsp:include>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
