package com.hzyc.ccs.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hzyc.ccs.client.model.DataStore;

public class WriteLog {
	
	public static void write( Exception e , String info){
		String exp = getExceptionTrace(e);
		/*Log4JLogger l = new Log4JLogger("log4j.properties"); //初始化日志驱动
		l.logDebug("--->dug记录");
		l.logError("日志正在记录...");
		l.logInfo("消息记录中...");
		try {
		Log4JLogger ll = null;
		ll.logDebug("---");
		} catch (Exception e) {
		l.logDebug(e, e.fillInStackTrace());
		       l.logError(e,e.fillInStackTrace());
		}*/
		Date date  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = sdf.format(date);
		File file  = new File("D:/log.txt");
		if(file.exists()){
			
		}else{
			boolean b;
			try {
				b = file.createNewFile();
				if(b){
					System.out.println("生成文件");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		System.out.println("有异常..."+"开始记录.....");
		//记录
		String s =currentDate+":"+info+":"+exp+"-------------------";
		/*byte[] contentInBytes = s.getBytes();
		FileOutputStream fo = new FileOutputStream(file,true);
		fo.write(contentInBytes );
		*/
	        FileWriter fw = null;
			try {
				fw = new FileWriter(file,true);
				fw.write(s);
		        fw.write("\r\n");
		        fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //设置成true就是追加
			finally{
				if(fw != null){
					try {
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
	        
		
		
	}
	
	public static void writeData(ArrayList<String> sqls,String store, String info){

		Date date  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = sdf.format(date);
		File file  = new File("D:/dataerror.txt");
		if(file.exists()){
			
		}else{
			boolean b;
			try {
				b = file.createNewFile();
				if(b){
					System.out.println("生成文件");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		System.out.println("有异常..."+"开始记录.....");
		//记录
		String s =currentDate+":"+info+":"+sqls+"-------------------"+store;
	        FileWriter fw = null;
			try {
				fw = new FileWriter(file,true);
				fw.write(s);
		        fw.write("\r\n");
		        fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //设置成true就是追加
			finally{
				if(fw != null){
					try {
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
	        
	}
	 

	public static String getExceptionTrace(Throwable e){
		if(e!=null){
		 StringWriter sw = new StringWriter();
		 PrintWriter pw = new PrintWriter(sw);
		 e.printStackTrace(pw);
		 pw.flush();
		 pw.close();
		 return sw.toString();
		}else{
		 String result = "No Exception";
		 return result;
		}
	}

}
