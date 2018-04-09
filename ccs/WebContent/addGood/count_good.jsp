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
</style>

<script type="text/javascript">
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			var myForm = document.getElementById("myForm");
	   		myForm.action = "countGood.action";
	   		myForm.submit();
		}
	  	function getDate(){
        	var d = new Date();
        	var y = d.getYear()+1900;
        	var m = d.getMonth()+1;
        	var da = d.getDate();
        	var clock= y+"-";
        	var qsrq = y+"-";
        	if(m < 10){
        		clock += "0";
        		qsrq += "0";
        	}
        	clock += m + "-";
        	qsrq += m + "-";
        	if(da < 10){
        		clock += "0";
        		qsrq += "0";
        	}
        	clock += da;
        	qsrq += da;
        	 //开始日期为一年前
        	document.getElementById("qsrq").value = qsrq;
        	 //截止日期为当前日期
        	document.getElementById("jzrq").value = clock;
       	}
       
       	function getElementAbsPos(e)   
       	{  
       	    var t = e.offsetTop;  
       	    var l = e.offsetLeft;  
       	    while(e = e.offsetParent)  
       	    {  
       	        t += e.offsetTop;  
       	    l += e.offsetLeft;  
       	    }  
       	  
       	    return {left:l,top:t};  
       	}  
       	function countGood(){
       		var myForm = document.getElementById("myForm1");
       		var hyDateL = document.getElementById("qsrq").value;
    		var hyDateR = document.getElementById("jzrq").value;
    		var bkind = document.getElementById("bkind").value;
    		var skind = document.getElementById("skind").value;
    		var goodName = document.getElementById("goodName").value;
    		myForm.action = "<%=path%>/countGood.action?hyDateL="+hyDateL+"&hyDateR="+hyDateR+"&skind="+skind+"&goodName="+goodName+"&bkind="+bkind;
    		myForm.target = "xFrame";
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
		if(bkind.value == ""){
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
		getDate();
		setTimeout(countGood,100);
	};
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script src="<%=path%>/css/laydate/laydate.js"></script>
</head>
<body>
<div id="container" style="width:800px; height:800px;margin:0 auto;">
	<div style="width:800px;height:86px;">
	<form id="myForm1" method="post">
		<table>
			<tbody>
			<tr style="height:43px;">
			 <td style="width:100px; text-align:center;">商品大类：</td>
			 <td> 
					<select id="bkind" name="goodBkind" class="select" style="width:100px;" onchange="loadSkind()">
						<option value="">全部</option>
					</select>
				</td> 
			<td style="width:100px; text-align:center;">商品小类：</td>
			<td> 
					<select id="skind" name="goodSkind" class="select" style="width:100px;">
						<option>全部</option>
					</select>
				</td> 
				<td colspan="2">
					&nbsp; &nbsp;商品名称：&nbsp; &nbsp;
					<input type="text" id="goodName" style="width:200px; height:30px; border:1px solid #a9a9dd" name="goodName"/>
					</td>
			 </tr>
			</tbody>
			<tfoot>
			<tr style="height:43px;">
				<td style="width:100px; text-align:center;"> 起始日期：</td>
				<td colspan="2">
			      <input id="qsrq" name="hyDateL" class="laydate-icon" onclick="laydate()" style="height:30px; background-color: #f7fcfe; "/>
			    </td>    
				<td style="width:100px; text-align:center;"> 截至日期：</td>
				<td>
			      <input id="jzrq" name="hyDateR" class="laydate-icon" onclick="laydate()" style="height:30px; background-color: #f7fcfe; "/>
			    </td>
			    <td>		
					&nbsp; &nbsp;&nbsp; &nbsp;<input class="layui-btn" type="button" onclick="countGood()" value="商品销售情况"/>
				</td>
			 </tr>
			 </tfoot>
		</table>
	</form>
	</div>	
	<div>
		<iframe id="iframe" src="" name="xFrame" frameborder="no" scrolling="auto" style="width:800px;height:500px;margin-top:30px"></iframe>
	</div>
</div>
</body>
</html>