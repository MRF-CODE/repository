package com.hzyc.ccs.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreNumToName {
	JDBCTools jt = new JDBCTools();
	public String[] storeNumToName(String[] storeNum){
		String[] storeName = new String[storeNum.length];
		for(int i =0;i<storeNum.length;i++){
			String sql = "select * from store_sign where id='"+storeNum[i]+"'";
			ArrayList<HashMap<String,String>> aList = jt.find(sql);
			storeName[i] = aList.get(0).get("sell_store");
		}
		return storeName;
	}
}
