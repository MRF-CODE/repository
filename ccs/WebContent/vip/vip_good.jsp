<%@page import="com.hzyc.ccs.serviceImpl.VipSerImpl"%>
<%@page import="com.hzyc.ccs.service.VipSer"%>
<%@page import="com.hzyc.ccs.model.VipRecord"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String path = request.getContextPath(); %>
    <%String data = (String)request.getAttribute("data"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script src="<%=path%>/css/laydate/laydate.js"></script>
<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script type="text/javascript">
</script>
<body>
	<%=data %>
</body>
</html>