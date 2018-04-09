<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
	String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form id="form2" method="post" enctype="multipart/form-data" action="<%=path%>/vip/uploadftp.action">
        <input type="file" name="file">
        <input type="submit" value="导入会员">
    </form>
</body>
</html>