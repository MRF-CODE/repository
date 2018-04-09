<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.model.Goods"%>
<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    List<Goods> gList = (List)request.getAttribute("gList");
    Integer nowPage =  0;
    if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	Integer lastPage = (Integer) request.getAttribute("lastPage");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	  	function selVip(){
       		var myForm = document.getElementById("myForm");
    		var tel = document.getElementById("tel").value;
    		myForm.action = "<%=path%>/vip/selVip.action?tel="+tel;
    		myForm.target = "vFrame";
    		myForm.submit();
       	}
   </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<body>
	<div style="width:1000px">
	<form id="myForm" method="post">
	<input type="hidden" id="targetPage" name="nowPage" value="">
	<div style="width:750px;height:80px;float:left">
		
		<table>
			<tr>
			<td>姓名/电话</td>
				 <td>		
						<input type="text" id="tel" class="layui-input" style="width:120px;margin-left:50px" name="tel"/>
				</td>
			          <td>		
							<input style="margin-left:50px" class="layui-btn" type="button" onclick="selVip()" value="查询会员"/>
					  </td>
			 </tr>
		</table>
		</div>	
		</form>
		</div>
	<div>
		<iframe id="iframe" src="<%=path%>/vip/selVip.action?tel=全部" name="vFrame" frameborder="no" scrolling="auto" width="1100px" height="500px" ></iframe>
	</div>
</body>
</html>