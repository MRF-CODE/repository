<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
   			function hpExport(obj){
     		obj.href = "../excelExport.jsp";
   			return true;
  }
</script>
</head>
<body  style="background: #f0f9fd;overflow-x:hidden;overflow-y:hidden" >
	<div class="lefttop">
			<span></span>当前位置：会员管理
		</div>
		<input type="hidden" value="<%=request.getSession().getAttribute("role")%>" id="role"/>
		<dl class="leftmenu" id="wrapper" >

			<dd id="level1">
				
			</dd>

<!-- 
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="vip.jsp" target="sysrightFrame">会员管理</a>
				</div>
			</dd> -->
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="add_vip.jsp" target="sysrightFrame">新增会员</a>
				</div>
			</dd>
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="sel_vip.jsp" target="sysrightFrame">查询会员</a>
				</div>
			</dd>
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="<%=path%>/vip/selVipByToday.action" target="sysrightFrame">当日新办会员</a>
				</div>
			</dd>
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="<%=path%>/vip/selVipByXuFei.action" target="sysrightFrame">当日续费会员</a>
				</div>
			</dd>
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span>
					 <a href="#" id="hpExport" onclick="if(confirm('确认导出全部信息吗?')){return  hpExport(this) } else{return false;} ">导出会员</a>
				</div>
			</dd>
			<dd>
				<div class="title">
					<span><img src="../img/leftico02.png" />
					</span><a href="../daoru.jsp" target="sysrightFrame">导入会员</a>
				</div>
			</dd>


		</dl>
		<div style="position:absolute;top:60px;left:300px;">
		<iframe id="iframe" src="add_vip.jsp" name="sysrightFrame" frameborder="no" scrolling="auto" width="1000px" height="800px" ></iframe>
		
		</div>
	
</body>
</html>