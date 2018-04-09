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
	background:#f1f1f1;
	font:70% Arial, Helvetica, sans-serif; 
	color:#000;
	line-height:150%;
	text-align:left;
}
.imgtable tr{
 		height:40px;
 	}
 	 *{
 	font-size:14px;
 	font-family:'微软雅黑';
 }
</style>
</head>
<body>
		<div style="width:500px;height:auto">
		<%if(cList !=null){ %>
		<table class="imgtable" style="margin-top:30px;font-size: 20px">
            			<tr class="tr">
            				<td>送货人</td>
            				<td>单数</td>
            			</tr>
            			
            		<%for(int i = 0;i<cList.size();i++){ 
            			if(cList.get(i).get("songhuo_people")!=null){
            		%>
            			<tr class="tr">
            				<td><%=cList.get(i).get("songhuo_people") %></td>
            				<td><%=cList.get(i).get("COUNT(songhuo_people)") %></td>
            			</tr>
            			
            		<%}
            			} %>
            </tbody>
		</table>
		<%} %>
	</div>
		
</body>
</html>