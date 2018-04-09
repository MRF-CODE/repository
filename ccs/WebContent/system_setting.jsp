<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<style>
	a{
		text-decoration: none;
	}
	dl.leftmenu{
		width:135px;
	}
	dd{
		width:133px;
	}
</style>
</head>
<body style="background: #f0f9fd;" onload="init_menu()">
		<div class="lefttop">
			<span></span>当前位置：系统设置
		</div>
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		<dl class="leftmenu" id="wrapper" style="display:inline-block;vertical-align:top;">

			<dd id="level1">
				
			</dd>


			<dd>
				<div class="title">
					<span><img src="img/leftico02.png" />
					</span><a href="systemInstall/personalInstall/main.jsp" target="sysrightFrame">个人设置</a>
				</div>
			</dd>


			<dd>
				<div class="title">
					<span><img src="img/leftico03.png" />
					</span><a href="systemInstall/add_store.jsp" target="sysrightFrame">添加店名</a>
				</div>
				 
			</dd>


			<dd>
				<div class="title">
					<span><img src="img/leftico04.png" />
					</span><a href="selZhekou.action" target="sysrightFrame">更改会员折扣</a>
				</div>
				 

			</dd>

		</dl>
		<div style="width:820px;height:auto;display:inline-block;vertical-align:top;">
		<iframe id="iframe" src="selZhekou.action" name="sysrightFrame" frameborder="no" scrolling="auto" width="820px" height="500px" ></iframe>
		
		</div>

	</body>



<br />
<br />

</body>
</html>