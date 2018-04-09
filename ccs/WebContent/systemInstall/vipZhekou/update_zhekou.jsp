<%@page import="com.hzyc.ccs.model.VipKind"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    VipKind vd= (VipKind)request.getAttribute("vd"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<title>Insert title here</title>
</head>
<body style="background: #f0f9fd">
<form action="updateVipKind.action" method="post" >
<div style="width:600px; line-height:45px; margin:auto;">
	<div style="width:600px; text-align:center;">
		修改会员折扣
	</div>
		<div>
		<input type="hidden" name="id" value="<%=vd.getId() %>">
		&nbsp; &nbsp;会员种类：
		 <input name="kind" style="width:180px; height:28px;" value="<%=vd.getKind() %>">
		&nbsp; &nbsp;会员折扣：
		<input name="zhekou" style="width:180px; height:28px;" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" value="<%=vd.getZhekou()%>">
		</div>
		<div>
		&nbsp; &nbsp;所需的钱：
		 <input name="needMoney" style="width:180px; height:28px;" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" value="<%=vd.getNeedMoney()%>">
		 </div>
		 <div style="text-align:center;">
		<input class="layui-btn layui-btn-big layui-btn-normal" style="width:180px;height:50px" type="submit" value="修改"/>
		</div>
	</div>
</form>	
</body>
</html>