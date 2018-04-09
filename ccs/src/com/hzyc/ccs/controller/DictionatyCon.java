package com.hzyc.ccs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hzyc.ccs.model.Dictionary;
import com.hzyc.ccs.system.DataDictionary;
import com.hzyc.ccs.tools.InsertSyn;
import com.hzyc.ccs.tools.JDBCTools;

@Controller
@RequestMapping(value="dataDictionary")
public class DictionatyCon {
	
	//加载字典项kind
	@RequestMapping("/loadDataDicKind.action")
	public void loadDataDicKind(HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		JDBCTools jt = new JDBCTools();
		DataDictionary.init();
		//查找所有key
		HashMap<String,HashMap<String,String>> oneMap = DataDictionary.getDict();
		System.out.println(DataDictionary.getDict());
		Gson g = new Gson();
		Iterator<String> set = oneMap.keySet().iterator();
		ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		while(set.hasNext()){
			String code = set.next();
			HashMap<String,String> name= jt.find("select dic_type,dic_type_name from dictionary_depict where dic_type='"+code+"'").get(0);
			data.add(name);
		}
		System.out.println(g.toJson(data));
		PrintWriter writer = response.getWriter();
		writer.print(g.toJson(data));
		writer.flush();
		writer.close();
	}
	
	//增加字典项
	@RequestMapping("addDic.action")
	public void addDic(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JDBCTools jt = new JDBCTools();
		String zdxtype = request.getParameter("zdxtype");
		String zdxdm = request.getParameter("zdxdm");
		String zdxmc = request.getParameter("zdxmc");
		zdxmc = new String(request.getParameter("zdxmc").getBytes("ISO-8859-1"),"utf-8"); 
		String sql = "INSERT INTO dictionary(dict_type,dict_code,dict_name) VALUES('"+zdxtype+"','"+zdxdm+"','"+zdxmc+"')";
		int s = jt.update(sql);
		InsertSyn.insertSql(sql);
		if(s!=0){
			PrintWriter out = response.getWriter();
			out.print("<script>window.close();</script>");
		}
	}
	//查询最大的字典项
	@RequestMapping("selMaxDic.action")
	public ModelAndView selMaxDic(String dict_type){
		ModelAndView mav = new ModelAndView();
		JDBCTools jt = new JDBCTools();
		String sql = "SELECT dict_type,dic_type_name,MAX(dict_code+1) FROM dictionary,dictionary_depict WHERE dict_type='"+dict_type+"' GROUP BY dict_type";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		Dictionary d = new Dictionary();
		d.setDictType(aList.get(0).get("dict_type"));
		d.setDictName(aList.get(0).get("dic_type_name"));
		d.setDictCode(aList.get(0).get("MAX(dict_code+1)"));
		mav.addObject("d",d);
		mav.setViewName("add_dict.jsp");
		return mav;
	}
	//删除字典项
	@RequestMapping("remDic.action")
	public ModelAndView remDic(HttpServletRequest request){
		JDBCTools jt = new JDBCTools();
		ModelAndView mav = new ModelAndView();
		String zdxdm = request.getParameter("zdxdm");
		String sql = "delete from dictionary where dict_code='"+zdxdm+"'";
		int s = jt.update(sql);
		InsertSyn.insertSql(sql);
		if(s!=0){
			mav.setViewName("../success.jsp");
		}
		return mav;
	}
	//更改字典项
	@RequestMapping("updDic.action")
	public ModelAndView updDic(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		JDBCTools jt = new JDBCTools();
		String zdxtype = request.getParameter("zdxtype");
		String zdxdm = request.getParameter("zdxdm");
		String zdxmc = request.getParameter("zdxmc");
		zdxmc = new String(request.getParameter("zdxmc").getBytes("ISO-8859-1"),"utf-8"); 
		String sql = "delete from dictionary where dict_code='"+zdxdm+"'";
		String sql1 = "INSERT INTO dictionary(dict_type,dict_code,dict_name) VALUES('"+zdxtype+"','"+zdxdm+"','"+zdxmc+"')";
		jt.update(sql);
		InsertSyn.insertSql(sql);
		int s = jt.update(sql1);
		InsertSyn.insertSql(sql1);
		if(s!=0){
			mav.setViewName("../success.jsp");
		}
		return mav;
		
	}
}
