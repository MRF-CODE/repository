<%@page import="com.hzyc.ccs.model.Dictionary"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%Dictionary d = (Dictionary)request.getAttribute("d"); %>
     <%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="addDic.action" method="post" >
		分类类型：
		 <input name="zdxtype" readonly="readonly" class="layui-input" value="<%=d.getDictType()%>"><br />
		分类名称：
		<input name="goodPrice" readonly="readonly" class="layui-input" value="<%=d.getDictName()%>"><br />
		字典项代码：
		 <input name="zdxdm" readonly="readonly" class="layui-input" value="<%=d.getDictCode()%>"><br />
		字典项名称：
		<input name="zdxmc" class="layui-input"><br />
		<input style="width:180px;height:50px" class="layui-btn layui-btn-big layui-btn-normal" type="submit" value="添加"/><br />
		</form>
	</body>
</html>