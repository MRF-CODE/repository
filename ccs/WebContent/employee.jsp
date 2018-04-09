<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.model.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%
    response.setHeader("pragma","No-Cache");
    response.setHeader("Cache-Control","No-Cache");
    response.setDateHeader("Expires",0); 
        
	String path = request.getContextPath();
    //List<Users> uList = (List)request.getAttribute("uList");
    String storeNum = request.getParameter("storeNum");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script type="text/javascript">
	function update(x){
		var uploadForm = document.getElementById("uploadForm");
		uploadForm.action="selOneUser.action?userid="+x;
		uploadForm.submit();
	}
	function update1(x){
		var uploadForm = document.getElementById("uploadForm");
		uploadForm.action="selOneUser1.action?userid="+x;
		uploadForm.submit();
	}
	//删除员工
	function deleteEmployee(x){
		var uploadForm = document.getElementById("uploadForm");
		uploadForm.action="deleteEmployee.action?uname="+x;
		uploadForm.submit();
	}
	function fenye(x){
		document.getElementById("targetPage").value = x;
		 var myForm = document.getElementById("uploadForm");
   		myForm.action = "selUsers.action";
   		myForm.submit();
	}
	function changeImg(s){
		document.getElementById(s).src="img/no_img.png";
	}
	function changePermission(){
 		 var s = document.getElementById("storeSelect1");
 		var index=s.selectedIndex ;
 		  if(s.options[index].text == "全部"){
 			document.getElementById("permission").value = "全部";
 		} else{
 			document.getElementById("permission").value = s.options[index].text;
 		}  
 	}
	function selUsers(){
		var permission = document.getElementById("permission").value;
		 var myForm = document.getElementById("uploadForm");
	   		myForm.action = "selUsers.action?permission="+permission;
	   		myForm.submit();
	}
</script>
</head>
<frameset rows="100px,*">
	
	
	<frame  src="employee_top.jsp"   frameborder="no" scrolling="auto" width="1100px" height="500px" />
	<frame  src="employee_table.jsp"  name="table" frameborder="no" scrolling="auto" width="1100px" height="500px" />
		
	<%-- <table width="1100px" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		   
		   <thead>
               <tr class="tr">
               	  <th width="80px">员工编码</th>
				  <th width="80px">员工姓名</th>
				  <th width="80px">所属店铺</th>
				  <th width="80px">员工权限</th>
				  <th width="250px">身份证正面图片</th>
				  <th width="250px">身份证反面图片</th>
				  <th width="250px">功能</th>
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
            	
            		<%if(uList !=null) {
            			for(int i = 0;i<uList.size();i++){
            		%>
            		<tr>	
	            		<td><%=uList.get(i).getUname() %></td>
	            		<td><%=uList.get(i).getTrueName() %></td>
	            		<td><%=uList.get(i).getStoreName() %></td>
	            		<td><%=uList.get(i).getPermission()%></td>
	            		<td><img id="zhengmian<%=i%>" style="width:330px;height:100px" src="http://47.94.151.107/ccs/image/<%=uList.get(i).getImgName()%>" onerror="changeImg('zhengmian<%=i%>')" onclick="update('<%=uList.get(i).getUserid() %>')"/></td>
            			<td>
		                   <input type="button" class="layui-btn" value="上传/修改" onclick="update('<%=uList.get(i).getUserid() %>')" />
		                   <img id="fanmian<%=i%>" style="width:330px;height:100px" src="http://47.94.151.107/ccs/image/<%=uList.get(i).getImgName1()%>" onerror="changeImg('fanmian<%=i%>')" onclick="update1('<%=uList.get(i).getUserid() %>')"/>
            			</td>
            			<td><input type="button" class="layui-btn" value="删除员工" onclick="if(confirm('确认删除员工信息吗?')) {deleteEmployee('<%=uList.get(i).getUname() %>')} else{return false;}" /></td>
            		</tr>
            		<%
            		}} %>
           		
                 
            </tbody>
		</table> --%>
		<%-- <div class="page mt10" style="margin-left: 10px;">
								<div class="pagination"><ul>
										<li class="first-child">
										<a onclick="fenye(1)">首页</a>
										</li>
										<li class="disabled"><span>共<%=lastPage %>页</span>
										</li>
										<li class="active"><span>当前第<%=nowPage %>页</span>
										</li>
										<li>
										
											<a onclick="fenye(<%=nowPage-1%>)">上一页</a>
										</li>
										<li><a onclick="fenye(<%=nowPage+1%>)">下一页</a>
										</li>
										<li class="last-child">
											<a onclick="fenye(<%=lastPage%>)">尾页</a>
										</li>
									</ul>
								</div>
							</div> --%>
							
</frameset>
</html>