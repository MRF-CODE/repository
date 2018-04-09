<%@page import="com.hzyc.ccs.model.Users"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%
	String path = request.getContextPath();
        Users u = (Users)request.getAttribute("user");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<script type="text/javascript">
function changeImg(s){
	document.getElementById(s).src="img/no_img.png";
}
</script>
</head>
<body>
员工管理上传图片
<form id="uploadForm" method="post" enctype="multipart/form-data" action="uploadimg.action"> 
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		   
		   <thead>
               <tr class="tr">
               	  <th width="80px">员工编码</th>
				  <th width="80px">员工姓名</th>
				  <th width="80px">所属店铺</th>
				  <th width="80px">员工权限</th>
				  <th width="80px">身份证正面图片</th>
				  <th colspan="2">功能</th>
                </tr>
                <tr><td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
            <tbody>
            	
            		<%if(u !=null) {
            		%>
            		<tr>	
	            		<td><%=u.getUname() %></td>
	            		<td><%=u.getTrueName() %></td>
	            		<td><%=u.getStoreName() %></td>
	            		<td><%=u.getPermission()%></td>
	            		<td><img id="zhengmian" style="width:350px;height:150px" src="http://47.94.151.107/ccs/image/<%=u.getImgName()%>" onerror="changeImg('zhengmian')"/></td>
	            		<td>
							<input type="file" name="imageFile" ID="fupPhoto"/>
						</td>
						<td>
							<input type="hidden" name="userid" value="<%=u.getUserid()%>">
							<input type="hidden" name="imgName" value="<%=u.getImgName()%>">
							<input type="submit" id="fileSubmit" name="submit" value="上传" style="width:80px;height:30px"/>
	            		</td>
            		</tr>
            		<%
            		} %>
           		
            </tbody>
		</table>
</form>
</body>
</html>