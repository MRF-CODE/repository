<%@page import="com.hzyc.ccs.model.GoodKind"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    List<GoodKind> gkList = (List)request.getAttribute("gkList");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function updateGood(x){
		var iWidth = 600;
		var iHeight = 370;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/selOneGood.action?goodCode="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
	function deleteGoodKind(x){
		var myForm = document.getElementById("myForm");
		myForm.action = "<%=path%>/deleteGoodKind.action?id="+x;
   		myForm.submit();
	}
   </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<style>
	.ahand{cursor: pointer;}
</style>
<body>
	<div style="width:800px">
	<form id="myForm" method="post">
	<div style="width:800px">
	<%if(gkList==null||gkList.size()==0){
	%>
	<span style="font-size:30px">未查到任何小类</span>
	<%
	}else{%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		  
		   <thead>
               <tr class="tr">
               	  <th width="80px">序号</th>
               	  <th width="80px">编码</th>
               	  <th width="80px">大类</th>
               	  <th width="80px">名称</th>
               	  <th width="80px">操作</th>
                </tr>
                <tr><td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
             <tbody>
            	<%for(int i = 0;i<gkList.size();i++){ %>
            	<tr class="tr">
					<td><%=i+1 %></td>
					<td><%=gkList.get(i).getId() %></td>    
					<td><%=gkList.get(i).getPid() %></td>    
					<td><%=gkList.get(i).getName() %></td>     
            		<td><button class="layui-btn" onclick="if(confirm('确认删除小类吗?')){deleteGoodKind('<%=gkList.get(i).getId() %>')} else{return false;} ">删除</button></td>    		
            	</tr>
            	<% }%>
            </tbody>
		</table>
		  
	</div>
							
		<%
	}%>
						
		</form>
		</div>
</body>
</html>