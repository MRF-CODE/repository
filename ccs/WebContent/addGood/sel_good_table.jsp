<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.model.Goods"%>
<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    List<Goods> gList = (List)request.getAttribute("gList");
    Integer nowPage =  0;
    if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	Integer lastPage = (Integer) request.getAttribute("lastPage");
	String skind = (String)request.getAttribute("skind");
	String bkind = (String)request.getAttribute("bkind");
	String goodName =(String)request.getAttribute("goodName");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function updateGood(x){
		var iWidth = 600;
		var iHeight = 370;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/selOneGood.action?goodCode="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
	function deleteGood(x){
		var myForm = document.getElementById("myForm");
		myForm.action = "<%=path%>/deleteGood.action?goodCode="+x;
   		myForm.submit();
	}
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			var myForm = document.getElementById("myForm");
			var bkind = document.getElementById("bkind").value;
			var skind = document.getElementById("skind").value;
    		var goodName = document.getElementById("goodName").value;
	   		myForm.action = "<%=path%>/selGood.action?skind="+skind+"&goodName="+goodName+"&bkind="+bkind;
	   		myForm.submit();
		}
   </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<style>
	.ahand{cursor: pointer;}
</style>
<body>
	<div style="width:800px;height:auto;margin:0 auto">
	<form id="myForm" method="post">
	<input type="hidden" id="targetPage" name="nowPage" value="">
	<input type="hidden" id="bkind" value="<%=bkind %>">
	<input type="hidden" id="skind" value="<%=skind %>">
	<input type="hidden" id="goodName" value="<%=goodName %>">
	<div style="width:800px">
	
	<%if(gList==null||gList.size()==0){
	%>
	<span style="font-size:30px">未查到任何满足条件的商品</span>
	<%
	}else{%>
		<table width="800px" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		  
		   <thead>
               <tr class="tr">
               	  <th width="80px">商品名称</th>
               	  <th width="80px">商品大类</th>
               	  <th width="80px">商品小类</th>
               	  <th width="80px">商品价格</th>
               	  <th width="80px">操作</th>
                </tr>
                <tr><td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
            <tbody>
            	<%for(int i = 0;i<gList.size();i++){ %>
            	<tr class="tr">
            		<td><%=gList.get(i).getGoodName() %></td>
            		<td><%=gList.get(i).getGoodBkind() %></td>
            		<td><%=gList.get(i).getGoodSkind() %></td>
            		<td><%=gList.get(i).getGoodPrice() %></td>
            		<td><button class="layui-btn" onclick="updateGood('<%=gList.get(i).getGoodCode() %>')">修改</button>
            		<button class="layui-btn" onclick="if(confirm('确认删除商品吗?')){deleteGood('<%=gList.get(i).getGoodCode() %>')} else{return false;} ">删除</button></td>
            		
            	</tr>
            	<% }%>
            </tbody>
		</table>
		  
	</div>
	<div class="page mt10" style="margin-left: 10px;">
								<div class="pagination"><ul>
										<li class="first-child">
										<a class="ahand" onclick="fenye(1)">首页</a>
										</li>
										<li class="disabled"><span>共<%=lastPage%>页</span>
										</li>
										<li class="active"><span>当前第<%=nowPage %>页</span>
										</li>
										<li>
											<a class="ahand" onclick="fenye(<%=nowPage-1%>)">上一页</a>
										</li>
										<li><a class="ahand" onclick="fenye(<%=nowPage+1%>)">下一页</a>
										</li>
										<li class="last-child">
											<a class="ahand" onclick="fenye(<%=lastPage%>)">尾页</a>
										</li>
									</ul>
								</div>
							</div>
							
		<%
	}%>
						
		</form>
		</div>
</body>
</html>