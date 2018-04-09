<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String[] storeNum = (String[])request.getAttribute("storeNum");
     String[] storeName = (String[])request.getAttribute("storeName"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body  style="background: #f0f9fd;sroll:auto" >
	<div class="lefttop">
		<form id="frame" action="selUsers.action" target="sysrightFrame"></form>
			<span></span>当前位置：员工管理
		</div>
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		<dl class="leftmenu" id="wrapper" >

			<dd id="level1">
				
			</dd>


			<%for(int i =0;i<storeName.length;i++){ %>
			<dd>
				<div class="title">
					<span><img src="img/leftico02.png" />
					</span><a href="employee_top.jsp?storeNum=<%=storeNum[i]%>" target="sysrightFrame"><%=storeName[i] %></a>
				</div>
			</dd>
			<%} %>
			


		</dl>
		<div style="position:absolute;top:50px;left:200px;"> 
		 <iframe id="iframe" src="employee_top.jsp" name="sysrightFrame" frameborder="no" scrolling="auto" width="1100px" height="600px" ></iframe>
		
		</div> 
	
</body>
</html>