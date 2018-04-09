package com.hzyc.ccs.system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.hzyc.ccs.tools.JDBCTools;

public class GetImage {

	public void getImage(String path) throws IllegalStateException, IOException{
		//查询所有用户，取出用户的id，用用户的id去把所有的数据库中已有的图片放到服务器上
		JDBCTools jt = new JDBCTools();
		String sql = "select * from users";
		ArrayList<HashMap<String,String>> uList = jt.find(sql);
		for(int i = 0;i<uList.size();i++){
			String fileName = uList.get(i).get("img_name");
			String finalPath = path +"image/"+fileName;
			File target = new File(finalPath);
			if(target.exists()){
				//target.delete();
			} else {
				jt.readDB2Image(finalPath, Integer.parseInt(uList.get(i).get("userid")));
			}
		}
	}
	public void getImage1(String path) throws IllegalStateException, IOException{
		//查询所有用户，取出用户的id，用用户的id去把所有的数据库中已有的图片放到服务器上
		JDBCTools jt = new JDBCTools();
		String sql = "select * from users";
		ArrayList<HashMap<String,String>> uList = jt.find(sql);
		for(int i = 0;i<uList.size();i++){
			String fileName = uList.get(i).get("img_name1");
			String finalPath = path +"image/"+fileName;
			File target = new File(finalPath);
			if(target.exists()){
				//target.delete();
			} else {
				jt.readDB2Image1(finalPath, Integer.parseInt(uList.get(i).get("userid")));
			}
		}
	}
}
