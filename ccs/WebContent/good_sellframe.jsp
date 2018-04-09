<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function countgood(){
		document.getElementById("iframe").src="addGood/count_good.jsp";
	}
	function cancelOrders(){
		document.getElementById("iframe").src="cancelOrder/cancel_order.jsp";
	}
</script>
<style>
	dd{
		width:133px;
	}
	dl.leftmenu{
		width:135px;
	}
</style>
</head>
<body style="background: #f0f9fd;overflow:hidden;" >
 	<div class="lefttop" >
			<span></span>当前位置：商品销售情况
		</div> 
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		
		
		<dl class="leftmenu" id="wrapper" style="display:inline-block;vertical-align:top;">

			<dd id="level1">
				
			</dd>
			

			 <dd>
				<div class="title" onclick="countgood()">
					<span><img src="img/leftico02.png" />
					</span>
					<form action="countGood.action" method="post" style="display:inline-block;" id="myForm1" target="sysrightFrame">
					 </form>
					 <a target="sysrightFrame">商品销售情况</a>
				</div>
			</dd>
			<dd>
				<div class="title" onclick="cancelOrders()">
					<span><img src="img/leftico02.png" />
					</span>
					<form action="" method="post" style="display:inline-block;" id="myForm1" target="sysrightFrame">
					 </form>
					 <a target="sysrightFrame">反结账情况</a>
				</div>
			</dd>


		</dl>
		<div style="width:820px;height:auto;display:inline-block;vertical-align:top;">
		<iframe id="iframe" src="addGood/count_good.jsp" name="sysrightFrame" frameborder="no" width="820px" height="500px" ></iframe>
		
		
		</div>
</body>
</html>