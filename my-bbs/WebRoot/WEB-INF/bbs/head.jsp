<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
  		window.onload = function(){
  			window.setInterval("updateTime()", 1000);
  		}
  		function updateTime(){
  			var date = new Date();
  			var time = date.toLocaleTimeString();
  			document.getElementById("spanId").innerHTML = time;
  		}
  	</script>
  	<style type="text/css">
  		a{
  			text-decoration: none
  		}
  	</style>
  </head>
  <body>
    <p>
      <div>
    	<div style="position:absolute;top: 30px">
    		欢迎<font color="red" size="4">${!empty sessionScope.user?user.username:'游客' }</font>光临
    		&nbsp;&nbsp;当前时间：<span id="spanId"></span>
    		<br/>
    		<c:choose>
    			<c:when test="${empty sessionScope.user }">
    				<a href="${pageContext.request.contextPath }/BbsServlet?method=toRegisterJsp">注册</a>&nbsp;
    				<a href="${pageContext.request.contextPath }/BbsServlet?method=toLoginJsp">登录</a>&nbsp;
    			</c:when>
    			<c:otherwise>
    				<a href="${pageContext.request.contextPath }/BbsServlet?method=exit">安全退出</a>&nbsp;	
    			</c:otherwise>
    		</c:choose>
    		<a href="${pageContext.request.contextPath }/BbsServlet?method=showAllType">首页</a>
   		</div>
    	<div style="position:absolute;top: 55px;right: 70px">
    		你的ip：${sessionScope.address.ip }&nbsp;&nbsp;归属地：${sessionScope.address.location }
    		<br/>
    		在线人数：${!empty applicationScope.online?applicationScope.online:'0' } 人
    		&nbsp;&nbsp;你是第：${!empty sessionScope.caller?sessionScope.caller:'0' }个访问者
    	</div>
      </div>
    </p>
  </body>
</html>
