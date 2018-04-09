<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%
	String path = request.getContextPath();
    List<Vip> vList = (List)request.getAttribute("vList");
    Vip vip = (Vip)request.getAttribute("vip");
    String total = (String)request.getAttribute("total");
    String totalExpense = (String)request.getAttribute("totalExpense");
    Integer nowPage =  0;
	if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	Integer lastPage = (Integer) request.getAttribute("lastPage");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<title>Insert title here</title>
<script type="text/javascript">
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			 var myForm = document.getElementById("myForm");
	   		myForm.action = "selVipFromOrders.action";
	   		myForm.submit();
		}
   </script>
</head>
<body>
	<%if(totalExpense==null||totalExpense.length()==0){
		%>
		
	<% }else{
	%>
		<span style="font-size:30px">所有会员的总消费金额为：</span>
		<input readonly="readonly" type="text" style="width:200px;height:50px;font-size:20px" value="<%=totalExpense%>"/>
	<%} 
	if(vList==null||vList.size()==0 &&totalExpense==null ){
	%>
	<%
	}else{%>
		<table border="0" cellpadding="0" cellspacing="0" class="list_table" >
		   <thead>
               <tr class="tr">
               	  <th width="180px">会员编码</th>
               	  <th width="180px">会员级别</th>
				  <th width="180px">会员电话</th>
				  <th width="180px">会员名</th>
				  <th width="180px">会员消费金额</th>
                </tr>
                <tr>
                <td>
                <form id="myForm" target="bottom1">
					<input type="hidden" id="targetPage" name="nowPage" value="">
					<input type="hidden" id="" name="hyCuntL" value="<%=vip.getHyCuntL() %>">
					<input type="hidden" id="" name="hyCuntR" value="<%=vip.getHyCuntR() %>">
				    <input type="hidden" id="" name="hyDateL" value="<%=vip.getHyDateL() %>">
				    <input type="hidden" id="" name="hyDateR" value="<%=vip.getHyDateR() %>">
				    <input type="hidden" id="" name="hyExpenseL" value="<%=vip.getHyExpenseL() %>">
				    <input type="hidden" id="" name="hyExpenseR" value="<%=vip.getHyExpenseR() %>">
				</form>
				</td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
            <tbody>
            	<%for(int i = 0;i<vList.size();i++){ %>
            	<tr class="tr">
            	 	<td><%=vList.get(i).getHyCode() %></td>
            	 	<td><%=vList.get(i).getHyKindCode() %></td>
            		<td><%=vList.get(i).getHyTel() %></td>
            		<td><%=vList.get(i).getHyName() %></td>
            		<td><%=vList.get(i).getHyExpense() %></td>
            	</tr>
            	<% }%>
            </tbody>
		</table>
	<div class="page mt10" style="margin-left: 10px;">
								<div class="pagination"><ul>
										<li class="first-child">
										<a  onclick="fenye(1)">首页</a>
										</li>
										<li class="disabled"><span>共<%=lastPage%>页</span>
										</li>
										<li class="active"><span>当前第<%=nowPage%>页</span>
										</li>
										<li>
										
										<a onclick="fenye(<%=nowPage-1%>)">上一页</a>
										</li>
										<li><a onclick="fenye(<%=nowPage+1%>)" >下一页</a>
										</li>
										<li class="last-child">
										<a onclick="fenye(<%=lastPage%>)">尾页</a>
										</li>
									</ul>
								</div>
	</div>
		<%
	}%>
</body>
</html>