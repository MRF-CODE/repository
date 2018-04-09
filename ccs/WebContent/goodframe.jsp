<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function addbkind(){
		document.getElementById("iframe").src="addGood/add_bkind.jsp";
	}
	function addskind(){
		document.getElementById("iframe").src="addGood/add_skind.jsp";
	}
	function addgood(){
		document.getElementById("iframe").src="addGood/add_good.jsp";
	}
	function selgood(){
		var form = document.getElementById("myForm");
		form.action="addGood/sel_good.jsp";
		form.submit();
	}
	function selskind(){
		var form = document.getElementById("myForm");
		form.action = "<%=path%>/selSkind.action";
		form.submit();
	}
	function countgood(){
		/* var form = document.getElementById("myForm1");
		form.submit(); */
		document.getElementById("iframe").src="addGood/count_good.jsp";
	}
	/* window.onload = function(){
		countgood();
   	}; */
</script>
<style type="text/css">
	dl.leftmenu{
		width:135px;
	}
	dd{
	width:133px;
	}

</style>
</head>
<body style="background: #f0f9fd;sroll:auto" >
	<div class="lefttop">
			<span></span>当前位置：商品维护
	</div>
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		<dl class="leftmenu" id="wrapper" style="display:inline-block;vertical-align:top;">

			<dd id="level1">
				
			</dd>
			

			<!-- <dd>
				<div class="title" onclick="addbkind()">
					<span><img src="img/leftico02.png" />
					</span><a  target="sysrightFrame">添加大类</a>
				</div>
			</dd> -->
			<dd>
				<div class="title" onclick="addskind()">
					<span><img src="img/leftico02.png" />
					</span><a target="sysrightFrame">添加小类</a>
				</div>
			</dd>
			<dd>
				<div class="title" onclick="selskind()">
					<span><img src="img/leftico02.png" />
					</span><a target="sysrightFrame">查询小类</a>
				</div>
			</dd>
			<dd>
				<div class="title" onclick="addgood()" >
					<span><img src="img/leftico02.png" />
					</span><a target="sysrightFrame">添加商品</a>
				</div>
			</dd>
			 <dd>
				<div class="title" onclick="selgood()">
					<span><img src="img/leftico02.png" />
					</span>
					<form method="post" style="display:inline-block;" id="myForm" target="sysrightFrame">
					 </form>
					 <a target="sysrightFrame">查询商品</a>
				</div>
			</dd>

		</dl>
		<div style="width:820px;height:1000px;display:inline-block;vertical-align:top;">
			
		 <iframe style="display:inline-block;vertical-align:top;" id="iframe" src="addGood/sel_good.jsp" name="sysrightFrame" frameborder="no" scrolling="auto" width="820px" height="500px" ></iframe> 
		
		</div>
	
</body>
</html>