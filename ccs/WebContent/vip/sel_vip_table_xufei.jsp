<%@page import="java.math.BigDecimal"%>
<%@page import="com.hzyc.ccs.model.Temp"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@page import="com.hzyc.ccs.model.Vip"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
    List<Vip> vList = (List)request.getAttribute("vList");
    Integer nowPage =  0;
    if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	}else{
		nowPage = 1;
	}
	Integer lastPage = (Integer) request.getAttribute("lastPage");
	Temp t = (Temp)request.getAttribute("t");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
	.ahand{cursor: pointer;}
</style>
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/main.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script type="text/javascript" src="<%=path%>/css/layui/layui.js"></script>
<script type="text/javascript">
	//查询vip的日志方法
	function selVipLog(x){
		var iWidth = 1000;
		var iHeight = 600;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/vip/selVipLog.action?code="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
	//查询vip的详情方法
	function selVipDetail(x){
		var iWidth = 1000;
		var iHeight = 600;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2 ;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		var win = window.open("<%=path%>/vip/selVipDetail.action?code="+x, "弹出窗口", "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	}
	function deleteVip(x){
		var myForm = document.getElementById("myForm");
		myForm.action = "<%=path%>/vip/delVip.action?code="+x;
   		myForm.submit();
	}
	  	function fenye(x){
			document.getElementById("targetPage").value = x;
			var myForm = document.getElementById("myForm");
    		myForm.action = "<%=path%>/vip/selVipByXuFei.action";
	   		myForm.submit();
		}
   </script>
</head>
<body>
	<div style="width:1000px">
	<form id="myForm" method="post">
	<input type="hidden" id="targetPage" name="nowPage" value="">
	<div style="width:1000px">
	
	<%if(vList==null||vList.size()==0){
	%>
	<span style="font-size:30px">未查到任何满足条件的会员</span>
	<%
	}else{%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table" >
		  
		   <thead>
               <tr class="tr">
               	 <th width="180px">卡号</th>
               	 <th width="180px">级别</th>
               	  <th width="180px">电话</th>
				  <th width="280px">姓名</th>
               	  <th width="180px">余额</th>
               	  <th width="180px">消费次数</th>
               	  <th width="180px">总充值</th>
				  <th width="180px">总消费</th>
               	  <th colspan="2">功能</th>
               	  <th width="80px">操作</th>
                </tr>
                <tr>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                	<td></td>
                </tr>
            </thead>
            <tbody>
            	<%for(int i = 0;i<vList.size();i++){ %>
            	<tr class="tr">
            		<td width="180px"><%=vList.get(i).getHyCode() %></td>
            		<td width="180px"><%=vList.get(i).getHyKindCode() %></td>
            		<td width="180px"><%=vList.get(i).getHyTel() %></td>
            		<td width="280px"><%=vList.get(i).getHyName() %></td>
            		<td width="180px"><%=vList.get(i).getHyRemainMoney() %></td>
            		<td width="180px"><%=vList.get(i).getHyCunt() %></td>
            		<td width="180px"><%=vList.get(i).getHyTotal() %></td>
            		<% BigDecimal b1 = new BigDecimal(Double.parseDouble(vList.get(i).getHyTotal())*100);
	           		 String.valueOf((int)b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
	        		 BigDecimal b2 = new BigDecimal(Double.parseDouble(vList.get(i).getHyRemainMoney())*100);
	        		 String.valueOf((int)b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()); 
        		 	  double ss = b1.subtract(b2).doubleValue();  
        		 	 String result = String.format("%.2f", ss/100);
        		 	  %>
            		<td width="180px"><%=result %></td>
            		<td width="80px"><input type="button" class="layui-btn" value="日志"  onclick="selVipLog('<%=vList.get(i).getHyCode() %>')"></td>
            		<td width="80px"><input type="button" class="layui-btn" value="详情" onclick="selVipDetail('<%=vList.get(i).getHyCode() %>' )"></td>
            		<td width="80px"><input type="button" class="layui-btn" value="删除" onclick="if(confirm('确认删除会员吗?')){deleteVip('<%=vList.get(i).getHyCode() %>')} else{return false;} "></td>
            	</tr>
            	<% }%>
            </tbody>
           <tfoot>
            	<tr>
            		<td colspan="3"><span style="font-size:20px">会员数量<span style="color:#ff0000"><%=t.getTotalCunt() %></span></span> </td>
            		<td colspan="3"><span style="font-size:20px">总消费<span style="color:#ff0000"><%=t.getTotalExpense() %></span>元</span> </td>
            		<td colspan="3"><span style="font-size:20px">总余额<span style="color:#ff0000"><%=t.getTotalRemain() %></span>元</span> </td>
            		<td colspan="2"><span style="font-size:20px">总充值<span style="color:#ff0000"><%=t.getTotaladd() %></span>元</span> </td>
            	</tr>
            </tfoot>
		</table>
		 
	</div>
	<div class="page mt10" style="margin-left: 10px;">
								<div class="pagination"><ul>
										<li class="first-child">
										<a class="ahand" class="ahand" onclick="fenye(1)">首页</a>
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