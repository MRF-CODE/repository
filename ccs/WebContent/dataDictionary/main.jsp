<%@page import="com.hzyc.ccs.system.DataDictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
   <script src="<%=path%>/layer/layer.js"></script>
   <link rel="stylesheet" href="<%=path%>/css/cityselect.css">
   <script type="text/javascript" src="<%=path%>/js/cityselect.js" charset="gbk"></script>
<title>Insert title here</title>
<style>
	/*向右三角形*/
	.arrow-right{
		width:10;height:0;
		border-top: 5px solid transparent;
		border-bottom: 5px solid transparent;
		border-left: 5px solid #1d78a2;
		margin-top:17px;
		margin-left:6px;
		margin-right:6px;
		float:left;
	}
	.now-position{
		float:left;
	}
	.now-position a:hover{
		text-decoration: underline;
	}
	.input-text{
		width:160px;	
	}
	
</style>
<script>
window.onload = test;

function test(){
	//加载所有角色
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4){
			var data = xmlhttp.responseText;
			
			var array = eval("("+data+")");
			//商品名称下拉框
			var goodNameSelect = document.getElementById("kind");
			/* var option1 = document.createElement("option");
			option1.innerText = "全部";
			option1.value = array;
			goodNameSelect.appendChild(option1); */
			for(var i = 0;i < array.length ;i++){
				var option = document.createElement("option");
				option.innerText = array[i].dic_type_name;
				option.value = array[i].dic_type;
				goodNameSelect.appendChild(option);
			}
		}
	};
	xmlhttp.open("post","<%=path%>/dataDictionary/loadDataDicKind.action",true);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlhttp.send(null);
	
}
function search(){
	
	var value = document.getElementById("kind").value;
	window.open("bottom.jsp?code="+value,"bottom");
}

</script>
 
</head>
<body style="overflow-x:hidden;">


	<div class="wrapper" style="width:1000px;">
		<form name="myForm" action="<%=path%>/baseData/selGoodsSelect.action" target="bottom" method="post">
		<table class="form_table" border="0" cellpadding="0" cellspacing="0">
	        <tr style="height:60px;">
	          <td>分类名称</td>
	          <td >
				<select id="kind" style="border:2px solid #e3e3e3;height:40px;width:150px;"></select>
			  </td>
	          
	          <td rowspan="2" style="vertical-align: middle" >
	          <ul class="toolbar">
		        <li class="click" onclick="search()"><span><img src="<%=path%>/images/t02.png" /></span>查询</li>
		      </ul>
        
	          
	          		
	          </td>
	        </tr>
	        
	   </table>
	   
	   </form>
   </div>
   <div style="width:100%;height:370px;margin-top:20px;">
   		<iframe src="bottom.jsp" name="bottom" frameborder="no" scrolling="auto" width="100%" style="height:370px" allowtransparency="true"></iframe>
   </div>
  
    <!-- 实例化 -->
	<script type="text/javascript">
	var test=new Vcity.CitySelector({input:'citySelect'});
	</script>
	
</body>
</html>