<%@page import="com.hzyc.ccs.web.service.WebserviceTest"%>
<%@page import="com.hzyc.ccs.web.service.impl.WebServiceTestImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit"> 
<title>小宠家蛋糕店</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected");
		$(this).addClass("selected");
	})	;
})	;
</script>
</head>
<body style="background:url(images/topbg.gif) repeat-x;overflow-y:hidden;position:relative;top:0;left:0">
<div class="topleft" style="">
    <img src="images/logo.png" title="系统首页" />
    </div>
        
    <ul class="nav" style="position:absolute;left:283px;top:0;">
    <li><a href="shouye.jsp"  target="rightFrame" class="selected"><img src="images/icon04.png" title="首页" /><h2>首页</h2></a></li>
    <li><a href="good_sellframe.jsp" target="rightFrame"><img src="images/icon05.png" title="商品销售情况" /><h2>商品销售情况</h2></a></li>
   	<!-- 如果是店长 -->
    <%if(request.getSession().getAttribute("storeName").equals("admin")){
    	%>
   	<li><a href="sell.jsp" target="rightFrame"><img src="images/icon01.png" title="销售模块" /><h2>销售模块</h2></a></li>
    <%}else{%>
     <li><a href="manager.jsp" target="rightFrame"><img src="images/icon01.png" title="销售模块" /><h2>销售模块</h2></a></li>
    <%} %>
     <li><a href="goodframe.jsp" target="rightFrame"><img src="images/icon02.png" title="商品维护" /><h2>商品维护</h2></a></li>
    <li><a href="vip/frame.jsp"  target="rightFrame"><img src="images/icon03.png" title="会员管理" /><h2>会员管理</h2></a></li>
    <li><a href="selstore.action"  target="rightFrame"><img src="images/icon04.png" title="员工管理" /><h2>员工管理</h2></a></li>
    <li><a href="dataDictionary/frame.jsp" target="rightFrame"><img src="images/icon05.png" title="字典管理" /><h2>字典管理</h2></a></li>
    <li ><a href="system_setting.jsp"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a target="_parent" href="javascript:if(confirm('确认注销登录?')) window.location.href='destorySession.action'" id="out">退出</a></li>
    </ul>
     
    <div class="user">
    <span><%=(String)request.getSession().getAttribute("uname")%></span>
    <b></b>
    </div>    
    
    </div>
	<div style="width:100%;height:580px;">
		<div style="">
			<iframe id="iframe" src="shouye.jsp" name="rightFrame" frameborder="no" scrolling="auto" width="100%" height="700px" ></iframe>
			
			</div>
	</div>
</body>
</html>