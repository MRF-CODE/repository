<%@page import="java.util.Map"%>
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
    /*包含支付方式的map  */
    HashMap<String,HashMap<String,Double>> sMap = (HashMap<String,HashMap<String,Double>>)request.getAttribute("sMap");
    /*包含裱花岗的map  */
    HashMap<String,Double> fMap = (HashMap<String,Double>)request.getAttribute("fMap");
    /*包含店铺的map  */
    HashMap<String,Double> oMap = (HashMap<String,Double>)request.getAttribute("oMap");
    /*包含店铺的配送费map  */
    HashMap<String,Double> pMap = (HashMap<String,Double>)request.getAttribute("pMap");
    /*包含小类的map  */
    HashMap<String,Double> cMap = (HashMap<String,Double>)request.getAttribute("cMap");
    /* 包含实收和应收 的map */
    HashMap<String,Double> aMap = (HashMap<String,Double>)request.getAttribute("aMap");
    /* 查询所有会员的总冲值金额*/
    HashMap<String,String> tMap = (HashMap<String,String>)request.getAttribute("tMap");
    JDBCTools jt = new JDBCTools();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script src="<%=path%>/css/laydate/laydate.js"></script>
<link rel="stylesheet" href="<%=path%>/css/cityselect.css">
<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="tablecloth/tablecloth.js"></script>
<title>Insert title here</title>
 <style>

body{
	margin:0;
	padding:0;
	background:#f1f1f1;
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
		<%if(aMap==null){ %>
	<%}else{ %>
		<table class="imgtable" style="margin-top:30px">
		    <thead>
		    	<tr>
		    		<td>应收</td>
		    		<td>实收</td>
		    		<td>会员的充值金额</td>
		    	</tr>
            </thead>
            <tbody>
            	<tr>
            		<td><%=aMap.get("应收") %></td>
            		<td><%=aMap.get("实收") %></td>
            		<td><%=tMap.get("会员充值金额") %></td>
            	</tr>
            </tbody>
		</table>
	<%} %>	
	<%-- <%if(sMap==null){ %>
	<%}else{ %>
		<table class="imgtable" style="margin-top:30px;font-size: 20px">
            <%
            	//去掉货到付款
            	for(String i: sMap.keySet()){
            		HashMap<String,Double> oneMap = sMap.get(i);
            		oneMap.remove("货到付款");
            	}
            	int count =  1;
            	for(String i: sMap.keySet()){
            		%>
            		<%
            		HashMap<String,Double> oneMap = sMap.get(i);
            		if(count <= 1){
            			%>
            			<tr>
            				<td>
            			</td>
            			<%
            			for(Map.Entry<String,Double> entry :oneMap.entrySet()){
                			count++;
                			String temp = entry.getKey()+entry.getValue();
                			%>
                				<td><%=entry.getKey()%></td>
                			
                			<%
                		}
            			%>
  
            			</tr>
  					<%
            		}
            		
            		%>
            			
            		
            		<tr>
            			<td>
            				<%=i%>:
            			</td>
            		<%
            		
            		for(Map.Entry<String,Double> entry :oneMap.entrySet()){
            			String temp = entry.getKey()+entry.getValue();
            			%>
            				<td><%=entry.getValue()%></td>
            			
            			<%
            		}
            		%>
            		
            			
            		</tr>
            		<%
            	}
            	
            %>
		</table>
	<%} %> --%>
	<%String storeName = (String)request.getSession().getAttribute("storeName"); %>
	<%if(sMap==null){ %>
	<%}else{ %>
		<table class="imgtable" style="margin-top:30px;font-size: 20px">
            <%
            	//去掉货到付款
            	for(String i: sMap.keySet()){
            		HashMap<String,Double> oneMap = sMap.get(i);
            		oneMap.remove("货到付款");
            	}
            	int count =  1;
            	for(String i: sMap.keySet()){
            		%>
            		<%
            		HashMap<String,Double> oneMap = sMap.get(i);
            		if(count <= 1){
            			%>
            			<tr>
            				<td>
            			</td>
            			<%
            			for(Map.Entry<String,Double> entry :oneMap.entrySet()){
                			count++;
                			String temp = entry.getKey()+entry.getValue();
                			%>
                				<td><%=entry.getKey()%></td>
                			
                			<%
                		}
            			%>
            			</tr>
  					<%
            		}
            		
            		%>
            		<tr>
            			<td>
            				<%=i%>:
            			</td>
            		<%
            		for(Map.Entry<String,Double> entry :oneMap.entrySet()){
            			String temp = entry.getKey()+entry.getValue();
            			%>
            				<td><%=entry.getValue()%></td>
            			<%
            		}
            		%>
            		</tr>
            		<%
            	}
            %>
		</table>
	<%} %> 
	<%if(fMap==null){ %>
	<%}else{ %>
	<table class="imgtable" style="margin-top:30px">
		    <thead>
		    	<tr>
		    		<td style="width:80px;">总收入</td>
		    		 <%
						ArrayList<HashMap<String,String>> aList = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='1' ");	
					%><% for(HashMap<String,String> i :aList){%>  
					 	<td style="width:80px;"><%=i.get("NAME") %></td>
					 <% } %>
		    	</tr>
            </thead>
            <tbody>
            		<tr>
						<td style="width:80px;"><%=fMap.get("总收入") %>+(定金：<%=fMap.get("定金") %>)</td>
					  <%
					  for(HashMap<String,String> i :aList){
					 %>
					 	<td style="width:80px;"><%=fMap.get(i.get("NAME")) %></td>
					<%} %> 
		    	</tr>
            </tbody>
		</table> 
	<%} %>
	<%if(oMap==null){ %>
	<%}else{ %>
		<table class="imgtable" style="margin-top:30px">
		  
		    <thead>
		    	<tr>
		    		<%
						ArrayList<HashMap<String,String>> aList1 = jt.find("SELECT sell_store FROM store_sign");
					%>
						<td style="width:80px;">总收入</td>
					  <%
					 for(HashMap<String,String> i :aList1){
					 %>
					 	<td style="width:80px;"><%=i.get("sell_store") %></td>
					<%} %> 
		    	</tr>
            </thead>
            <tbody>
            	<tr>
						<td style="width:80px;"><%=oMap.get("总收入") %></td>
					  <%
					 for(HashMap<String,String> i :aList1){
					 %>
					 	<td style="width:80px;"><%=oMap.get(i.get("sell_store")) %></td>
					<%} %> 
		    	</tr>
            </tbody>
		</table>
	<%} %>	
		<%if(cMap==null){ %>
	<%}else{ %>
		<table class="imgtable" style="margin-top:30px">
		  
		    <thead>
		    	<tr>
		    		<%
		    			ArrayList<HashMap<String,String>> aList2 = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='2'");
					%>
						<td style="width:80px;">总收入</td>
					  <%
					 for(HashMap<String,String> i :aList2){
					 %>
					 	<td style="width:80px;"><%=i.get("NAME") %></td>
					<%} %> 
		    	</tr>
            </thead>
            <tbody>
            		<tr>
						<td style="width:80px;"><%=cMap.get("总收入") %>+(定金：<%=fMap.get("定金") %>)</td>
					  <%
					 for(HashMap<String,String> i :aList2){
					 %>
					 	<td style="width:80px;"><%=cMap.get(i.get("NAME")) %></td>
					<%} %> 
		    	</tr>
            </tbody>
		</table>
	<%} %>		 
</body>
</html>