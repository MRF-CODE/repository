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
	String hyDateL = (String)request.getAttribute("hyDateL");
	String hyDateR = (String)request.getAttribute("hyDateR");
	String skind = (String)request.getAttribute("skind");
	String bkind = (String)request.getAttribute("bkind");
	String goodName =(String)request.getAttribute("goodName");
	Double totalPrice = (Double)request.getAttribute("totalPrice");
	Integer totalNumber = (Integer)request.getAttribute("totalNumber");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
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
	<input type="hidden" id="skind" value="<%=skind %>">
	<input type="hidden" id="bkind" value="<%=bkind %>">
	<input type="hidden" id="goodName" value="<%=goodName %>">
 	<div style="width:800px">
	
	<%if(gList==null||gList.size()==0){
	%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id="list_table" class="list_table"> 
		  <!-- <table class="imgtable" style="margin-top:30px;font-size: 20px"> -->
		   <thead>
               <tr class="tr">
               	  <th class="sorttable_nosort" align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品名称</a></th>
               	  <th align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品大类</a></th>
               	  <th align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品小类</a></th>
               	  <th class="number" align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品销售钱数</a></th>
               	  <th class="number" align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >销售数量</a></th>
                </tr>
            </thead>
            <tfoot>
            	<tr>
            	<td colspan="5">暂无商品信息</td>
            	</tr>
            </tfoot>
		</table>
	<%
	}else{%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="list_table" class="list_table"> 
		  <!-- <table class="imgtable" style="margin-top:30px;font-size: 20px"> -->
		   <thead>
               <tr class="tr">
               	  <th class="sorttable_nosort" align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品名称</a></th>
               	  <th align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品大类</a></th>
               	  <th align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品小类</a></th>
               	  <th class="number" align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >商品销售钱数</a></th>
               	  <th class="number" align="center"><a href="javascript:" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" >销售数量</a></th>
                </tr>
            </thead>
            <tbody>
            	<%for(int i = 0;i<gList.size();i++){ %>
            	<tr class="tr">
            		<td><%=gList.get(i).getGoodName() %></td>
            		<td><%=gList.get(i).getGoodBkind() %></td>
            		<td><%=gList.get(i).getGoodSkind() %></td>
            		<td><%=gList.get(i).getTotalPrice() %></td>
            		<td><%=gList.get(i).getNumber() %></td>
            	</tr>
            	<% }%> 
            	
            
            </tbody>
            <tfoot>
            	<tr>
            	<td colspan="3">总计</td>
            	<td><%=totalPrice %></td>
            	<td><%=totalNumber %></td>
            	</tr>
            </tfoot>
		</table>
		  
	</div>
							
		<%
	}%>
						
		</form>
		</div>
</body>
</html>