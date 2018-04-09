<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
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
<title>Insert title here</title>
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
	function checkNull(){
	    var goodName = document.getElementById("goodName").value;
	    if(goodName==""){
	    	alert("商品名不能为空");
	    	return false;
	    }
	    var goodPrice = document.getElementById("goodPrice").value;
	    if(goodPrice==""){
	    	alert("价格不能为空");
	    	return false;
	    }
		return true;
	 }
	function ValidateValue(textbox) {
		var goodName = document.getElementById("goodName");
		if(textbox.indexOf("+") >= 0 || textbox.indexOf("*") >= 0){
			alert("商品名不能包含+和*");
			document.getElementById("goodName").value = "";
		}
		}
</script>
</head>
<body>
	<span style="font-size:20px">添加商品</span><br />
	<form action="<%=path %>/addgood.action" method="post">
	
		商品名称：
		 <input id="goodName" name="goodName" class="layui-input" style="width:400px" onkeyup="ValidateValue(this.value)"><br />
		商品价格：
		<input id="goodPrice" name="goodPrice" class="layui-input" onkeyup="this.value=this.value.replace(/[^\d\.]/g, '')" style="width:400px"><br />
		商品大类：<br />
		<select id="bkind" class="select" name="goodBkind" style="width:400px;height:40px" onchange="loadSkind()">
					</select>
					<br /><br />	
		商品小类：<br />
		<select id="skind" class="select" name="goodSkind" style="width:400px;height:40px">
					</select><br /><br />
		商品备注：
		<input name="goodBz" class="layui-input" style="width:400px"><br />
		<input class="layui-btn layui-btn-big layui-btn-normal" style="width:200px" type="submit"  onclick="return checkNull()" value="添加"/><br />
	</form>
</body>
</html>