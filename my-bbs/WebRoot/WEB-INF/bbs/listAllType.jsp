<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  		<jsp:include page="head.jsp"/>
	<p>
	<div>
		<div style="position: absolute;top:120px;left: 200px ">
			<center>
				<table border="1" cellpadding="5" cellspacing="0">
					<thead>
						<h3>论坛系统</h3>
					</thead>
					<tbody>
						<tr>
							<th>图标</th>
							<th>名称</th>
							<th>点击数</th>
							<th>主题数</th>
							<th>最新主题</th>
							<th>版主</th>
						</tr>
						<c:forEach var="type" items="${requestScope.typeList }">
							<tr>
								<td width="80" align="center"><img src="${type.image }" width="40" height="45" /></td>
								<td width="80"><a href="${pageContext.request.contextPath }/BbsServlet?method=showAllTopic&typeId=${type.id}">${type.title }</a></td>
								<td width="80">${type.click }</td>
								<td width="80">${type.topicCnt }</td>
								<td><font size="2">主题：${type.topic.title}<br /> 作者：${type.topic.name}<br />
									时间：<fmt:formatDate value="${type.topic.time}" type="both"
										dateStyle="full" timeStyle="default" /> <br /></font>
								</td>
								<td >${type.admin.name }&nbsp;&nbsp;
								<c:if test="${sessionScope.user.username == type.admin.name }">
									<a href="${pageContext.request.contextPath }/BbsServlet?method=editType&typeId=${type.id}">编辑&nbsp;&nbsp;</a>
									<a href="${pageContext.request.contextPath }/BbsServlet?method=deleteType&typeId=${type.id}">删除</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</center>
		</div>
		<div style="position: absolute;top:180px;right: 160px ">
			论坛今日流量:${!empty applicationScope.todayFlow?applicationScope.todayFlow:'0' };&nbsp;
			昨日流量:${applicationScope.yesterdayFlow }<br />
			<center>
				<table border="0" cellpadding="5" cellspacing="0">
					<thead>
						<h4>论坛发帖排行</h4>
					</thead>
					<tbody>
						<c:forEach var="typeOrder" items="${requestScope.typeListOrder }"
							begin="0" end="2" varStatus="status">
							<tr>
								<td width="30">${status.index+1 }</td>
								<td width="50">${typeOrder.title }</td>
								<td width="50">${typeOrder.click }</td>
								<td width="50"><img src="image/bar.jpg" height="8px" width="${typeOrder.click }"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</center>
		</div>
	</div>
	</p>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
