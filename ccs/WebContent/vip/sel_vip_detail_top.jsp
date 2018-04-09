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
   <%List<VipRecord> vrList = (List)request.getAttribute("vrList"); %>
     <%ArrayList<HashMap<String,String>> vList = (ArrayList<HashMap<String,String>>)request.getAttribute("vList"); %>
    <%String name = (String)request.getAttribute("name"); %>
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
	};
	function selVipRecord(){
		var myForm = document.getElementById("myForm");
		var code = document.getElementById("code").value;
		var hyDateL = document.getElementById("qsrq").value;
		var hyDateR = document.getElementById("jzrq").value;
		myForm.action = "selVipRecord.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR+"&code="+code;
		myForm.submit();
	}
</script>
<body>
			<table>
				<tr>
					<td>
				          <input  id="qsrq" name="hyDateL" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin-right:10px;margin-left:200px">
				      
				           至 
				      
				          <input id="jzrq" name="hyDateR" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin-left:10px;margin-right:10px;">
				          </td>
				          <td>		
								<input class="layui-btn" type="button" style="width:65px;height:35px;margin-left:10px" onclick="selVipRecord()" value="查询"/>
						</td>
				 </tr>
			</table>
</body>
</html>