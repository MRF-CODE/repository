<%@page import="com.hzyc.ccs.serviceImpl.OrderSerImpl"%>
<%@page import="com.hzyc.ccs.service.OrderSer"%>
<%@page import="com.hzyc.ccs.model.CancelOrders"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    List<CancelOrders> cList = (List)request.getAttribute("cList");
    Integer nowPage =  0;
    if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	Integer lastPage = (Integer) request.getAttribute("lastPage");
	String hyDateL = (String)request.getAttribute("hyDateL");
	String hyDateR = (String)request.getAttribute("hyDateR");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			var myForm = document.getElementById("myForm");
       		var hyDateL = document.getElementById("hyDateL").value;
    		var hyDateR = document.getElementById("hyDateR").value;
    		myForm.action = "<%=path%>/selcancelOrder.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR;
	   		myForm.submit();
		}
   </script>
 <style>
 *{
 	font-size:14px;
 	font-family:'微软雅黑';
 }
 	.imgtable tr{
 		height:40px;
 	}
body{
	margin:0;
	padding:0;
	background:#f1f1f1;
	font:70% Arial, Helvetica, sans-serif; 
	color:#555;
	line-height:150%;
	text-align:left;
}
#container{
	margin:0 auto;
	width:680px;
	padding-bottom:20px;
}
#content{margin:0 20px;}
 form{
	margin:1em 0;
	padding:.2em 20px;
} 
  #tip {
  border: solid 1px black;
  width: 358px;
  line-height: 21px;
  height: 21px;
  padding: 2px 10px;
  background-color: pink;
  font-size: 12px;
  text-align: center;
  margin: 10px auto 10px;
  }

  .list_table {
  width: 380px;
  margin: 0 auto;
  border-collapse: collapse;
  text-align: center;
  }

  
  .list_table tr th {
  background: #e5f1f4;
  color: #666;
  height: 30px;
  /*line-height: 30px;*/
  }
  .list_table tr td{
  background: #fff;
  color: #666;
  height: 30px;
  }


  .list_table a,
  .list_table a:visited {
  color: #333;
  text-decoration: none
  }

  .list_table a:hover,
  .list_table a:active {
  color: blue;
  text-decoration: none
  }
 </style>
  <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/jquery.tablesort.js"></script>
<script type="text/javascript">
$(function(){
$('table').tablesort().data('tablesort');

$('thead th.number').data('sortBy', function(th, td, sorter) {
	return parseInt(td.text(), 10);
});

//Sorting indicator example
$('table.table').on('tablesort:start', function(event, tablesort) {
	$('table.table tbody').addClass("disabled");
	$('.table th.number').removeClass("sorted").text('Sorting..');
});

$('table.table').on('tablesort:complete', function(event, tablesort) {
	$('table.table tbody').removeClass("disabled");
	$('.table th.number').text('Number');
});
});
   </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script src="<%=path%>/css/laydate/laydate.js"></script>
</head>
<body>
<div id="container" style="width:800px;height:80px">
	<form id="myForm" method="post">
	<input type="hidden" id="targetPage" name="nowPage" value="">
	<input type="hidden" id="hyDateL" value="<%=hyDateL%>">
	<input type="hidden" id="hyDateR" value="<%=hyDateR%>">
	<div style="width:800px">
	
	<%if(cList==null||cList.size()==0){
	%>
	<span style="font-size:30px">未查到任何满足条件的反结账记录</span>
	<%
	}else{%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="list_table" class="list_table"> 
		  
		   <thead>
               <tr class="tr">
               	 <th>序号</th>
               	 <th>订购产品</th>
               	 <th>电话</th>
               	 <th>送货地址</th>
               	 <th>姓名</th>
               	 <th>日期</th>
               	 <th>销售员</th>
               	 <th>送货员</th>
               	 <th>反结账时间</th>
               	 <th>反结账操作人</th>
                </tr>
            </thead>
            <tbody>
            	<%for(int i = 0;i<cList.size();i++){ 
            		OrderSer vs =new OrderSerImpl();
            	%>
            	<tr class="tr">
            		<td><%=i+1 %></td>
            		<td>
            			<%=vs.selBuyGood(cList.get(i).getDdCode()) %>
            		</td>
            		<td><%=cList.get(i).getCustomerTel() %></td>
            		<td><%=cList.get(i).getCustomerAddress() %></td>
            		<td><%=cList.get(i).getCustomerName() %></td>
            		<td><%=cList.get(i).getDdTime() %></td>
            		<td><%=cList.get(i).getCashier() %></td>
            		<td><%=cList.get(i).getSonghuoPeople() %></td>
            		<td><%=cList.get(i).getoTime() %></td>
            		<td><%=cList.get(i).getoPeople() %></td>
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