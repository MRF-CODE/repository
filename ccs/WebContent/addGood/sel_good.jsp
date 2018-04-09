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
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function updateGood(x){
		var iWidth = 800;
		var iHeight = 500;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/selOneGood.action?goodCode="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			var myForm = document.getElementById("myForm");
	   		myForm.action = "selGood.action";
	   		myForm.submit();
		}
	  	function changeSkind(){
	   		 var s = document.getElementById("storeSelect3");
	   		var index=s.selectedIndex ;
	   		  if(s.options[index].text == "全部"){
	   			document.getElementById("skind").value = "全部";
	   		} else{
	   			document.getElementById("skind").value = s.options[index].text;
	   		}  
	   	}
	  	function changeBkind(){
	   		 var s = document.getElementById("storeSelect1");
	   		var index=s.selectedIndex ;
	   		  if(s.options[index].text == "全部"){
	   			document.getElementById("bkind").value = "全部";
	   		} else{
	   			document.getElementById("bkind").value = s.options[index].text;
	   		}  
	   	}
	  	function selGood(){
       		var myForm = document.getElementById("myForm");
       		var bkind = document.getElementById("bkind").value;
    		var skind = document.getElementById("skind").value;
    		var goodName = document.getElementById("goodName").value;
    		myForm.action = "<%=path%>/selGood.action?skind="+skind+"&goodName="+goodName+"&bkind="+bkind;
    		myForm.target = "gFrame";
    		myForm.submit();
       	}
   </script>
   <script type="text/javascript">
	function loadBkind(){
		var request = new XMLHttpRequest();
		request.onreadystatechange = function (){
			if(request.readyState == 4){
				var data = request.responseText;
				var dataarr = eval(data);
				var bkind = document.getElementById("bkind");
				for(var i = 0 ; i < dataarr.length ; i++ ){
					var option = document.createElement("option");
					option.innerText=dataarr[i].NAME;
					bkind.appendChild(option);
				}
			}
		};
		request.open("post", "<%=path%>/loadBkind.action", true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		request.send("");
	}
	function loadSkind(){
		var skind = document.getElementById("skind");
		var arr = skind.childNodes;
		var length = arr.length;
		for(var i = 0 ; i < length ; i ++){
			skind.removeChild(skind.lastChild);
		}
		var request = new XMLHttpRequest();
		request.onreadystatechange = function (){
			if(request.readyState == 4){
				var data = request.responseText;
				var dataarr = eval(data);
				var skind = document.getElementById("skind");
				var option1 = document.createElement("option");
				option1.innerText="全部";
				skind.appendChild(option1);
				for(var i = 0 ; i < dataarr.length ; i++ ){
					var option = document.createElement("option");
					option.innerText=dataarr[i].NAME;
					skind.appendChild(option);
				}
				
			}
		};
		request.open("post", "<%=path%>/loadSkind.action", true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var bkind = document.getElementById("bkind");
		if(bkind.value == "全部"){
			//选择了全部，清空select 的option 只加上一个全部
			var elem_child = skind.childNodes;
			for(var i=0; i<elem_child.length;i++){
				//循环清除最后一个子节点
				skind.removeChild(skind.lastChild);
			}
			var opt = document.createElement("option");
			opt.innerText="全部";
			skind.appendChild(opt);
		}else
		request.send("code="+bkind.value);
	}
	window.onload = function(){
		loadBkind();
	};
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<body>
	<div style="width:850px;display:inline-block;height:1000px;">
	<form id="myForm" method="post">
	<input type="hidden" id="targetPage" name="nowPage" value="">
	<div style="width:850px;height:80px;">
		
		<table>
			<tr>
			<td>商品大类</td>
			 <td> 
			<select id="bkind" name="goodBkind" class="select" style="width:100px;" onchange="loadSkind()">
						<option value="全部">全部</option>
					</select>
			<td>商品小类</td>
			 <td> 
				<select id="skind" name="goodSkind" class="select" style="width:100px;">
						<option>全部</option>
					</select>
				</td> 
				<td>按商品名查询</td>
				  <td>		
						<input type="text" id="goodName" class="layui-input" style="width:100px" name="goodName"/>
				</td>
			          <td>		
							<input class="layui-btn" type="button" onclick="selGood()" value="查询商品"/>
					  </td>
			 </tr>
		</table>
		</div>	
		</form>
		
	<div>
		<iframe id="iframe" src="<%=path%>/selGood.action?bkind=全部&skind=全部&goodName=" name="gFrame" frameborder="no" scrolling="auto" width="850px" height="400px" ></iframe>
	</div>
	</div>
</body>
</html>