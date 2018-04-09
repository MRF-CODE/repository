<%@page import="com.hzyc.ccs.system.DataDictionary"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=path %>/addbkind.action" method="post">
	<span style="font-size:20px">新商品添加：</span><br />
	 <span style="font-size:20px">大类：</span><br />
	 <input type="text" name ="name" style="width:100px;height:30px;font-size:20px"/><br />
	  <input style="width:180px;height:50px" type="submit"  value="添加"/><br />
	 </form>
</body>
</html>