<%@page import="com.hzyc.ccs.model.VipKind"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%List<VipKind> vkList = (List)request.getAttribute("vkList"); 
    String path = request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function updateVipKind(x){
		var iWidth = 600;
		var iHeight = 370;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/selOneVipKind.action?id="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
</script>
</head>
<body>
	<div style="width:800px">
	<%if(vkList==null||vkList.size()==0){
	%>
	
	<%
	}else{%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		  
		   <thead>
               <tr class="tr">
               	  <th width="80px">会员种类</th>
               	  <th width="80px">会员折扣</th>
               	  <th width="80px">所需要的钱</th>
               	  <th width="80px">操作</th>
                </tr>
                <tr><td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
            <tbody>
            	<%for(int i = 0;i<vkList.size();i++){ %>
            	<tr class="tr">
            		<td><%=vkList.get(i).getKind() %></td>
            		<td><%=vkList.get(i).getZhekou() %></td>
            		<td><%=vkList.get(i).getNeedMoney() %></td>
            		<td><button class="layui-btn" onclick="updateVipKind('<%=vkList.get(i).getId() %>')">修改</button></td>
            	</tr>
            	<% }}%>
            </tbody>
		</table>
		  
	</div>
</body>
</html>