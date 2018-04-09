<%@page import="java.math.BigDecimal"%>
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
   <%Vip v = (Vip)request.getAttribute("v"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<title>Insert title here</title>
<style>
	
	.t{
	width:400px;
	height:45px;
	float:left;
	margin-left:50px;
	}
	.input{
		width:180px; 
		height:28px;
		margin-left:50px;
		text-align:center;
		readonly:readonly;
	}
</style>
</head>
<body style="background: #f0f9fd">
<form action="updateGood.action" method="post" >
		
<div style="width:1000px; line-height:55px; margin:auto;">
		
		<div style="width:1000px; text-align:center;">
			会员详细信息
		</div>
		<div class="t">
		真实姓名
		<input name="" class="input" readonly="readonly" value="<%=v.getHyName()%>">
		</div>
		
		<div class="t">
		电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话&nbsp;
		<input name="" class="input" readonly="readonly" value="<%=v.getHyTel()%>">
		</div>
		
		<div class="t">
		会员级别
		<input name="" class="input" readonly="readonly" value="<%=v.getHyKindCode()%>">
		</div>
		<div class="t">
		余&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额&nbsp;
		<input name="" class="input" readonly="readonly" value="<%=v.getHyRemainMoney()%>">
		</div>
		<div class="t">
		总充值&nbsp;&nbsp; &nbsp;
		<input name="" class="input" readonly="readonly" value="<%=v.getHyTotal()%>">
		</div>
		<div class="t">
		总消费&nbsp;&nbsp;&nbsp;
		<% BigDecimal b1 = new BigDecimal(Double.parseDouble(v.getHyTotal())*100);
     		 String.valueOf((int)b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
     		 BigDecimal b2 = new BigDecimal(Double.parseDouble(v.getHyRemainMoney())*100);
     		 String.valueOf((int)b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); 
	 	 	double ss = b1.subtract(b2).doubleValue();  
 		 	 String result = String.format("%.2f", ss/100);
 		%>
		<input name="" class="input" readonly="readonly" value="<%=result %>">
		</div>
		<div class="t">
		消费次数 
		<input name="" class="input" readonly="readonly" value="<%=v.getHyCunt()%>">
		</div>
		<div class="t">
		注册时间
		<input name="" class="input" readonly="readonly" value="<%=v.getHyDate()%>">
		</div>
		
		<div style="width:870px;height:40px;float:left;margin-left:50px;">
		会员备注
		<input name="" readonly="readonly" style="width:680px;height:28px;margin-left:50px;text-align:center;" value="<%=v.getHyBz()%>">
		</div>
		
		<div style="width:870px;height:40px;float:left;margin-left:50px;">
		收货地址
			<input name="" readonly="readonly" style="width:680px;height:28px;margin-left:50px;text-align:center;" value="<%=v.getHyAddress()%>">
		</div>
		
</div>	
		
</form>
</body>
</html>