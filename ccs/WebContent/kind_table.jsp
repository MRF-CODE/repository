<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.system.DataDictionary"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
	String path = request.getContextPath();%>
    <%
    /*包含裱花岗的map  */
    HashMap<String,Double> fMap = (HashMap<String,Double>)request.getAttribute("fMap");
    /*包含店铺的map  */
    HashMap<String,Double> oMap = (HashMap<String,Double>)request.getAttribute("oMap");
    /*包含店铺的配送费map  */
    HashMap<String,Double> pMap = (HashMap<String,Double>)request.getAttribute("pMap");
    /*包含小类的map  */
    HashMap<String,Double> cMap = (HashMap<String,Double>)request.getAttribute("cMap");
    JDBCTools jt = new JDBCTools();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=path%>/js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script src="<%=path%>/css/laydate/laydate.js"></script>
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
<title>Insert title here</title>
 <style>

body{
	margin:0;
	padding:0;
	font:70% Arial, Helvetica, sans-serif; 
	color:#000;
	line-height:150%;
	text-align:left;
}
.imgtable tr{
 		height:40px;
 	}
 	 *{
 	font-size:14px;
 	font-family:'微软雅黑';
 }
</style>
</head>
<body>
	<%if(fMap==null){ %>
	<%}else{ %>
	<table class="">
		    <thead>
		    		<tr><td style="width:80px;">总收入</td>
		    		 <%
						ArrayList<HashMap<String,String>> aList = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='1' ");	
					%>
					<td style="width:80px;"><%=fMap.get("总收入") %>+(定金：<%=fMap.get("定金") %>)</td></tr>
            </thead>
            <tbody>
						
					  <%
					  for(HashMap<String,String> i :aList){
					 %>
					 	<tr><td style="width:80px;"><%=i.get("NAME") %></td>
					 	<td style="width:80px;"><%=fMap.get(i.get("NAME")) %></td>
					 	</tr>
					<%} %> 
            </tbody>
		</table> 
	<%} %>
</body>
</html>