<%@page import="jxl.write.WritableCellFormat"%>
<%@page import="jxl.write.WritableFont"%>
<%@page import="com.hzyc.ccs.tools.JDBCTools"%>
<%@ page language="java"
	import="java.util.*,java.io.*,jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,jxl.write.Label"
	pageEncoding="UTF-8"%>
<%
	try {
		String city = "北京市";
		String grade = "成绩";
		String sql = "SELECT * FROM vip";
		JDBCTools jt = new JDBCTools();
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		response.setContentType("application/vnd.ms-excel"); //保证不乱码
		String fileName = "小宠家会员excel表格数据.xls";
		/* //到第一个值项是attachment，这是真正的关键，设定了这个值，浏览器就会老老实实地显示另存为对话框，如果这个值设成 inline，则无论怎样浏览器都会自动尝试用已知关联的程序打开文件。
		response.addHeader("Content-Disposition","attachment; filename=\""+ new String(fileName.getBytes("gb2312"),"iso8859-1") + "\""); */
		
		response.setHeader("Content-Disposition", "attachment;"
				+ " filename="
				+ new String(fileName.getBytes(), "ISO-8859-1"));
		OutputStream os = response.getOutputStream();
		out.clear();
		out = pageContext.pushBody(); //这2句一定要，不然会报错。
		// 新建excel的工作薄文件
		WritableWorkbook book = Workbook.createWorkbook(os);
		// 生成名为"第一页"的工作表，参数0表示这是第一页
		WritableSheet sheet1 = book.createSheet("第一页", 0);
		/***********start第一行（各个列的标题）************/
		WritableFont fontTitle = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
		fontTitle.setColour(jxl.format.Colour.BLACK);
		WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);
		formatTitle.setWrap(true);
		//第一列第一行(0,0)
		Label label1 = new Label(0, 0, "姓名",formatTitle);
		// 将定义好的单元格添加到工作表中
		sheet1.addCell(label1);
		//第二列第一行
		Label label2 = new Label(1, 0, "积分",formatTitle);
		sheet1.addCell(label2);
		//第三列第一行
		Label label3 = new Label(2, 0, "余额",formatTitle);
		sheet1.addCell(label3);
		Label label4 = new Label(3, 0, "会员分类",formatTitle);
		sheet1.addCell(label4);
		Label label5 = new Label(4, 0, "联系电话",formatTitle);
		sheet1.addCell(label5);
		Label label6 = new Label(5, 0, "加入日期",formatTitle);
		sheet1.addCell(label6);
		Label label7 = new Label(6, 0, "地址",formatTitle);
		sheet1.addCell(label7);
		Label label8 = new Label(7, 0, "备注",formatTitle);
		sheet1.addCell(label8);
		
		WritableFont bodyTitle = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD);
		bodyTitle.setColour(jxl.format.Colour.BLACK);
		WritableCellFormat bodyT = new WritableCellFormat(bodyTitle);
		//body
		WritableCellFormat body = new WritableCellFormat(bodyT);
		body.setWrap(true);
	    /***********end第一行（各个列的标题）************/
		for(int i=1; i<=aList.size(); i++){	//控制行数（从第二行开始）
			//sheet1.setRowView(i,400); 	
			int[] width = new int[]{10,10,10,20,20,40,40,20};
			for(int j=0 ;j<8 ;j++) { //控制列数
					sheet1.setColumnView(j, width[j]);            
					 
					if(j==0) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_name"),body);
						sheet1.addCell(label);
					}
					if(j==1) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_jf"),body);
						sheet1.addCell(label);
					}
					if(j==2) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_remain_money"),body);
						sheet1.addCell(label);
					}	
					if(j==3) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_kind_code"),body);
						sheet1.addCell(label);
					}
					if(j==4) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_tel"),body);
						sheet1.addCell(label);
					}
					if(j==5) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_date"),body);
						sheet1.addCell(label);
					}	
					if(j==6) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_address"),body);
						sheet1.addCell(label);
					}
					if(j==7) {
						Label label = new Label(j, i, aList.get(i-1).get("hy_bz"),body);
						sheet1.addCell(label);
					}
				}		
		}	
		// 写入数据并关闭文件
		book.write();
		book.close();
		os.close();

	} catch (Exception e) {
		System.out.println("生成信息表(Excel格式)时出错：");
		e.printStackTrace();
	}
%>