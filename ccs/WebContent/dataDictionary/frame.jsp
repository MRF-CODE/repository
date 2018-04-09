<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style>
	dd{
		width:133px;
	}
	dl.leftmenu{
		width:135px;
	}
</style>
</head>
<body  style="background: #f0f9fd;" >
	<div class="lefttop">
			<span></span>当前位置：字典管理
		</div>
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		<dl class="leftmenu" id="wrapper" style="display:inline-block;vertical-align:top;">

			<dd id="level1">
				
			</dd>


			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="main.jsp" target="sysrightFrame">字典设置</a>
				</div>
			</dd>


		</dl>
		<div style="width:820px;height:auto;display:inline-block;vertical-align:top;margin:0 auto;">
		<iframe id="iframe" src="main.jsp" name="sysrightFrame" frameborder="no" scrolling="auto" width="1000px" height="800px" ></iframe>
		
		</div>
	
</body>
</html>