<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script type="text/javascript">
function checkNull(){
    var hyName = document.getElementById("hyName").value;
    var hyTel = document.getElementById("hyTel").value;
    var money = document.getElementById("money").value;
    if(hyName==""){
    	alert("姓名不能为空");
    	return false;
    }
    if(hyTel==""){
    	alert("电话不能为空");
    	return false;
    }
    if(money==""){
    	alert("充值金额不能为空");
    	return false;
    }
	return true;
 }
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="1000px;height:500px;">
		<span style="font-size:20px">添加会员</span><br />
	<form action="<%=path %>/vip/addvip.action" method="post">
	
		姓名：
		<input id="hyName" name="hyName" class="layui-input" style="width:400px">
		电话：
		<input id="hyTel" name="hyTel" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" class="layui-input" style="width:400px">
		充值金额：
		 <input id="money" name="money" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" class="layui-input" style="width:400px">
		赠送金额：
		<input id="giveMoney" name="giveMoney" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" class="layui-input" style="width:400px" value="0">
		会员备注：
		 <input id="hyBz" name="hyBz" class="layui-input" style="width:400px">
		赠品备注：
		<input id="giveGood" name="giveGood" class="layui-input" style="width:400px">
		收货地址
		<input id="hyAddress" name="hyAddress" class="layui-input" style="width:400px">
		<input class="layui-btn layui-btn-big layui-btn-normal" style="width:200px" type="submit" onclick="return checkNull()" value="注册"/><br />
	</form>
	
	</div>
</body>
</html>