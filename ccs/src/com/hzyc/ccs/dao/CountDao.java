package com.hzyc.ccs.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hzyc.ccs.model.CancelOrders;
import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.model.Tu;
import com.hzyc.ccs.system.DataDictionary;
import com.hzyc.ccs.tools.GetNowTime;
import com.hzyc.ccs.tools.GoodManager;
import com.hzyc.ccs.tools.JDBCTools;
import com.hzyc.ccs.tools.VipManager;

public class CountDao {
	
	//获取当前时间的方法
	GetNowTime gnt = new GetNowTime();
	//按月统计形成的数组
	public String[] count(String storeName){
		JDBCTools jt = new JDBCTools();
		Date date  = new Date();
		int year = date.getYear() + 1900 ;
		String sql1="";
		String sql2="";
		String sql3="";
		String sql4="";
		String sql5="";
		String sql6="";
		String sql7="";
		String sql8="";
		String sql9="";
		String sql10="";
		String sql11="";
		String sql12="";
		if(storeName.equals("admin")){
			sql1="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-02' AND SUBSTR(dd_time,1,10)>'"+year+"-01'";
			sql2="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-03' AND SUBSTR(dd_time,1,10)>'"+year+"-02'";
			sql3="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-04' AND SUBSTR(dd_time,1,10)>'"+year+"-03'";
			sql4="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-05' AND SUBSTR(dd_time,1,10)>'"+year+"-04'";
			sql5="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-06' AND SUBSTR(dd_time,1,10)>'"+year+"-05'";
			sql6="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-07' AND SUBSTR(dd_time,1,10)>'"+year+"-06'";
			sql7="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-08' AND SUBSTR(dd_time,1,10)>'"+year+"-07'";
			sql8="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-09' AND SUBSTR(dd_time,1,10)>'"+year+"-08'";
			sql9="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-10' AND SUBSTR(dd_time,1,10)>'"+year+"-09'";
			sql10="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-11' AND SUBSTR(dd_time,1,10)>'"+year+"-10'";
			sql11="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-12' AND SUBSTR(dd_time,1,10)>'"+year+"-11'";
			sql12="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+(year+1)+"-01' AND SUBSTR(dd_time,1,10)>'"+year+"-12'";
		}else{
			sql1="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-02' AND SUBSTR(dd_time,1,10)>'"+year+"-01' and sell_store = '"+storeName+"'";
			sql2="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-03' AND SUBSTR(dd_time,1,10)>'"+year+"-02' and sell_store = '"+storeName+"'";
			sql3="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-04' AND SUBSTR(dd_time,1,10)>'"+year+"-03' and sell_store = '"+storeName+"'";
			sql4="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-05' AND SUBSTR(dd_time,1,10)>'"+year+"-04' and sell_store = '"+storeName+"'";
			sql5="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-06' AND SUBSTR(dd_time,1,10)>'"+year+"-05' and sell_store = '"+storeName+"'";
			sql6="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-07' AND SUBSTR(dd_time,1,10)>'"+year+"-06' and sell_store = '"+storeName+"'";
			sql7="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-08' AND SUBSTR(dd_time,1,10)>'"+year+"-07' and sell_store = '"+storeName+"'";
			sql8="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-09' AND SUBSTR(dd_time,1,10)>'"+year+"-08' and sell_store = '"+storeName+"'";
			sql9="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-10' AND SUBSTR(dd_time,1,10)>'"+year+"-09' and sell_store = '"+storeName+"'";
			sql10="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-11' AND SUBSTR(dd_time,1,10)>'"+year+"-10' and sell_store = '"+storeName+"'";
			sql11="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+year+"-12' AND SUBSTR(dd_time,1,10)>'"+year+"-11' and sell_store = '"+storeName+"'";
			sql12="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) <'"+(year+1)+"-01' AND SUBSTR(dd_time,1,10)>'"+year+"-12' and sell_store = '"+storeName+"'";
			
		}

		ArrayList<HashMap<String,String>> list1 = jt.find(sql1);
		ArrayList<HashMap<String,String>> list2 = jt.find(sql2);
		ArrayList<HashMap<String,String>> list3 = jt.find(sql3);
		ArrayList<HashMap<String,String>> list4 = jt.find(sql4);
		ArrayList<HashMap<String,String>> list5 = jt.find(sql5);
		ArrayList<HashMap<String,String>> list6 = jt.find(sql6);
		ArrayList<HashMap<String,String>> list7 = jt.find(sql7);
		ArrayList<HashMap<String,String>> list8 = jt.find(sql8);
		ArrayList<HashMap<String,String>> list9 = jt.find(sql9);
		ArrayList<HashMap<String,String>> list10 = jt.find(sql10);
		ArrayList<HashMap<String,String>> list11 = jt.find(sql11);
		ArrayList<HashMap<String,String>> list12= jt.find(sql12);
		
		String value1 = list1.get(0).get("sumTatal");
		String value2 = list2.get(0).get("sumTatal");
		String value3 = list3.get(0).get("sumTatal");
		String value4 = list4.get(0).get("sumTatal");
		String value5 = list5.get(0).get("sumTatal");
		String value6 = list6.get(0).get("sumTatal");
		String value7 = list7.get(0).get("sumTatal");
		String value8 = list8.get(0).get("sumTatal");
		String value9 = list9.get(0).get("sumTatal");
		String value10 = list10.get(0).get("sumTatal");
		String value11 = list11.get(0).get("sumTatal");
		String value12 = list12.get(0).get("sumTatal");
		if(value1==null){
			value1="0";
		}
		if(value2==null){
			value2="0";
		}
		if(value3==null){
			value3="0";
		}
		if(value4==null){
			value4="0";
		}
		if(value5==null){
			value5="0";
		}
		if(value6==null){
			value6="0";
		}
		if(value7==null){
			value7="0";
		}
		if(value8==null){
			value8="0";
		}
		if(value9==null){
			value9="0";
		}
		if(value10==null){
			value10="0";
		}
		if(value11==null){
			value11="0";
		}
		if(value12==null){
			value12="0";
		}
		String[] value = {value1,value2,value3,value4,value5,value6,value7,value8,value9,value10,value11,value12};
		return value;
	}
	public String[] countByYear(String storeName){
		JDBCTools jt = new JDBCTools();
		Date date  = new Date();
		int year = date.getYear() + 1900 ;
		int year1 = year-2;
		int year2 = year-1;
		int year4 = year+1;
		int year5 = year+2;
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		if(storeName.equals("admin")){
			sql1="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year1+"%'";
			sql2="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year2+"%'";
			sql3="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year+"%'";
			sql4="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year4+"%'";
			sql5="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year5+"%'";
		}else{
			sql1="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year1+"%' and sell_store = '"+storeName+"'";
			sql2="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year2+"%' and sell_store = '"+storeName+"'";
			sql3="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year+"%' and sell_store = '"+storeName+"'";
			sql4="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year4+"%' and sell_store = '"+storeName+"'";
			sql5="SELECT (SUM(pay_way1_shishou_money-zhaoling_money)+SUM(pay_way2_shishou_money)) sumTatal FROM orders WHERE SUBSTR(dd_time,1,10) LIKE '"+year5+"%' and sell_store = '"+storeName+"'";
		}
	
		ArrayList<HashMap<String,String>> list1 = jt.find(sql1);
		ArrayList<HashMap<String,String>> list2 = jt.find(sql2);
		ArrayList<HashMap<String,String>> list3 = jt.find(sql3);
		ArrayList<HashMap<String,String>> list4 = jt.find(sql4);
		ArrayList<HashMap<String,String>> list5 = jt.find(sql5);
		
		String value1 = list1.get(0).get("sumTatal");
		String value2 = list2.get(0).get("sumTatal");
		String value3 = list3.get(0).get("sumTatal");
		String value4 = list4.get(0).get("sumTatal");
		String value5 = list5.get(0).get("sumTatal");
		
		if(value1==null){
			value1="0";
		}
		if(value2==null){
			value2="0";
		}
		if(value3==null){
			value3="0";
		}
		if(value4==null){
			value4="0";
		}
		if(value5==null){
			value5="0";
		}
		String[] value = {value1,value2,value3,value4,value5};
		return value;
	}
	public HashMap<String,Double> countByStore(String hyDateL,String hyDateR,String storeName){
		String sql = "";
		if(storeName.equals("admin")){
			sql="SELECT sell_store,SUM(yingshou_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' GROUP BY sell_store";
		}else{
			sql="SELECT sell_store,SUM(yingshou_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"' GROUP BY sell_store";
		}
		JDBCTools jt = new JDBCTools();
    	ArrayList<HashMap<String,String>> tList = jt.find(sql);
    	HashMap<String,Double> oneMap = new HashMap<String,Double>();
    	for(int i = 0;i<tList.size();i++){
    		oneMap.put(tList.get(i).get("sell_store"), Double.parseDouble(tList.get(i).get("SUM(yingshou_money)")));
    	}
    	return oneMap;
	}
	public List<Tu> countByDate(String hyDateL,String hyDateR,String storeName){
		String sql = "";
		if(storeName.equals("admin")){
			sql="SELECT SUBSTR(dd_time,1,10),SUM(yingshou_money) FROM orders WHERE SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' GROUP BY SUBSTR(dd_time,1,10)";
		}else{
			sql="SELECT SUBSTR(dd_time,1,10),SUM(yingshou_money) FROM orders WHERE SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"' GROUP BY SUBSTR(dd_time,1,10)";

		}
		JDBCTools jt = new JDBCTools();
    	List<Tu> uList = new ArrayList<Tu>();
    	ArrayList<HashMap<String,String>> tList = jt.find(sql);
    	HashMap<String,Double> oneMap = new HashMap<String,Double>();
    	for(int i = 0;i<tList.size();i++){
    		Tu tu = new Tu();
    		tu.setDate(tList.get(i).get("SUBSTR(dd_time,1,10)"));
    		tu.setMoney(Double.parseDouble(tList.get(i).get("SUM(yingshou_money)")));
    		uList.add(tu);
    	}
    	return uList;
	}
	/**
	 * @param hyDateL
	 * @param hyDateR
	 * @return
	 * 按收款方式统计
	 */
	public HashMap<String,HashMap<String,Double>> selOrderByWay(String hyDateL,String hyDateR,String storeName){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
		 	DataDictionary.init();
			String[] array = DataDictionary.getDictItem("pay_way");
			//总收入
			double allMoney = 0.0;
			 HashMap<String,HashMap<String,Double>> tMap = new HashMap<String,HashMap<String,Double>>();
		if(storeName.equals("admin")){
			String sql = "SELECT sell_store FROM store_sign";
			ArrayList<HashMap<String,String>> aList = jt.find(sql);
			
			 for(int i = 0;i<aList.size();i++){
				 HashMap<String,Double> hMap = new HashMap<String,Double>();
				 for(String payWay :array){
					String	sql1 = "SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE pay_way1 ='"+payWay+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store='"+aList.get(i).get("sell_store")+"'";
					String	sql2 = "SELECT SUM(pay_way2_shishou_money) FROM orders WHERE pay_way2 ='"+payWay+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store='"+aList.get(i).get("sell_store")+"'";
					ArrayList<HashMap<String,String>> pList1 = jt.find(sql1);
					ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
					Double payMoney1 = 0.0;
					Double payMoney2 = 0.0;
					if(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						payMoney1 = Double.parseDouble(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					if(pList2.get(0).get("SUM(pay_way2_shishou_money)")!=null){
						payMoney2 = Double.parseDouble(pList2.get(0).get("SUM(pay_way2_shishou_money)"));
					}
					Double payMoney = payMoney1+payMoney2;
					allMoney += payMoney;
					hMap.put(payWay, payMoney);
				 	} 
				 tMap.put(aList.get(i).get("sell_store"), hMap);
			 }
		}else{
			
				 HashMap<String,Double> hMap = new HashMap<String,Double>();
				 for(String payWay :array){
					String	sql1 = "SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE pay_way1 ='"+payWay+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store='"+storeName+"'";
					String	sql2 = "SELECT SUM(pay_way2_shishou_money) FROM orders WHERE pay_way2 ='"+payWay+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store='"+storeName+"'";
					ArrayList<HashMap<String,String>> pList1 = jt.find(sql1);
					ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
					Double payMoney1 = 0.0;
					Double payMoney2 = 0.0;
					if(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						payMoney1 = Double.parseDouble(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					if(pList2.get(0).get("SUM(pay_way2_shishou_money)")!=null){
						payMoney2 = Double.parseDouble(pList2.get(0).get("SUM(pay_way2_shishou_money)"));
					}
					Double payMoney = payMoney1+payMoney2;
					allMoney += payMoney;
					hMap.put(payWay, payMoney);
				 	} 
				 tMap.put(storeName, hMap);
		}
		  //hMap.put("总收入", allMoney);
		return tMap;
	}
	/**
	 * @param hyDateL
	 * @param hyDateR
	 * @return
	 * 查询实收和应收的钱
	 */
	public HashMap<String,Double> selorderByShishou(String hyDateL,String hyDateR,String storeName){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
		 	HashMap<String,Double> hMap = new HashMap<String,Double>();
		 	Double payMoney3 = 0.0;
		 	Double allMoney = 0.0;
			if(storeName.equals("admin")){
				ArrayList<HashMap<String,String>> aList = jt.find("SELECT SUM(yingshou_money) FROM orders WHERE SUBSTR(dd_time,1,10)<='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >='"+hyDateL+"'");
				
				if(aList.get(0).get("SUM(yingshou_money)")!=null){
					payMoney3 = Double.parseDouble(aList.get(0).get("SUM(yingshou_money)"));
				}
					ArrayList<HashMap<String,String>> aList1 = jt.find("SELECT sell_store FROM store_sign");
				for(HashMap<String,String> i :aList1){
			        String	sql1 ="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE sell_store ='"+i.get("sell_store")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					String	sql2 ="SELECT SUM(pay_way2_shishou_money) FROM orders WHERE sell_store ='"+i.get("sell_store")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					System.out.println(sql1+"sql1"+"----------");
					System.out.println(sql2+"sql2");
					
					ArrayList<HashMap<String,String>> pList1 = jt.find(sql1);
					ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
					Double payMoney1 = 0.0;
					Double payMoney2 = 0.0;
					if(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						payMoney1 = Double.parseDouble(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					if(pList2.get(0).get("SUM(pay_way2_shishou_money)")!=null){
						payMoney2 = Double.parseDouble(pList2.get(0).get("SUM(pay_way2_shishou_money)"));
					}
					Double payMoneyStore = payMoney1+payMoney2;
					allMoney +=payMoneyStore;	
					}	
			}else{
				ArrayList<HashMap<String,String>> aList = jt.find("SELECT SUM(yingshou_money) FROM orders WHERE SUBSTR(dd_time,1,10)<='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >='"+hyDateL+"' and sell_store = '"+storeName+"'");
				if(aList.get(0).get("SUM(yingshou_money)")!=null){
					payMoney3 = Double.parseDouble(aList.get(0).get("SUM(yingshou_money)"));
				}
			        String	sql1 ="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE sell_store ='"+storeName+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					String	sql2 ="SELECT SUM(pay_way2_shishou_money) FROM orders WHERE sell_store ='"+storeName+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					ArrayList<HashMap<String,String>> pList1 = jt.find(sql1);
					ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
					Double payMoney1 = 0.0;
					Double payMoney2 = 0.0;
					if(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						payMoney1 = Double.parseDouble(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					if(pList2.get(0).get("SUM(pay_way2_shishou_money)")!=null){
						payMoney2 = Double.parseDouble(pList2.get(0).get("SUM(pay_way2_shishou_money)"));
					}
					Double payMoneyStore = payMoney1+payMoney2;
					allMoney +=payMoneyStore;	
			}
			hMap.put("应收", payMoney3);
			hMap.put("实收", allMoney);
			return hMap;
	}
	/**
	 * @param hyDateL
	 * @param hyDateR
	 * @return
	 * 按大类查询
	 */
	public HashMap<String,Double> selOrderByFenGong(String hyDateL,String hyDateR,String storeName){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
		 	HashMap<String,Double> hMap = new HashMap<String,Double>();
			ArrayList<HashMap<String,String>> aList = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='1'");
			Double allMoney = 0.0;
			if(storeName.equals("admin")){
				for(HashMap<String,String> i :aList){
					String sql = "SELECT SUM(orderdetail.yingshou_money) FROM orderdetail,goods,orders WHERE goods.good_code = orderdetail.good_code and orders.pay_state = '已支付' AND orders.dd_code = orderdetail.dd_code AND goods.good_bkind = '"+i.get("NAME")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					ArrayList<HashMap<String,String>> pList = jt.find(sql);
					Double payMoneyByFenGong = 0.0;
					if(pList.get(0).get("SUM(orderdetail.yingshou_money)")!=null){
						payMoneyByFenGong = Double.parseDouble(pList.get(0).get("SUM(orderdetail.yingshou_money)"));
					}
					hMap.put(i.get("NAME"), payMoneyByFenGong);
					allMoney +=payMoneyByFenGong;
				}
				String sql1="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE pay_state='有尾款' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
				ArrayList<HashMap<String,String>> List = jt.find(sql1);
				Double dingjin=0.0;
				if(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
					dingjin = Double.parseDouble(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
				}
				Double Jifen = 0.0;
				String sql2="SELECT SUM(pay_jifen_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
				ArrayList<HashMap<String,String>> List2 = jt.find(sql2);
				if(List2.get(0).get("SUM(pay_jifen_money)")!=null){
					Jifen = Double.parseDouble(List2.get(0).get("SUM(pay_jifen_money)"));
				}
				hMap.put("总收入", allMoney);
				hMap.put("定金", dingjin);
				hMap.put("积分", Jifen);
			}else{
				for(HashMap<String,String> i :aList){
					String sql = "SELECT SUM(orderdetail.yingshou_money) FROM orderdetail,goods,orders WHERE goods.good_code = orderdetail.good_code and orders.pay_state = '已支付' AND orders.dd_code = orderdetail.dd_code AND goods.good_bkind = '"+i.get("NAME")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"'";
					ArrayList<HashMap<String,String>> pList = jt.find(sql);
					Double payMoneyByFenGong = 0.0;
					if(pList.get(0).get("SUM(orderdetail.yingshou_money)")!=null){
						payMoneyByFenGong = Double.parseDouble(pList.get(0).get("SUM(orderdetail.yingshou_money)"));
					}
					hMap.put(i.get("NAME"), payMoneyByFenGong);
					allMoney +=payMoneyByFenGong;
				}
				String sql1="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE pay_state='有尾款' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"'";
				ArrayList<HashMap<String,String>> List = jt.find(sql1);
				Double dingjin=0.0;
				if(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
					dingjin = Double.parseDouble(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
				}
				Double Jifen = 0.0;
				String sql2="SELECT SUM(pay_jifen_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"'";
				ArrayList<HashMap<String,String>> List2 = jt.find(sql2);
				if(List2.get(0).get("SUM(pay_jifen_money)")!=null){
					Jifen = Double.parseDouble(List2.get(0).get("SUM(pay_jifen_money)"));
				}
				hMap.put("总收入", allMoney);
				hMap.put("定金", dingjin);
				hMap.put("积分", Jifen);
			}
			
			return hMap;
	}
	
	/**
	 * @param hyDateL
	 * @param hyDateR
	 * @return
	 * 按店铺查询
	 */
	public HashMap<String,Double> selOrderByStore(String hyDateL,String hyDateR,String storeName){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
			ArrayList<HashMap<String,String>> aList1 = jt.find("SELECT sell_store FROM store_sign");
			HashMap<String,Double> hMap = new HashMap<String,Double>();
			if(storeName.equals("admin")){
				Double allMoney = 0.0;
				for(HashMap<String,String> i :aList1){
			        String	sql1 ="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE sell_store ='"+i.get("sell_store")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					String	sql2 ="SELECT SUM(pay_way2_shishou_money) FROM orders WHERE sell_store ='"+i.get("sell_store")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					ArrayList<HashMap<String,String>> pList1 = jt.find(sql1);
					ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
					Double payMoney1 = 0.0;
					Double payMoney2 = 0.0;
					if(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						payMoney1 = Double.parseDouble(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					if(pList2.get(0).get("SUM(pay_way2_shishou_money)")!=null){
						payMoney2 = Double.parseDouble(pList2.get(0).get("SUM(pay_way2_shishou_money)"));
					}
					Double payMoneyStore = payMoney1+payMoney2;
					hMap.put(i.get("sell_store"), payMoneyStore);
					allMoney +=payMoneyStore;
				  }
				hMap.put("总收入", allMoney);
			}else{
				Double allMoney = 0.0;
			        String	sql1 ="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE sell_store ='"+storeName+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					String	sql2 ="SELECT SUM(pay_way2_shishou_money) FROM orders WHERE sell_store ='"+storeName+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					ArrayList<HashMap<String,String>> pList1 = jt.find(sql1);
					ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
					Double payMoney1 = 0.0;
					Double payMoney2 = 0.0;
					if(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						payMoney1 = Double.parseDouble(pList1.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					if(pList2.get(0).get("SUM(pay_way2_shishou_money)")!=null){
						payMoney2 = Double.parseDouble(pList2.get(0).get("SUM(pay_way2_shishou_money)"));
					}
					Double payMoneyStore = payMoney1+payMoney2;
					hMap.put(storeName, payMoneyStore);
					allMoney +=payMoneyStore;
				hMap.put("总收入", allMoney);
			}
		return hMap;
	}
	public HashMap<String,Double> selOrderPeisonfeiByStore(String hyDateL,String hyDateR){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
		ArrayList<HashMap<String,String>> aList1 = jt.find("SELECT sell_store FROM store_sign");
		HashMap<String,Double> hMap = new HashMap<String,Double>();
		Double allMoney = 0.0;
	for(HashMap<String,String> i :aList1){
		String	sql2 ="SELECT SUM(peisongfei) FROM orders WHERE sell_store ='"+i.get("sell_store")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
		ArrayList<HashMap<String,String>> pList2 = jt.find(sql2);
		Double peisongfei = 0.0;
		if(pList2.get(0).get("SUM(peisongfei)")!=null){
			peisongfei = Double.parseDouble(pList2.get(0).get("SUM(peisongfei)"));
		}
		hMap.put(i.get("sell_store"), peisongfei);
		allMoney +=peisongfei;
	  }
	hMap.put("总配送费", allMoney);
	return hMap;
	}
	/**
	 * @param hyDateL 时间下限
	 * @param hyDateR	时间上限
	 * @return
	 * 按小类查询
	 */
	public HashMap<String,Double> selOrderByClass(String hyDateL,String hyDateR,String storeName){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
		 	HashMap<String,Double> hMap = new HashMap<String,Double>();
		 	Double allMoney = 0.0;
			ArrayList<HashMap<String,String>> aList2 = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='2'");	
			if(storeName.equals("admin")){
				for(HashMap<String,String> i :aList2){
					  String sql = "SELECT SUM(orderdetail.yingshou_money) FROM orderdetail,goods,orders WHERE goods.good_code = orderdetail.good_code and orders.pay_state = '已支付' AND orders.dd_code = orderdetail.dd_code AND goods.good_skind = '"+i.get("NAME")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
						ArrayList<HashMap<String,String>> pList = jt.find(sql);
						Double payMoneyByFenGong = 0.0;
						if(pList.get(0).get("SUM(orderdetail.yingshou_money)")!=null){
							payMoneyByFenGong = Double.parseDouble(pList.get(0).get("SUM(orderdetail.yingshou_money)"));
						}
						hMap.put(i.get("NAME"), payMoneyByFenGong);
						allMoney +=payMoneyByFenGong;
					} 
					String sql1="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE pay_state='有尾款' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					ArrayList<HashMap<String,String>> List = jt.find(sql1);
					Double dingjin=0.0;
					if(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						dingjin = Double.parseDouble(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					Double Jifen = 0.0;
					String sql2="SELECT SUM(pay_jifen_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'";
					ArrayList<HashMap<String,String>> List2 = jt.find(sql2);
					if(List2.get(0).get("SUM(pay_jifen_money)")!=null){
						Jifen = Double.parseDouble(List2.get(0).get("SUM(pay_jifen_money)"));
					}
					hMap.put("总收入", allMoney);
					hMap.put("定金", dingjin);
					hMap.put("积分", Jifen);
			}else{
				for(HashMap<String,String> i :aList2){
					  String sql = "SELECT SUM(orderdetail.yingshou_money) FROM orderdetail,goods,orders WHERE goods.good_code = orderdetail.good_code and orders.pay_state = '已支付' AND orders.dd_code = orderdetail.dd_code AND goods.good_skind = '"+i.get("NAME")+"' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store='"+storeName+"'";
						ArrayList<HashMap<String,String>> pList = jt.find(sql);
						Double payMoneyByFenGong = 0.0;
						if(pList.get(0).get("SUM(orderdetail.yingshou_money)")!=null){
							payMoneyByFenGong = Double.parseDouble(pList.get(0).get("SUM(orderdetail.yingshou_money)"));
						}
						hMap.put(i.get("NAME"), payMoneyByFenGong);
						allMoney +=payMoneyByFenGong;
					} 
					String sql1="SELECT SUM(pay_way1_shishou_money-zhaoling_money) FROM orders WHERE pay_state='有尾款' and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'  and sell_store='"+storeName+"'";
					ArrayList<HashMap<String,String>> List = jt.find(sql1);
					Double dingjin=0.0;
					if(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)")!=null){
						dingjin = Double.parseDouble(List.get(0).get("SUM(pay_way1_shishou_money-zhaoling_money)"));
					}
					Double Jifen = 0.0;
					String sql2="SELECT SUM(pay_jifen_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"'  and sell_store='"+storeName+"'";
					ArrayList<HashMap<String,String>> List2 = jt.find(sql2);
					if(List2.get(0).get("SUM(pay_jifen_money)")!=null){
						Jifen = Double.parseDouble(List2.get(0).get("SUM(pay_jifen_money)"));
					}
					hMap.put("总收入", allMoney);
					hMap.put("定金", dingjin);
					hMap.put("积分", Jifen);
			}
			return hMap;
	}
	/**
	 * @param hyDateL 时间下限
	 * @param hyDateR 时间上限
	 * @param skind	 小类
	 * @param goodName	名字
	 * @param bkind	大类
	 * @return
	 * 统计卖的商品
	 */
	public List<Goods> countGood(String hyDateL,String hyDateR,String skind,String goodName,String bkind){
		JDBCTools jt = new JDBCTools();
		if(hyDateR==null ||hyDateR ==""){
			hyDateR = gnt.time();
		}
		if(hyDateL==null ||hyDateL ==""){
			hyDateL = gnt.time();
		}
		//前缀
		String sql="SELECT goods.*,SUM(orderdetail.number),SUM(orderdetail.yingshou_money) FROM orders,orderdetail,goods " +
			"WHERE orders.dd_code = orderdetail.dd_code and orders.pay_state='已支付' AND orderdetail.good_code = goods.good_code ";
		
		//如果大类选择了 （不为空或者不是全部）
		if( bkind!=null && !bkind.equals("") && !bkind.equals("全部")){
			sql += " and goods.good_bkind='"+bkind+"'";
		}
		//如果小类选择了 （不为空或者不是全部）
		if( skind!=null && !skind.equals("") && !skind.equals("全部")){
			sql += " and goods.good_skind='"+skind+"'";
		}
		//如果选择了具体商品
		if(goodName!=null&& !goodName.equals("")){
			sql += " AND goods.good_name LIKE '%"+goodName+"%'";
		}
		//后缀
		String suffix =" and SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' GROUP BY orderdetail.good_code";
		sql += suffix;
		
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		GoodManager g = new GoodManager();
		List<Goods> gList = g.transformToGList(aList);
		return gList;
	}
	/**
	 * @param hyDateL
	 * @param hyDateR
	 * @return
	 * 查询反结账记录
	 */
	public List<CancelOrders> selcancelOrder(String hyDateL,String hyDateR){
		JDBCTools jt = new JDBCTools();
		String sql = "SELECT * FROM cancel_orders WHERE SUBSTR(o_time,1,10)>='"+hyDateL+"' AND SUBSTR(o_time,1,10)<='"+hyDateR+"'";
		
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		VipManager g = new VipManager();
		List<CancelOrders> gList = g.transformToGList2(aList);
		return gList;
	}
	
	/**
	 * @param code
	 * @return
	 * 反结账的商品
	 */
	public String selBuyGood(String code){
		String sql = "SELECT goods.good_name,orderdetail.number FROM goods,cancel_orders,orderdetail WHERE orderdetail.dd_code = cancel_orders.dd_code AND orderdetail.good_code = goods.good_code and cancel_orders.dd_code='"+code+"'";
		JDBCTools  jt = new JDBCTools();
		ArrayList<HashMap<String,String>> list = jt.find(sql);
		if(list != null && list.size() > 0){
			String goods = ""; 
			for(int i = 0; i < list.size(); i++){
				goods += list.get(i).get("good_name") +"*"+ list.get(i).get("number")+"+";
				
			}
			if(goods.length() > 2){
				goods = goods.substring(0,goods.length()-1);
			}
			
			return goods;
		}else{
			return "";
		}
	}
	/**
	 * @param hyDateL 最小时间
	 * @param hyDateR 最大时间	
	 * @return 结果的ArrayList<HashMap<String,String>>
	 * 用来查询每个销售客服现金收到的钱数
	 */
	public ArrayList<HashMap<String,String>> selCashBYEmployee(String hyDateL,String hyDateR){
		JDBCTools jt = new JDBCTools();
		String sql = "SELECT ss1.cashier,IFNULL(ss1.psum1,0),IFNULL(ss2.psum2,0) FROM " +
				"(SELECT cashier,pay_way1,SUM(pay_way1_shishou_money) psum1 FROM orders WHERE pay_way1 = '现金支付' AND SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >='"+hyDateL+"' GROUP BY cashier) ss1 " +
				"LEFT JOIN " +
				"(SELECT cashier,pay_way2,SUM(pay_way2_shishou_money) psum2 FROM orders WHERE pay_way2= '现金支付' AND SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >='"+hyDateL+"' GROUP BY cashier) ss2 " +
				"ON ss1.pay_way1 = ss2.pay_way2 AND ss1.cashier = ss2.cashier";
		ArrayList<HashMap<String,String>> sList = jt.find(sql);
		//应该写在serviceimpl
		for(int i = 0 ;i<sList.size();i++){
			String s1 = sList.get(i).get("IFNULL(ss1.psum1,0)");
			String s2 = sList.get(i).get("IFNULL(ss2.psum2,0)");
			String s = Float.parseFloat(s1)+Float.parseFloat(s2)+"";
			System.out.println(s+""+sList.get(i).get("cashier"));
		}
		return sList;
	}
	public static void main(String[] args){
		CountDao cd = new CountDao();
		System.out.println(cd.selCash("","2017-09-15","2017-09-15"));
	}
	public String selCash(String name,String hyDateL,String hyDateR){
		JDBCTools jt = new JDBCTools();
		String sql1 = "SELECT IFNULL(SUM(pay_way1_shishou_money),0)  FROM orders WHERE pay_way1 = '现金支付' AND SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >='"+hyDateL+"' AND cashier='"+name+"'";
		String sql2 = "SELECT IFNULL(SUM(pay_way2_shishou_money),0)  FROM orders WHERE pay_way2 = '现金支付' AND SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >='"+hyDateL+"' AND cashier='"+name+"'";
		ArrayList<HashMap<String,String>> aList1 = jt.find(sql1);
		ArrayList<HashMap<String,String>> aList2 = jt.find(sql2);
		String money1 = aList1.get(0).get("IFNULL(SUM(pay_way1_shishou_money),0)");
		String money2 = aList2.get(0).get("IFNULL(SUM(pay_way2_shishou_money),0)");
		double money = Double.parseDouble(money1)+Double.parseDouble(money2);
		String returnMoney = money+"";
		return returnMoney;
	}
}
