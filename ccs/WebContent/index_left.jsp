<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index_left.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/public.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/jquery.min.js">
</script>
		<script type="text/javascript" src="js/global.js">
</script>
		<script type="text/javascript" src="js/jquery-1.7.2.js">
</script>

		<script type="text/javascript">
/* //初始化权限菜单
function init_menu() {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			var data = request.responseText;
			var menuArray = eval("(" + data + ")");
			//获取父节点
			var Level1 = document.getElementById("level1");
			for ( var i = 0; i < menuArray.length; i++) {
				//创建一级菜单
				var div = document.createElement("div");
				//用于装载子菜单默认高度为0
				div.className ="title";
				div.id="div"+(i);
				div.innerHTML = "<span><img src='img/leftico0"+(i>3?1:i+1)+".png' /></span>"+menuArray[i].p_name;
				Level1.appendChild(div);
				
				(function(i){
					//动态滑动菜单
					$("#"+div.id).click(function() {
						var $ul = $(this).next('ul');
						$('dd').find('ul').slideUp();
						if ($ul.is(':visible')) {
							$(this).next('ul').slideUp();
						} else {
							$(this).next('ul').slideDown();
						}
					});
					//创建子菜单ul
					var ul = document.createElement("ul");
					ul.className = "menuson";
					var par_code = menuArray[i].p_code;
					var request1 = new XMLHttpRequest();
					request1.onreadystatechange = function() {
						if (request1.readyState == 4) {
							var data2 = request1.responseText;
							var menuArray2 = eval("(" + data2 + ")");
							//创建二级菜单
							for ( var j = 0; j < menuArray2.length; j++) {
								var li = document.createElement("li");
								//点击li变色
								$(".menuson li").click(function() {
										$(".menuson li.active").removeClass("active")
										$(this).addClass("active");
								});
								li.innerHTML="<cite></cite><a href='"+menuArray2[j].url+"' target='rightFrame'>"+menuArray2[j].p_name+"</a><i></i>"; 
								//追加
								ul.appendChild(li);
							}
						}
					};
					
					//追加二级
					Level1.appendChild(ul);
					request1.open("post", "initLevel2.action", true);
					request1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					request1.send("par_code=" + par_code + "&role=" + document.getElementById("role").value);
				})(i);
			}
		}
	};
	request.open("post", "initMenu.action", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.send("role=" + document.getElementById("role").value);
} */
</script>
	</head>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery.js">
</script>
	<body style="background: #f0f9fd;" onload="init_menu()">
		<div class="lefttop">
			<span></span>通讯录
		</div>
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		<dl class="leftmenu" id="wrapper">

			<dd id="level1">
				
			</dd>


			<dd>
				<div class="title">
					<span><img src="img/leftico02.png" />
					</span>其他设置
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="#">编辑内容</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">发布信息</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">档案列表显示</a><i></i>
					</li>
				</ul>
			</dd>


			<dd>
				<div class="title">
					<span><img src="img/leftico03.png" />
					</span>编辑器
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="#">自定义</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">常用资料</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">信息列表</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">其他</a><i></i>
					</li>
				</ul>
			</dd>


			<dd>
				<div class="title">
					<span><img src="img/leftico04.png" />
					</span>日期管理
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="#">自定义</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">常用资料</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">信息列表</a><i></i>
					</li>
					<li>
						<cite></cite><a href="#">其他</a><i></i>
					</li>
				</ul>

			</dd>

		</dl>

	</body>
</html>
