<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
	String path = request.getContextPath();
	ArrayList<HashMap<String,String>> cList = (ArrayList)request.getAttribute("cList");
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
	myForm.action = "countAll1.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
	myForm.target = "kindFrame";
	myForm.submit();
}
function selOrderBySonghuo(){
	var myForm = document.getElementById("myForm");
	var hyDateL = document.getElementById("qsrq1").value;
	var hyDateR = document.getElementById("jzrq1").value;
	myForm.action = "selOrderBySonghuo1.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
	myForm.target = "sFrame";
	myForm.submit();
}
function selOrderBySell(){
	var myForm = document.getElementById("myForm");
	var hyDateL = document.getElementById("qsrq2").value;
	var hyDateR = document.getElementById("jzrq2").value;
	myForm.action = "selOrderBySell.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
	myForm.target = "xsFrame";
	myForm.submit();
}
function selOrderByStore(){
	var myForm = document.getElementById("myForm");
	var hyDateL = document.getElementById("qsrq3").value;
	var hyDateR = document.getElementById("jzrq3").value;
	myForm.action = "countAll2.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
	myForm.target = "storeFrame";
	myForm.submit();
}
       	function getDate(){
        	var d = new Date();
        	var y = d.getYear()+1900;
        	var m = d.getMonth()+1;
        	var da = d.getDate();
        	var clock= y+"-";
        	var qsrq = y+"-";
        	var qsrq1 = y+"-";
        	var qsrq2 = y+"-";
        	var qsrq3 = y+"-";
        	if(m < 10){
        		clock += "0";
        		qsrq += "0";
        		qsrq1 += "0";
        		qsrq2 += "0";
        		qsrq3 += "0";
        	}
        	clock += m + "-";
        	qsrq += m + "-";
        	qsrq1 += m + "-";
        	qsrq2 += m + "-";
        	if(da < 10){
        		clock += "0";
        		qsrq += "0";
        		qsrq1 += "0";
        		qsrq2 += "0";
        		qsrq3 += "0";
        	}
        	clock += da;
        	qsrq += da;
        	qsrq1 += da;
        	qsrq2 += da;
        	qsrq3 += da;
        	document.getElementById("qsrq").value = qsrq;
        	document.getElementById("qsrq1").value = qsrq1;
        	document.getElementById("qsrq2").value = qsrq2;
        	document.getElementById("qsrq3").value = qsrq2;
        	 //截止日期为当前日期
        	document.getElementById("jzrq").value = clock;
        	document.getElementById("jzrq1").value = clock;
        	document.getElementById("jzrq2").value = clock;
        	document.getElementById("jzrq3").value = clock;
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
       		selOrderBySonghuo();
       		selOrderBySell();
       		selOrderByStore();
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
td.text1{
	color:#555;
}
</style>
</head>
<body   style="background: #f0f9fd" >

	<div style="width:1000px;height:600px;margin:0 auto">
	<form id="myForm" method="post"></form>
		<div style="width:500px;height:200px;float:left">
			<table>
				<tr>
					<td>	起始日期：<br /></td>
					<td>
				          <input  id="qsrq" name="hyDateL" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin-right:10px;">
				      
				           至 
				      
				          <input id="jzrq" name="hyDateR" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin-left:10px;margin-right:10px;">
				          </td>
				          <td>		
								<input class="layui-btn" type="button" style="width:65px;height:35px" onclick="selOrderByWay()" value="统计"/>
						</td>
				 </tr>
			</table>
			<div>
			<iframe id="iframe" src="" name="kindFrame" frameborder="no" scrolling="auto" width="500px" height="200px" ></iframe>
			</div>
		</div>
		<div style="width:450px;height:200px;float:left;margin-left:30px">
			<table>
				<tr>
					<td class="text1">	 起始日期： <br /></td>
					<td>
				          <input  id="qsrq3" name="hyDateL1" class="laydate-icon" onclick="laydate()" style="height:30px;width:90px;background-color: #f7fcfe;margin-right:10px;">
				      
				           至 
				      
				          <input id="jzrq3" name="hyDateR1" class="laydate-icon" onclick="laydate()" style="height:30px;width:90px;background-color: #f7fcfe;margin-left:10px;margin-right:1px;">
				          </td>
				          <td>		
								<input class="layui-btn" type="button" style="width:65px;height:35px" onclick="selOrderByStore()" value="统计"/>
						</td>
				 </tr>
			</table>
			<div >
				<iframe id="iframe" src="" name="storeFrame" frameborder="no" scrolling="auto" width="450px" height="200px"></iframe>
			</div>
		</div>
		<div style="width:500px;height:200px;float:left;margin-top:70px">
			<table>
				<tr>
					<td>	起始日期：<br /></td>
					<td>
				          <input  id="qsrq2" name="hyDateL1" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin-right:10px;">
				      
				           至 
				      
				          <input id="jzrq2" name="hyDateR1" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin-left:10px;margin-right:10px;">
				          </td>
				          <td>		
								<input class="layui-btn" type="button" style="width:65px;height:35px" onclick="selOrderBySell()" value="统计"/>
						</td>
				 </tr>
			</table>
			<div>
			<iframe id="iframe" src="" name="xsFrame" frameborder="no" scrolling="auto" width="500px" height="200px"></iframe>
			</div>
		</div>
		<div style="width:450px;height:auto;float:left;margin-left:30px;margin-top:70px;">
			<table class="ceshi">
				<tr>
					<td>	起始日期：<br /></td>
					<td>
				          <input  id="qsrq1" name="hyDateL1" class="laydate-icon" onclick="laydate()" style="height:30px;width:90px;background-color: #f7fcfe;margin-right:10px;">
				      
				           至 
				      
				          <input id="jzrq1" name="hyDateR1" class="laydate-icon" onclick="laydate()" style="height:30px;width:90px;background-color: #f7fcfe;margin-left:10px;margin-right:10px;">
				          </td>
				          <td>		
								<input class="layui-btn" type="button" style="width:65px;height:35px" onclick="selOrderBySonghuo()" value="统计"/>
						</td>
				 </tr>
			</table>
			<div>
			<iframe id="iframe" src="" name="sFrame" frameborder="no" scrolling="auto" width="450px" height="200px"></iframe>
			</div>
		</div>
	</div>
</body>
</html>