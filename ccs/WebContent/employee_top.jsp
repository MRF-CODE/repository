<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
String path = request.getContextPath();
%>
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
</head>
<%
String storeNum = request.getParameter("storeNum");
/* if(storeNum == null){
	storeNum = "";
}  */
%>
<body onload="ceshi()">
	<input type="hidden" id="targetPage" name="nowPage" value="">
	<div style="width:1100px;height:40px;">
		<select id="storeSelect1" class="select"  style="width:100px;margin-left:400px">
						 <option value="全部">&nbsp;全部&nbsp;</option>
						 <%
						 	JDBCTools jt = new JDBCTools();
							ArrayList<HashMap<String,String>> aList1 = jt.find("SELECT rname FROM roles");	
						%>
						 <% for(HashMap<String,String> i :aList1){%>
					        <option value="<%=i.get("rname")%>"><%=i.get("rname")%></option>
						 <% } %>
		</select>
		<input type="hidden" id="permission" name="permission" value="全部"/>
		<input type="button" style="width:120px;margin-left:10px" class="layui-btn" value="查询" onclick="selUsers()"/>
	<script>
	function ceshi(){
		var a = document.getElementById("storeSelect1").value;
		window.open("selUsers.action?storeNum="+<%=storeNum%>+"&permission="+a,"table");
	}
	function selUsers(){
		var a = document.getElementById("storeSelect1").value;
		window.open("selUsers.action?storeNum="+<%=storeNum%>+"&permission="+a,"table");
		
	}
		
	</script>
	</div>
	
	
	<iframe id="iframe" src="employee_table.jsp" name="table" frameborder="no" scrolling="auto" width="1000px" height="500px" ></iframe>
</body>
</html>