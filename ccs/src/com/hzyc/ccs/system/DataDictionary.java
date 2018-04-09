package com.hzyc.ccs.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hzyc.ccs.model.Dictionary;
import com.hzyc.ccs.tools.JDBCTools;


/**
 * 数据字典缓存类
 *
 *@author 邵帅
 */
public class DataDictionary  {
	//装载数据字典结构
	public static HashMap<String,HashMap<String,String>> totalMap = new HashMap<String,HashMap<String,String>>();
	//装载反向数据字典
	public static HashMap<String,HashMap<String,String>>  reverseMap = new  HashMap<String,HashMap<String,String>>();
	/**
	 * 数据字典初始化
	 * 
	 * @author 邵帅
	 */
	public static  void init(){
		try {
			System.out.println("数据字典开始缓存...");
			JDBCTools jt = new JDBCTools();
			//开始查询
			ArrayList<HashMap<String,String>> alhs = jt.find("select * from dictionary group by dict_type");
			for(int i = 0;i<alhs.size();i++){
				String type = alhs.get(i).get("dict_type");
				ArrayList<HashMap<String,String>> alhs2 = jt.find("select * from dictionary where dict_type ='"+type+"'");
				//实例化新map装载详细字典项目
				HashMap<String,String> oneMap = new HashMap<String, String>();
				HashMap<String,String> onereverseMap = new HashMap<String, String>();
				for(int j = 0;j<alhs2.size() ;j++){
					String code =  alhs2.get(j).get("dict_code");
					String name = alhs2.get(j).get("dict_name");
					Dictionary dict = new Dictionary();
					dict.setDictCode(code);
					dict.setDictName(name);
					oneMap.put(dict.getDictCode(),dict.getDictName());
					onereverseMap.put(dict.getDictName(), dict.getDictCode());
				}
				//装载类型，以及类型下的全部字典项目
				totalMap.put(type,oneMap);
				//反向字典，根据文字找编码
				reverseMap.put(type, onereverseMap);
			}
			if(totalMap.size() > 0 && reverseMap.size() > 0){
				System.out.println("数据字典缓存成功：size为"+totalMap.size());
				System.out.println("数据反向字典缓存成功：size为"+reverseMap.size());
			}
		} catch (Exception e) {
			System.out.println("数据字典初始化异常......");
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public static HashMap<String,HashMap<String,String>> getDict(){
		return totalMap;
	}
	/**
	 * 获取里层Map
	 * @param key 字典类别(例如大区)
	 * @return
	 */
	public static HashMap<String,String> getNexineMap(String key){
		return totalMap.get(key);
	}
	/** 
	 * 根据key(type)找出全部字典项目
	 * 
	 * @author 邵帅
	 * @param String 字典类别
	 * @return String[] 返回类型字典项数组
	 */
	public static String[] getDictItem(String type){
		
		HashMap<String,String> h =  totalMap.get(type);
		String[] array = new String[h.size()];
		int i = 0;
		for(Map.Entry<String,String> entry:h.entrySet()){
			array[i] = entry.getValue();
			i++;
		}
		return array;
	}
	
	public static String getString(String type,String key){
		HashMap<String,String> h = totalMap.get(type);
		if(h!= null && h.size() > 0){
			String name = h.get(key);
			return name;
		}else 
			return "暂无";
	}
	
	public static String getKeyName(String key){
		JDBCTools jt = new JDBCTools();
		String name = "";
		try {
			name = jt.find("select dic_type_name from dictionary_depict where dic_type='"+key+"'").get(0).get("dic_type_name");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(name == null){
				return "暂无";
			}
		}
		return name;
	}
	
	/**
	 * 根据中文反向找到字典编码
	 * 
	 * @author 邵帅
	 * @param String 字典类型(字典类型下的Map的key不会重复)
	 * @param String 要反向查找的字符
	 * @return  String 字典编码
	 */
	public static String getReverseCode(String type,String key){
		HashMap<String,String> h =  reverseMap.get(type);
		if(h!= null && h.size() > 0){
			String code = h.get(key);
			return code;
		}else 
			return null;
	}
	//测试
	public static void main(String[] args) {
		init();
		//System.out.println(getReverseCode("pay_way", "现金支付"));
		DataDictionary d = new DataDictionary();
		System.out.println(DataDictionary.getDict());
	}
}
