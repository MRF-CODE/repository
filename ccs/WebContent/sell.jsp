<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.system.DataDictionary"%>
<%@page import="com.hzyc.ccs.model.Orders"%>
<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    Double payMoney = (Double)request.getAttribute("payMoney");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script src="<%=path%>/css/laydate/laydate.js"></script>
<link rel="stylesheet" href="<%=path%>/css/cityselect.css">
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script>
	function selOrderByWay(){
		var myForm = document.getElementById("myForm");
		var hyDateL = document.getElementById("qsrq").value;
		var hyDateR = document.getElementById("jzrq").value;
		myForm.action = "countAll.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
		myForm.target = "newFrame";
		myForm.submit();
	}
	function selOrderByYue(){
		var myForm = document.getElementById("myForm");
		myForm.action = "selOrderByYue.action";
		myForm.submit();
	}
	function selOrderByYear(){
		var myForm = document.getElementById("myForm");
		myForm.action = "selOrderByYear.action";
		myForm.submit();
	}
	function selOrderByStore(){
		var myForm = document.getElementById("myForm");
		myForm.action = "selOrderByStore.action";
		myForm.submit();
	}
	function selOrderByDate(){
		var myForm = document.getElementById("myForm");
		myForm.action = "selOrderByDate.action";
		myForm.submit();
	}
	
	function selOrderBySonghuo(){
		var myForm = document.getElementById("myForm");
		var hyDateL = document.getElementById("qsrq").value;
		var hyDateR = document.getElementById("jzrq").value;
		myForm.action = "selOrderBySonghuo.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
		myForm.submit();
	}
	function selOrderBySell(){
		var myForm = document.getElementById("myForm");
		var hyDateL = document.getElementById("qsrq").value;
		var hyDateR = document.getElementById("jzrq").value;
		myForm.action = "selOrderBySell.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
		myForm.submit();
	}
       	function getDate(){
        	var d = new Date();
        	var y = d.getYear()+1900;
        	var m = d.getMonth()+1;
        	var da = d.getDate();
        	var clock= y+"-";
        	var qsrq = y+"-";
        	if(m < 10){
        		clock += "0";
        		qsrq += "0";
        	}
        	clock += m + "-";
        	qsrq += m + "-";
        	if(da < 10){
        		clock += "0";
        		qsrq += "0";
        	}
        	clock += da;
        	qsrq += da;
        	 //开始日期为一年前
        	document.getElementById("qsrq").value = qsrq;
        	 //截止日期为当前日期
        	document.getElementById("jzrq").value = clock;
       	}
       
       	function getElementAbsPos(e)   
       	{  
       	    var t = e.offsetTop;  
       	    var l = e.offsetLeft;  
       	    while(e = e.offsetParent)  
       	    {  
       	        t += e.offsetTop;  
       	    l += e.offsetLeft;  
       	    }  
       	  
       	    return {left:l,top:t};  
       	}  
       	window.onload = function(){
       		getDate();
       		selOrderByWay();
       	};
 </script>
 <style>
 *{
 	font-size:14px;
 	font-family:'微软雅黑';
 }
 	.imgtable tr{
 		height:40px;
 	}
body{
	margin:0;
	padding:0;
	background:#f1f1f1;
	font:70% Arial, Helvetica, sans-serif; 
	color:#555;
	line-height:150%;
	text-align:left;
}
#container{
	margin:0 auto;
	width:680px;
	background:#fff;
	padding-bottom:20px;
}
#content{margin:0 20px;}
p.sig{	
	margin:0 auto;
	width:680px;
	padding:1em 0;
}
 form{
	margin:1em 0;
	padding:.2em 20px;
	background:#eee;
} 
</style>
</head>

<body>
<div id="container" style="width:1100px;height:80px">
	<form id="myForm" method="post">
		<div style="width:1060px;height:80px;float:left">
		
		<table>
			<tr>
				<td>	起始日期：<br /></td>
				<td>
			          <input  id="qsrq" name="hyDateL" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;">
			           至 
			          <input id="jzrq" name="hyDateR" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;">
			          </td>
			          <td>		
							<input class="layui-btn" type="button" style="width:55px;height:40px" onclick="selOrderByWay()" value="统计"/>
							<input class="layui-btn" type="button" onclick="selOrderByYue()" value="按月统计"/>
							<input class="layui-btn" type="button" onclick="selOrderByDate()" value="按天"/>
					  </td>
				<td>
					<input class="layui-btn" type="button" onclick="selOrderByYear()" value="按年统计"/>
					<input class="layui-btn" type="button" onclick="selOrderByStore()" value="店铺"/>
					<input class="layui-btn" type="button" onclick="selOrderBySonghuo()" value="送货员"/>
					<input class="layui-btn" type="button" onclick="selOrderBySell()" value="销售客服"/>
					
				</td>
			 </tr>
		</table>
		</div>	
	</form>
		<div>
			<iframe id="iframe" src="table.jsp" name="newFrame" frameborder="no" scrolling="auto" width="1100px" height="1000px" ></iframe>
		</div>
	</div>
</body>
</html>