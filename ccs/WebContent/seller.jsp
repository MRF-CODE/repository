<%@page import="com.hzyc.ccs.dao.CountDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <%
	String path = request.getContextPath();
	ArrayList<HashMap<String,String>> cList = (ArrayList)request.getAttribute("cList");
	String hyDateL = (String)request.getAttribute("hyDateL");
	String hyDateR = (String)request.getAttribute("hyDateR");
    %>
<title>Insert title here</title>
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
     <link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
    <style>

body{
	margin:0;
	padding:0;
	font:70% Arial, Helvetica, sans-serif; 
	color:#000;
	line-height:150%;
	text-align:left;
}
.imgtable tr{
 	}
 	 *{
 	font-size:14px;
 	font-family:'微软雅黑';
 }
</style>
</head>
<body style="overflow-x:hidden">
		<div style="width:300px;">
		<%if(cList !=null && cList.size() > 0){ %>
		<table class="imgtable" style="margin-top:10px;font-size: 20px;width:500px;border:1px solid #fff;">
            			<tr class="tr">
            				<td>销售客服</td>
            				<td>单数</td>
            				<td>实收的钱数</td>
            				<td>销售的现金数</td>
            			</tr>
            			
            		<%for(int i = 0;i<cList.size();i++){ 
            			if(cList.get(i).get("cashier")!=null){
            				CountDao c = new CountDao();
            		%>
            			<tr class="tr">
            				<td><%=cList.get(i).get("cashier") %></td>
            				<td><%=cList.get(i).get("COUNT(cashier)") %></td>
            				<td><%=cList.get(i).get("SUM(pay_way1_shishou_money+pay_way2_shishou_money-zhaoling_money)") %></td>
            				<td><%=c.selCash(cList.get(i).get("cashier"),hyDateL,hyDateR) %></td>
            			</tr>
            			
            			
            		<%}
            			} %>
            </tbody>
		</table>
		<%} else{%>
			<table class="imgtable" style="margin-top:10px;font-size: 20px;width:500px;border:1px solid #fff;">
            			<tr class="tr">
            				<td>销售客服</td>
            				<td>单数</td>
            				<td>销售的钱数</td>
            			</tr>
            			
            				<tr class="tr">
            				<td colspan="3" style="text-align: center">暂无查询结果</td>
            				</tr>
            </tbody>
		</table>
			
		<%} %>
	</div>
		
</body>
</html>