package com.hzyc.ccs.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import com.google.gson.Gson;
import com.hzyc.ccs.client.model.DataStore;
import com.hzyc.ccs.tools.GetNowTime;
import com.hzyc.ccs.tools.JDBCTools;
import com.hzyc.ccs.tools.NewThread;
import com.hzyc.ccs.tools.StringtoList;
import com.hzyc.ccs.tools.WriteLog;
import com.hzyc.ccs.web.service.WebserviceTest;

public class WebServiceTestImpl implements WebserviceTest {
	ArrayList<HashMap<String,String>> list;
	
	public static Vector<DataStore> v = new Vector<DataStore>();
	//判断是否需要执行线程的标记
	public static boolean startFlag = true;

	Gson g = new Gson();
	StringtoList stringtoList = new StringtoList();
	
	//向客户端发送店铺的数据
	public String setStore(){
		JDBCTools jt = new JDBCTools();
		String sql = "select * from store_sign";
		list = jt.find(sql);
		String storeString = g.toJson(list);
		return storeString;
	}
	
	
	//获取数据库中的sql语句传过去
	public String getData(String code){
		JDBCTools jt = new JDBCTools();
		//获取当天凌晨的时间
		String startTime = GetNowTime.getStartTime();
			String sql = "SELECT * FROM syn_data WHERE time >= '"+startTime+"'  and CODE NOT IN(SELECT CODE FROM syn_data_sign WHERE store='"+code+"' and time >= '"+startTime+"')";
			//客户端要执行的sql语句 
			ArrayList<HashMap<String,String>> sList = jt.find(sql);
			//String sql2 = "UPDATE store_sign SET flag = '0' where id = '"+code+"'";
			ArrayList<String> sqls = new ArrayList<String>();
			for(int i = 0;i<sList.size();i++){
				String sql1 = "insert into syn_data_sign(code,store,time) values('"+sList.get(i).get("code")+"','"+code+"',now())";
				sqls.add(sql1);
			}
			try {
				jt.batchnorollback(sqls);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return g.toJson(sList);
	}
	
	
	@SuppressWarnings("finally")
	public boolean sednData(String data,String store) throws Exception{
		try{
			//把数据用对象的形式保存起来
			DataStore ds = new DataStore();
			ds.setData(data);
			ds.setStore(store);
			v.add(ds);
			
			//如果没启动，则启动
			if(startFlag){
				NewThread nt = new NewThread();
				startFlag = false;
				nt.start();
				Thread.sleep(20);
			}
		}catch(Exception e){
			e.printStackTrace();
			WriteLog.write(e, "前台传输数据时异常");
		}finally{
			return true;
		}
		
	}
	
	public boolean insertData(DataStore ds) throws Exception{
		boolean flag = false;
		
		String data = ds.getData();
		String store = ds.getStore();
		List<Map<String,String>> aList = stringtoList.jsonStringToList(data);
		//所有需要直接执行的sql
		ArrayList<String> sqls =  new ArrayList<String>();
		//所有要添加进syn_data的sql
		//ArrayList<String> sqls1 = new ArrayList<String>();
		for(int i = 0;i<aList.size();i++){
			
			String sql = aList.get(i).get("syn_sql");
			//执行全部sql
			sqls.add(sql);
			//如果语句包含vip相关操作，则插入syn_data表中
			if(sql.contains("INSERT INTO vip")||sql.contains("UPDATE vip")||sql.contains("INSERT INTO vip_record")||sql.contains("UPDATE vip_record")){
				//long time = System.currentTimeMillis();
				String time = UUID.randomUUID().toString();
				//要执行sql的副本
				String sqlcopy = sql;
				sqlcopy = sqlcopy.replace("'", "\\'");
				//插入同步表中
				String sql2 = "insert into syn_data(code,syn_sql,time) values('"+time+"','"+sqlcopy+"',now())";
				//插入同步记录表中
				String sql1 = "insert into syn_data_sign(code,store,time) values('"+time+"','"+store+"',now())";
				sqls.add(sql1);
				sqls.add(sql2);
			}
		}
		
		JDBCTools jt = new JDBCTools();
		try{
			flag = jt.batchWithRollback(sqls);
		}catch(Exception e){
			e.printStackTrace();
			WriteLog.write(e, "批量的返回值"+flag);
		}
		
		return flag;
	}
}