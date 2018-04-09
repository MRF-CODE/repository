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
	function selVipBuyGood(x,y){
		var iWidth = 600;
		var iHeight = 370;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/vip/selGood.action?code="+x+"&date="+y, "弹出商品", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
	
</script>
<body>
	<div style="width:800px">
	<form id="myForm" method="post">
	<%if(vrList==null||vrList.size()==0){
	%>
	
	<span style="font-size:30px">该会员没有日志</span>
	<%
	}else{%>
	<input type="hidden" id="code" name="code" value="<%=vrList.get(0).getCode()%>">
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
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		  
		   <thead>
               <tr class="tr">
               	 <th width="180px">会员名</th>
               	 <th width="180px">操作类型</th>
               	  <th width="180px">金额</th>
				  <th width="180px">时间</th>
               	  <th width="180px">赠送金额</th>
               	  <th width="180px">赠品备注</th>
               	 <th width="180px">是否领取</th>
               	 <th width="180px">领取时间</th>
               	  <th width="280px">购买商品</th>
                </tr>
                <tr><td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
            <tbody>
            	<%
            	VipSer vs =new VipSerImpl();
            	for(int i = 0;i<vrList.size();i++){ %>
            	<tr class="tr">
            		<td><%=name %></td>
            		<td><%=vrList.get(i).getType() %></td>
            		<td><%=vrList.get(i).getMoney() %></td>
            		<td><%=vrList.get(i).getOperateDate() %></td>
            		<td>
            			<%if(vrList.get(i).getGiveMoney()!=null){ %>
            			<%=vrList.get(i).getGiveMoney() %>
            			<%}else{
            				%>
            				0
            			<% }%>
            		</td>
            		<td>
            			<%if(vrList.get(i).getGiveGood()!=null){ %>
            			<%=vrList.get(i).getGiveGood() %>
            			<%}else{
            				%>
            				
            			<% }%>
            		</td>
            		<td>
            			<%if(vrList.get(i).getIsReceive()!=null){ %>
            			<%=vrList.get(i).getIsReceive() %>
            			<%}else{
            				%>
            				
            			<% }%>
            		</td>
            		<td>
            			<%if(vrList.get(i).getLingquDate()!=null){ %>
            			<%=vrList.get(i).getLingquDate() %>
            			<%}else{
            				%>
            				
            			<% }%>
            		</td>
            		<td>
            			<%if(!vrList.get(i).getType().equals("充值")) {
            			%>
						<input type="button" class="layui-btn" value="详情" onclick="selVipBuyGood('<%=vrList.get(i).getCode()%>','<%=vrList.get(i).getOperateDate()%>')">
            			<%-- <%=vs.selVipBuyGood(vrList.get(i).getCode(),vrList.get(i).getOperateDate()).equals("") ? "无":vs.selVipBuyGood(vrList.get(i).getCode(),vrList.get(i).getOperateDate()) %> --%>
            			<%}else{ %>
            				
            			<%} %>
            		</td>
            	</tr>
            	<% }%>
            </tbody>
		</table>
		 <%} %>
		</form>
		</div>
</body>
</html>