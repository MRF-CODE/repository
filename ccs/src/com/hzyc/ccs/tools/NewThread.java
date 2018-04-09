package com.hzyc.ccs.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.hzyc.ccs.web.service.impl.WebServiceTestImpl;

/**
 * 这个线程是用来监控vector里面有没有数据的，如果有数据则相数据库同步
 * @author Administrator
 *
 */
public class NewThread extends Thread{
	
	public void run(){
		int count = 0;
			WebServiceTestImpl wsti = new WebServiceTestImpl();
	        try {
				while(WebServiceTestImpl.v.size()>0){
					System.out.println(WebServiceTestImpl.v.size()+"=================");
					boolean flag = wsti.insertData(WebServiceTestImpl.v.get(0));
					if(flag){
						WebServiceTestImpl.v.remove(0);
					}else{
						count++;
					}
					if(count == 5){
						//转化成sqls,把sqls存在文件中
						StringtoList stringtoList = new StringtoList();
						List<Map<String,String>> aList = stringtoList.jsonStringToList(WebServiceTestImpl.v.get(0).getData());
						ArrayList<String> sqls =  new ArrayList<String>();
						for(int i = 0;i<aList.size();i++){
							String sql = aList.get(i).get("syn_sql");
							sqls.add(sql);
						}
						WriteLog.writeData(sqls,WebServiceTestImpl.v.get(0).getStore(), "数据传输5次失败");
						//error ----> log
						WebServiceTestImpl.v.remove(0);
						count = 0;
					}
				}
				//run执行到最后，循环结束了，startFlag可以被从外部重新启动了
				WebServiceTestImpl.startFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
				WriteLog.write(e, "while运行异常");
			}
			
		}
		
}
