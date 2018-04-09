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
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function changeSkind(){
	 var s = document.getElementById("storeSelect");
	var index=s.selectedIndex ;
		document.getElementById("bkind").value = s.options[index].text;
}
</script>
</head>
<body onload="changeSkind()">
<form action="<%=path %>/addskind.action" method="post">
	<span style="font-size:20px">添加小类：</span><br />
	<input type="hidden" id="bkind" name="bkind"/>
	<select id="storeSelect" class="select" style="border:2px solid #e3e3e3;height:40px;width:150px;" onchange="changeSkind()">
					 <%
					 	JDBCTools jt = new JDBCTools();
						ArrayList<HashMap<String,String>> aList = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='1' ");	
					%>
					 <% for(HashMap<String,String> i :aList){%>  
				        <option value=""><%=i.get("NAME")%></option>  
					 <% } %>
					</select><br />
	 <span style="font-size:20px">小类：</span><br />
	 <input type="text" name ="name" class="layui-input" style="width:400px"/><br />
	  <input class="layui-btn layui-btn-big layui-btn-normal" style="width:200px" type="submit"  value="添加"/><br />
	 </form>
</body>
</html>