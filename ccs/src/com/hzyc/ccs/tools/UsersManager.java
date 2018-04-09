package com.hzyc.ccs.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzyc.ccs.model.Users;

public class UsersManager {
	//将用户的ArrayList<HashMap<String,String> 转换为List<Users>
	public List<Users> transformToGList(ArrayList<HashMap<String,String>> rsList){
		List<Users> gList = new ArrayList<Users>();
		try {
			for(int i = 0;i < rsList.size();i++){
				Map<String,String> rsMap = rsList.get(i);
				Users temp = new Users();
				if(rsMap.get("userid") !=null){
					temp.setUserid(Integer.parseInt(rsMap.get("userid")));
				}
				if(rsMap.get("uname")!=null){
					temp.setUname(rsMap.get("uname"));
				}
				if(rsMap.get("userpw")!=null){
					temp.setUserpw(rsMap.get("userpw"));
				}
				if(rsMap.get("store_name")!=null){
					temp.setStoreName(rsMap.get("store_name"));
				}
				if(rsMap.get("true_name")!=null){
					temp.setTrueName(rsMap.get("true_name"));
				}
				if(rsMap.get("img_name")!=null){
				temp.setImgName(rsMap.get("img_name"));
				}
				if(rsMap.get("img_name1")!=null){
					temp.setImgName1(rsMap.get("img_name1"));
				}
				if(rsMap.get("rname")!=null){
					temp.setPermission(rsMap.get("rname"));
				}
				gList.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gList;
	}
}
