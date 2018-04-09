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
		function selcancelOrder(){
			/*查询反结账的记录  */
			var myForm = document.getElementById("myForm");
       		var hyDateL = document.getElementById("qsrq").value;
    		var hyDateR = document.getElementById("jzrq").value;
    		myForm.action = "<%=path%>/selcancelOrder.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
    		myForm.target = "canelFrame";
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
       		selcancelOrder();
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
	font:70% Arial, Helvetica, sans-serif; 
	color:#555;
	line-height:150%;
	text-align:left;
	overflow-x:hidden;
}
#container{
	width:680px;
	padding-bottom:20px;
}
 form{
	margin:1em 0;
	padding:.2em 20px;
} 
</style>

</head>

<body>
<div id="container" style="width:820px;height:80px">
	<form id="myForm" method="post">
		<table>
			<tfoot>
			<tr style="height:43px;">
				<td style="width:100px; text-align:center;"> 起始日期：</td>
				<td colspan="2">
			      <input id="qsrq" name="hyDateL" class="laydate-icon" onclick="laydate()" style="height:30px; background-color: #f7fcfe; "/>
			    </td>    
				<td style="width:100px; text-align:center;"> 截至日期：</td>
				<td>
			      <input id="jzrq" name="hyDateR" class="laydate-icon" onclick="laydate()" style="height:30px; background-color: #f7fcfe; "/>
			    </td>
			    <td>		
					&nbsp; &nbsp;&nbsp; &nbsp;<input class="layui-btn" type="button" onclick="selcancelOrder()" value="反结账情况"/>
				</td>
			 </tr>
			 </tfoot>
		</table>
	</form>
		<div>
			<iframe id="iframe" src="" name="canelFrame" frameborder="no" scrolling="auto" width="820px" height="800px" ></iframe>
		</div>
	</div>
</body>
</html>