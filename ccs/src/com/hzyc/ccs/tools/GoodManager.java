package com.hzyc.ccs.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzyc.ccs.model.GoodKind;
import com.hzyc.ccs.model.Goods;


public class GoodManager {
	
	
	//将商品的ArrayList<HashMap<String,String> 转换为List<Goods>
	public List<Goods> transformToGList(ArrayList<HashMap<String,String>> rsList){
		List<Goods> gList = new ArrayList<Goods>();
		try {
			for(int i = 0;i < rsList.size();i++){
				Map<String,String> rsMap = rsList.get(i);
				Goods temp = new Goods();
				temp.setGoodCode((rsMap.get("good_code")));
				temp.setGoodName(rsMap.get("good_name"));
				temp.setGoodPrice(rsMap.get("good_price"));
				temp.setGoodSize(rsMap.get("good_size"));
				temp.setGoodBkind(rsMap.get("good_bkind"));
				temp.setGoodSkind(rsMap.get("good_skind"));
				temp.setGoodBz(rsMap.get("good_bz"));
				if(rsMap.get("SUM(orderdetail.number)")!=null){
					temp.setNumber(rsMap.get("SUM(orderdetail.number)"));
				}
				if(rsMap.get("SUM(orderdetail.yingshou_money)")!=null){
					temp.setTotalPrice(rsMap.get("SUM(orderdetail.yingshou_money)"));
				}
				gList.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gList;
	}
	//将商品的ArrayList<HashMap<String,String> 转换为List<GoodKind>
	public List<GoodKind> transformToGList1(ArrayList<HashMap<String,String>> rsList){
		List<GoodKind> gList = new ArrayList<GoodKind>();
		JDBCTools jt = new JDBCTools();
		try {
			for(int i = 0;i < rsList.size();i++){
				Map<String,String> rsMap = rsList.get(i);
				String sql = "select * from good_kind where id= '"+rsMap.get("pid")+"'";
				ArrayList<HashMap<String,String>> aList = jt.find(sql);
				GoodKind temp = new GoodKind();
				temp.setId(rsMap.get("id"));
				temp.setLevel(rsMap.get("level"));
				temp.setPid(aList.get(0).get("name"));
				temp.setBz(rsMap.get("bz"));
				temp.setName(rsMap.get("name"));
				gList.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gList;
	}
}
