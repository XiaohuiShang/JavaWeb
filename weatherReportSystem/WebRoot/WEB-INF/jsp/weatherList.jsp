<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>天气预报</title>
    <style>
    	.div{
    	}
    	.div select{
			width:200px;
		}
		.div select option{
			width:150px;
			height:30px;
		}
    </style>
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
  </head>
  
  <body>
	<div>
		<div style="position:absolute;left:250px;top:40px">
			<font size="5" style="font-weight: bold">${weatherCustom.name }&nbsp;</font><span id="spanId"></span>
		</div>
		<div style="position:absolute;right:500px;top:40px" class="div">
			城市天气
			<form action="${pageContext.request.contextPath }/weather/selectByCity.action" method="post">
				<select name="name">
					<option value="">-请选择-</option>
					<option value="北京">北京</option>
					<option value="上海">上海</option>
					<option value="广州">广州</option>
					<option value="深圳">深圳</option>
					<option value="南京">南京</option>
				</select>
				<input type="submit" value="查询"/>
			</form>
		</div>
		<div style="position:absolute;left:250px;top:100px">
		    <table border="1" cellpadding="5" cellspacing="0" width="400px">
		    	<caption><h4>天气实况</h4></caption>
		    	<tr><td><input type="button" align="left" value="48小时预报"/></td></tr>
		    	<tr>
		    		<td>日期</td>
		    		<td>温度</td>
		    		<td>天气状况</td>
		    		<td>提醒</td>
		    	</tr>
		    	<c:if test="${weatherCustom.weathers !=null }">
			    	<c:forEach var="weather" items="${weatherCustom.weathers }">
			    		<tr>
			    			<td><fmt:formatDate value="${weather.ctime}" pattern="yyyy-MM-dd" type="date"/></td>
			    			<td>${weather.temperature }</td>
			    			<td>${weather.weathercond }</td>
			    			<td>${weather.tips }</td>
			    		</tr>
			    	</c:forEach>
		    	</c:if>
		    </table>
	    </div>
   </div>
  </body>
</html>
