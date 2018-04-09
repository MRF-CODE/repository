<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    List<Vip> vList = (List)request.getAttribute("vList");
    String total = (String)request.getAttribute("total");
    String totalExpense = (String)request.getAttribute("totalExpense");
    Integer nowPage =  0;
	if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	Integer lastPage = (Integer) request.getAttribute("lastPage");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="<%=path%>/css/common.css">
	<link rel="stylesheet" href="<%=path%>/css/main.css">
	<link rel="stylesheet" href="<%=path%>/css/common.css">
	<link rel="stylesheet" href="<%=path%>/css/style.css">
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/colResizable-1.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common.js"></script>
	<script src="<%=path%>/css/laydate/laydate.js"></script>
	<link rel="stylesheet" href="<%=path%>/css/cityselect.css">
	<script type="text/javascript" src="<%=path%>/js/cityselect.js" charset="gbk"></script>
	<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script type="text/javascript">
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			 var myForm = document.getElementById("myForm");
	   		myForm.action = "selVipFromOrders.action";
	   		myForm.submit();
		}
   		function selVip(){
   		 var myForm = document.getElementById("myForm");
   			myForm.action = "selVipFromOrders.action";
   			myForm.submit();
   		}
   		function selVipTotalExpense(){
     		 var myForm = document.getElementById("myForm");
     			myForm.action = "selVipTotalExpense.action";
     			myForm.submit();
     		}
</script>
  <script>
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
   	document.getElementById("qsrq").value = qsrq;
   	document.getElementById("jzrq").value = clock;
  	}
   	window.onload = getDate;
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
</script>
 	<style>
 		.input{
 			height:30px;width:150px;background-color: #f7fcfe;margin: 10px 10px 10px 10px;
 		}
 	</style>
 </head>
<body>
	<div style="width:939px;height:700px">
	<form id="myForm" method="post" target="bottom1">
	<div style="width:939px;height:150px;">
	<table class="imgtable">
    
    <tbody>
    <tr>
		<td>
		消费次数:
		</td>
			
			<td>
				<input type="hidden" id="targetPage" name="nowPage" value="">
		        <input  id="" name="hyCuntL" class="input">
		      至
		        <input id="" name="hyCuntR" class="input">
	        </td>
	        <td><input class="layui-btn" style="width:200px;height:50px" type="button" onclick="selVip()" value="查询会员"/></td>
		</tr>
	<tr>
		<td>起始日期:</td>
		<td>
	          <input  id="qsrq" name="hyDateL" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin: 10px 10px 10px 10px;">
	         
	           至 
	          
	          <input id="jzrq" name="hyDateR" class="laydate-icon" onclick="laydate()" style="height:30px;width:130px;background-color: #f7fcfe;margin: 10px 10px 10px 10px;">
	          </td>
	 </tr>
	<tr>
			<td>消费金额</td>
			<td>
		          <input  id="" name="hyExpenseL" class="input">
		          至
		          <input id="" name="hyExpenseR" class="input">
		    </td>
		    <td><input class="layui-btn" style="width:200px;height:50px" type="button" onclick="selVipTotalExpense()" value="查询所有会员总消费金额"/></td>
		</tr>
		 
	</table>	
	</div>
 	</form>
 	 <div style="margin-top:20px" >
		<iframe id="iframe" src="bottom.jsp" name="bottom1" frameborder="no" scrolling="auto" width="939px" height="400px" ></iframe>
	</div>
 	</div>
 	
</body>
</html>