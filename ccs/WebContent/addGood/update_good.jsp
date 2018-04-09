<%@page import="com.hzyc.ccs.model.Goods"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script type="text/javascript">
	function loadBkind(){
		var request = new XMLHttpRequest();
		request.onreadystatechange = function (){
			if(request.readyState == 4){
				var data = request.responseText;
				var dataarr = eval(data);
				var bkind = document.getElementById("bkind");
				for(var i = 0 ; i < dataarr.length ; i++ ){
					var option = document.createElement("option");
					option.innerText=dataarr[i].NAME;
					bkind.appendChild(option);
				}
			}
		};
		request.open("post", "<%=path%>/loadBkind.action", true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		request.send("");
	}
	function loadSkind(){
		var skind = document.getElementById("skind");
		var arr = skind.childNodes;
		var length = arr.length;
		for(var i = 0 ; i < length ; i ++){
			skind.removeChild(skind.lastChild);
		}
		var request = new XMLHttpRequest();
		request.onreadystatechange = function (){
			if(request.readyState == 4){
				var data = request.responseText;
				var dataarr = eval(data);
				var skind = document.getElementById("skind");
				for(var i = 0 ; i < dataarr.length ; i++ ){
					var option = document.createElement("option");
					option.innerText=dataarr[i].NAME;
					skind.appendChild(option);
				}
			}
		};
		request.open("post", "<%=path%>/loadSkind.action", true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var bkind = document.getElementById("bkind");
		request.send("code="+bkind.value);
	}
	window.onload = function(){
		loadBkind();
		setTimeout(loadSkind,1000);
	};
</script>
<title>Insert title here</title>
<% Goods g = (Goods)request.getAttribute("g"); %>
</head>
<body style="background: #f0f9fd">
<form action="updateGood.action" method="post" >
		
<div style="width:600px; line-height:45px; margin:auto;">
		
		<div style="width:600px; text-align:center;">
			修改商品信息
		</div>
		
		<div>
		&nbsp; &nbsp;商品名称：
		<input name="goodName" style="width:180px; height:28px;" value="<%=g.getGoodName() %>">

		&nbsp; &nbsp;商品价格：
		<input name="goodPrice" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" style="width:180px; height:28px;" value="<%=g.getGoodPrice()%>">
		</div>
		
		<div>
		&nbsp; &nbsp;商品大类：
		<select id="bkind" class="select" name="goodBkind" style="width:184px; height:33px;" onchange="loadSkind()">
				<option><%=g.getGoodBkind()%></option>
		</select>
		&nbsp; &nbsp;商品小类：
		<select id="skind" class="select" name="goodSkind" style="width:184px; height:33px;">
			<option><%=g.getGoodSkind()%></option>
					</select>
		</div>
		
		<div>
		&nbsp; &nbsp;商品备注：
		<input name="goodBz" style="width:453px; height:28px;" value="<%=g.getGoodBz()%>">
		</div>
		
		<div style="text-align:center;">
		<input type="hidden" name="goodCode" value="<%=g.getGoodCode() %>">
		<input class="layui-btn layui-btn-big layui-btn-normal" style="width:200px" type="submit" value="修改"/>
		</div>
</div>	
		
</form>
</body>
</html>