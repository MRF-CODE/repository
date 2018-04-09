<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.hzyc.ccs.system.DataDictionary"%>
<%@page import="com.hzyc.ccs.model.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//List<Goods> gList = (List<Goods>) request.getAttribute("selAllGoods");
	Integer nowPage =  0;
	if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	//Goods outStore = (Goods)request.getAttribute("good");
	Integer lastPage = (Integer) request.getAttribute("lastPage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/css/common.css">
   <link rel="stylesheet" href="<%=path%>/css/main.css">
   <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
   <script type="text/javascript" src="<%=path%>/js/colResizable-1.3.min.js"></script>
   <script type="text/javascript" src="<%=path%>/js/common.js"></script>
   <script type="text/javascript"src="<%=path%>/layer/layer.js" ></script>
<title>查询结果</title>
<style>
tbody tr:hover{
	cursor: pointer;
}

</style>
<script>
//更改数据字典
function updtr(x,y,z,a){
	var tr = document.getElementById("tr"+x);
	tr.parentNode.removeChild(tr);
	var newTr = document.createElement("tr");
	newTr.id = "tr"+x;
	newTr.innerHTML = 
		"<td><input type='hidden' id='zdxtype' value='"+y+"'/>"+y+""+
		"</td>"+
		"<td>"+z+""+
		"</td>"+
		"<td><input type='hidden' id='zdxdm' value='"+a+"'/>"+a+""+
		"</td>"+
		"<td><input type='text' id='zdxmc' name='' style='width:80px;height:30px;background:#99ff00'/>"+
		"</td>"+
		"<td><input type='button' onclick='updDic()' value='确定' style='width:80px;height:30px'/></td>";
	var tbody = document.getElementById("tbody");
	var nodes = tbody.childNodes;
	tbody.appendChild(newTr);
	
}

/*删除数据字典项的方法  */
function deltr(x,y){
	var tr = document.getElementById("tr"+x);
	tr.parentNode.removeChild(tr);  
	subform.action = "remDic.action?zdxdm="+y;
	subform.submit();
}
function addtr(x,y,z,a){
	var newTr = document.createElement("tr");
	newTr.id = "tr"+x;
	
	newTr.innerHTML = 
		"<td>"+y+""+
		"</td>"+
		"<td><input type='hidden' id='zdxtype' value='"+y+"'/>"+z+""+
		"</td>"+
		"<td><input type='text' id='zdxdm' name='' style='width:80px;height:30px;background:#99ff00'/>"+
		"</td>"+
		"<td><input type='text' id='zdxmc' name='' style='width:80px;height:30px;background:#99ff00'/>"+
		"</td>"+
		"<td><input type='button' onclick='addDic()' value='添加' style='width:80px;height:30px'/></td>";
	var tbody = document.getElementById("tbody");
	var nodes = tbody.childNodes;
	tbody.appendChild(newTr);
}
/* function addDic(){
	var zdxtype = document.getElementById("zdxtype").value;
	var zdxdm = document.getElementById("zdxdm").value;
	var zdxmc = document.getElementById("zdxmc").value;
	var subform = document.getElementById("subform");
	subform.action="addDic.action?zdxdm="+zdxdm+"&zdxmc="+zdxmc+"&zdxtype="+zdxtype;
	subform.submit();
} */
function updDic(){
	var zdxtype = document.getElementById("zdxtype").value;
	var zdxdm = document.getElementById("zdxdm").value;
	var zdxmc = document.getElementById("zdxmc").value;
	var subform = document.getElementById("subform");
	subform.action="updDic.action?zdxdm="+zdxdm+"&zdxmc="+zdxmc+"&zdxtype="+zdxtype;
	subform.submit();
}
function addDic(x){
	var iWidth = 800;
	var iHeight = 500;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	var win = window.open("selMaxDic.action?dict_type="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
}
</script>
</head>
<body>
	<%
		JDBCTools jt = new JDBCTools();
		String code = request.getParameter("code");
		Gson g = new Gson();
		
		if(code != null && !code.equals("null")){
			HashMap<String,String> nMap =  DataDictionary.getNexineMap(code);
		
	%>
	<form id="subform" target="bottom" method="post" action="addDic.action">
	<div class="box_border" style="width:1000px">
			<div class="box_top"><b class="pl15">查询结果</b></div>
	</div>
	<div id="table" class="mt10" style="width:1000px">
        <div class="box span10 oh">
        	 
              <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
                <thead>
                <tr>
                   <th width="160px">分类类型</th>
                   <th width="160px">分类名称</th>
                   <th width="160px">字典项代码</th>
                   <th width="160px">字典项名称</th>
                   <th width="400px">操作</th>
                   <th></th>
                </tr>
                </thead>
                <tbody id="tbody">
                <%		int i = 0;
                		for (Map.Entry<String,String> entry : nMap.entrySet()) {
                	    ++i;
                %>
		               	<tr id="tr<%=i %>" class="tr">
		                   <td><%=code%></td>
		                   <td><%=DataDictionary.getKeyName(code)%></td>
		                   <td><%=entry.getKey()%></td>
		                   <td><%=entry.getValue()%></td>
		                   <td>
		                  <%--  <input type="button" value="增加" onclick="addtr('<%=i %>','<%=code%>','<%=DataDictionary.getKeyName(code)%>','<%=entry.getKey()%>')" style="width:80px;height:30px"/> --%>
		                   <input type="button" value="修改" onclick="updtr('<%=i %>','<%=code%>','<%=DataDictionary.getKeyName(code)%>','<%=entry.getKey()%>')" style="width:80px;height:30px"/> 
		                   <input type="button" value="删除" onclick="deltr('<%=i %>','<%=entry.getKey()%>')" style="width:80px;height:30px"/> 
		                   </td>
		                   <%
                			}
		                   %>
            	 </tbody>
            	 <tfoot>
            	 	 <input type="button" value="增加" onclick="addDic('<%=code%>','<%=DataDictionary.getKeyName(code)%>')" style="width:80px;height:30px"/>
            	 </tfoot>
           		 </table>
            	<%
					}else{
						
				%>	
				 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
	                <thead>
	                <tr>
	                   <th width="60">分类类型</th>
	                   <th width="60">分类名称</th>
	                   <th width="50">字典项代码</th>
	                   <th width="50">字典项名称</th>
	                   <th width="110">备注</th>
	                   <th width="110">备注</th>
	                </tr>
	                </thead>
					<tbody>
                <%		
                		ArrayList<HashMap<String,String>> alhs = jt.find("SELECT * FROM dictionary WHERE  dict_type IN (SELECT dic_type FROM dictionary_depict)");
                		for (int i = 0;i<alhs.size() ;i++) {
                %>
		               	<tr class="tr">
		                   <td><%=alhs.get(i).get("dict_type")%></td>
		                   <td><%=DataDictionary.getKeyName(alhs.get(i).get("dict_type")) %></td>
		                   <td><%=alhs.get(i).get("dict_code")%></td>
		                   <td><%=alhs.get(i).get("dict_name")%></td>
		                   <td>
		                   		暂无
		                   </td>
		                   <td>1</td>
		                   <%
                			}
		                   %>
            	 </tbody>
           		 
					
				</table>
				<%		
						
					}
            	%>
                
						
	</div>
	</div>
	</form>
	
</body>
</html>