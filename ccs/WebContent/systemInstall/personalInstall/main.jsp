<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
   <link rel="stylesheet" href="<%=path%>/css/main.css">
   <link rel="stylesheet" href="<%=path%>/css/style.css">
   <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
   <script type="text/javascript" src="<%=path%>/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="<%=path%>/js/common.js"></script>
   <script src="<%=path%>/css/laydate/laydate.js"></script>
   <link rel="stylesheet" href="<%=path%>/css/cityselect.css">
   <script type="text/javascript" src="<%=path%>/js/cityselect.js" charset="gbk"></script>
   <link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function valid(){
		var bpwd= document.getElementById("bpwd").value;
		var pwd = document.getElementById("pwd").value;
		var spwd=document.getElementById("spwd").value;
		if(bpwd.trim()!= "" && pwd.trim()!= "" && spwd.trim()!= ""){
			if(pwd == spwd ){
				document.getElementById("myform").submit();
			}else{
				alert("两次密码必须输入一致");
			}
			
		}else{
			alert("请输入完整信息");
		}
	}

</script>
<style>
	td.left{
		width:100px;
		padding-left:70px;
		height:30px;
		padding:10px 20px;
	}
	td{
		width:80px;
	}
	input#bpwd {
		height:30px;
		width:200px;
		border:1px solid #e3e3e3;
	}
	input#pwd {
		height:30px;
		width:200px;
		border:1px solid #e3e3e3;
	}
	input#spwd {
		height:30px;
		width:200px;
		border:1px solid #e3e3e3;
	}
</style>
</head>
<body>
	<%
		int uid = 1;
		String uname = "admin";
	%>
	<div class="wrapper" style="width:650px;">
	<form id="myform" action="<%=path%>/updPwd.action" method="post" >
	<table border="0" cellpadding="0" cellspacing="0" class="list_table" >
	        <tbody>
	        <tr>
	          <td class="left">用户名</td>
	          <td>
	         	 <%
	         	 	JDBCTools jt = new JDBCTools();
	         	 	String username = jt.find("select * from boss where id='"+uid+"'").get(0).get("uname");
	         	 	out.print(username);
	         	 %>
	          </td>
	          <td rowspan="3">
	          		暂无
	          </td>
	        </tr>
	        	<tr>
	       		<td  class="left">角色</td>
		          <td>
					管理员
		          </td>
	       	</tr>
	       	<tr>
	      		<td  class="left">备注</td>
	        	<td>
	          		暂无
	            </td>
	       	</tr>
	       	<tr>
	      		<td  class="left">原密码</td>
	        	<td>
	          		<input type="password" name="bpwd" id="bpwd" placeholder="请输入原密码" />
	            </td>
	            <td rowspan="3">
	            	<input type="button" name="button" class="layui-btn" onclick="valid()" value="提交"> 
	            </td>
	       	</tr>
	       	<tr>
	      		<td  class="left">新密码</td>
	        	<td>
	          		<input type="password" id="pwd" name="pwd" placeholder="请输入新密码" />
	            </td>
	       	</tr>
	       	<tr>
	      		<td  class="left">确认密码</td>
	        	<td>
	          		<input type="password" id="spwd" name="spwd" placeholder="确认密码" />
	            </td>
	       	</tr>
	       	</tbody>
	   </table>
	</form>
	<%
		String cflag = request.getParameter("compare");
		String flag = request.getParameter("updflag");
		if(cflag!= null){
			if(cflag.equals("false")){
				%>
				<script>
					
				alert("原密码不正确");
				</script>
				<%
			}
		}
		if(flag!= null){
			if(flag.equals("true")){
				%>
				<script>
					
				alert("修改密码成功");
				</script>
				<%
			}
		}
	%>
	
	</div>
	
</body>
</html>