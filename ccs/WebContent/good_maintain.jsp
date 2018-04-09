<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function addbkind(){
		document.getElementById("iframe").src="addGood/add_bkind.jsp";
	}
	function addskind(){
		document.getElementById("iframe").src="addGood/add_skind.jsp";
	}
	function addgood(){
		document.getElementById("iframe").src="addGood/add_good.jsp";
	}
</script>
</head>
<body>
<div style="width:200px;height:600px;float:left">
商品维护<br />
		<form action="selGood.action" method="post" id="myForm" target="bottom2">
		<!-- 大类：<input style="width:180px;height:50px" type="text" name="goodBkind"/><br />
		小类：<input style="width:180px;height:50px" type="text" name="goodSkind"/><br />
		 商品名：<input style="width:180px;height:50px" type="text" name="goodName"/><br /> -->
		 <input style="width:180px;height:50px" type="submit" value="查询商品">
		 </form>
	 <input style="width:180px;height:50px" type="button" onclick="addbkind()" value="添加大类"/><br />
	 <input style="width:180px;height:50px" type="button" onclick="addskind()" value="添加小类"/><br />
	 <input style="width:180px;height:50px" type="button" onclick="addgood()" value="添加商品"/><br />
</div>
	<div style="width:800px;height:600px;float:left;">
		<iframe id="iframe" name="bottom2" frameborder="no" scrolling="auto" width="100%" height="500px" ></iframe>
	</div>
</body>
</html>