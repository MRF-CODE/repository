package com.hzyc.ccs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.hzyc.ccs.dao.CountDao;
import com.hzyc.ccs.model.GoodKind;
import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.service.GoodSer;
import com.hzyc.ccs.tools.Fenye;
import com.hzyc.ccs.tools.JDBCTools;

@Controller
public class GoodCon {
	
	@Autowired
	GoodSer goodSer;
	CountDao cd = new CountDao();
	JDBCTools jt = new JDBCTools();
	//分页方法
	public Fenye fenye(int Allrow,String nowPage){
		Fenye fenye = new Fenye();
		int pageSize = 5;
		Integer lastPage = Allrow % pageSize == 0 ? Allrow  / pageSize : Allrow 
				/ pageSize + 1;
		
		if(nowPage == null || nowPage.equals("") || nowPage.equals("null")){
			nowPage = "1";
		}
		
		if(Integer.parseInt(nowPage) > lastPage){
			nowPage = lastPage.toString();
		}
		if(Integer.parseInt(nowPage)  < 1){
			nowPage = "1";
		}
		int now = Integer.valueOf(nowPage) - 1;
		int maxPage = now*pageSize + pageSize;
		if(now*pageSize + pageSize > Allrow){
			maxPage = Allrow;
		}
		fenye.setStartPage(now*pageSize);
		fenye.setLastPage(lastPage);
		System.out.println(nowPage);
		fenye.setNowPage(Integer.parseInt(nowPage));
		fenye.setMaxPage(maxPage);
		return fenye;
	}
	//添加大类
	@RequestMapping("addbkind.action")
	public ModelAndView addbkind(GoodKind goodKind){
		ModelAndView mav = new ModelAndView();
		//大类的级别为1
		goodKind.setLevel("1");
		int success = goodSer.insertGoodKind(goodKind);
		if(success !=0){
			mav.setViewName("success.jsp");
		}
		return mav;
	}
	
	//添加小类
	@RequestMapping("addskind.action")
	public ModelAndView addbsind(String bkind,GoodKind goodKind){
		String sql = "select * from good_kind where name='"+bkind+"'";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		String pid = aList.get(0).get("id");
		//查出大类名字对应编码，将父类编码以及级别2存起来
		goodKind.setPid(pid);
		goodKind.setLevel("2");
		ModelAndView mav = new ModelAndView();
		int success = goodSer.insertGoodKind(goodKind);
		if(success !=0){
			mav.setViewName("success.jsp");
		}
		return mav;
	}
	
	//加载大类
	@RequestMapping("loadBkind.action")
	public void laodBkind(HttpServletResponse response) throws IOException{
		 	JDBCTools jt = new JDBCTools();
		 	//使用了ajax技术，查询所有大类
			ArrayList<HashMap<String,String>> aList = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='1'");	
			Gson g = new Gson();
			//转换数据类型
			String retext = g.toJson(aList);
			//开始返回
			response.setContentType("text/html");
			//设置返回编码方式
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
		
			out.print(retext);
			out.flush();
			out.close();
	}
	//二级联动加载小类
	@RequestMapping("loadSkind.action")
	public void loadSkind(HttpServletResponse response,HttpServletRequest request) throws IOException {
		JDBCTools jt = new JDBCTools();
		String code = request.getParameter("code");
		//查询大类对应的id
		String sql = "select id from good_kind where name='"+code+"'";
		ArrayList<HashMap<String,String>> list = jt.find(sql);
		String id = list.get(0).get("id");
		//查询此大类下的所有的小类
		ArrayList<HashMap<String,String>> aList = jt.find("SELECT NAME FROM good_kind WHERE LEVEL='2' and pid='"+id+"'");	
		Gson g = new Gson();
		String retext = g.toJson(aList);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(retext);
		out.flush();
		out.close();
	}
	//添加商品
	@RequestMapping("addgood.action")
	public ModelAndView addgood(Goods goods){
		ModelAndView mav = new ModelAndView();
		
		int success = goodSer.insertGood(goods);
		if(success!=0){
			mav.setViewName("success.jsp");
		}
		return mav;
	}
	//查询小类
	@RequestMapping("selSkind.action")
	public ModelAndView selSkind(){
		ModelAndView mav = new ModelAndView();
		List<GoodKind> gkList = goodSer.selSkind();
		mav.addObject("gkList",gkList);
		mav.setViewName("addGood/sel_skind_table.jsp");
		return mav;
	}
	//查询商品
	@RequestMapping("selGood.action")
	public ModelAndView selGood(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		String nowPage = request.getParameter("nowPage");
		String bkind = request.getParameter("bkind");
		String skind = request.getParameter("skind");
		String goodName = request.getParameter("goodName");
		if(bkind !=null && bkind !=""){
			if(bkind.equals("全部")){
			}else{
				bkind = new String(request.getParameter("bkind").getBytes("ISO-8859-1"),"utf-8"); 
			}
		}
		if(skind !=null && skind !=""){
			if(skind.equals("全部")){
			}else{
				skind = new String(request.getParameter("skind").getBytes("ISO-8859-1"),"utf-8"); 
			}
		}
		if(goodName !=null && goodName !=""){
			goodName = new String(request.getParameter("goodName").getBytes("ISO-8859-1"),"utf-8");
			
		}
		List<Goods> rList = new ArrayList<Goods>();
		List<Goods> gList = goodSer.selGood(bkind,skind,goodName);
		Fenye fenye = fenye(gList.size(),nowPage);
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			rList.add(gList.get(i));
		}
		mav.addObject("gList",rList);
		mav.addObject("nowPage",fenye.getNowPage());
		mav.addObject("lastPage",fenye.getLastPage());
		mav.addObject("skind",skind);
		mav.addObject("bkind",bkind);
		mav.addObject("goodName",goodName);
		mav.setViewName("addGood/sel_good_table.jsp");
		return mav;
	}
	//统计商品的销售数量
	@RequestMapping("countGood.action")
	public ModelAndView countGood(HttpServletRequest request) throws UnsupportedEncodingException{
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String skind = request.getParameter("skind");
		String bkind = request.getParameter("bkind");
		String goodName = request.getParameter("goodName");
		skind = new String(request.getParameter("skind").getBytes("ISO-8859-1"),"utf-8"); 
		bkind = new String(request.getParameter("bkind").getBytes("ISO-8859-1"),"utf-8"); 
		goodName = new String(request.getParameter("goodName").getBytes("ISO-8859-1"),"utf-8"); 
		ModelAndView mav = new ModelAndView();
		List<Goods> gList = cd.countGood(hyDateL,hyDateR,skind,goodName,bkind);
		Double totalPrice = 0.0;
		Integer totalNumber = 0;
		for(int i = 0 ;i<gList.size();i++){
			totalPrice += Double.parseDouble(gList.get(i).getTotalPrice());
			totalNumber += Integer.parseInt(gList.get(i).getNumber());
		}
		
		mav.addObject("gList",gList);
		mav.addObject("hyDateL",hyDateL);
		mav.addObject("hyDateR",hyDateR);
		mav.addObject("skind",skind);
		mav.addObject("bkind",bkind);
		mav.addObject("goodName",goodName);
		mav.addObject("totalPrice",totalPrice);
		mav.addObject("totalNumber",totalNumber);
		
		mav.setViewName("addGood/count_good_table.jsp");
		return mav;
	}
	//在修改之前要查询单个商品的信息
	@RequestMapping("selOneGood.action")
	public ModelAndView selOneGood(String goodCode){
		ModelAndView mav = new ModelAndView();
		Goods g = goodSer.selOneGood(goodCode);
		mav.addObject("g",g);
		mav.setViewName("addGood/update_good.jsp");
		return mav;
	}
	//更改商品
	@RequestMapping("updateGood.action")
	public void updateGood(HttpServletResponse response,Goods g) throws IOException{
	
		int s = goodSer.updateGood(g);
		if(s !=0){
			PrintWriter out = response.getWriter();
			out.print("<script>window.close();</script>");
		}
		
	}
	
	//删除商品
	@RequestMapping("deleteGood.action")
	public void deleteGood(String goodCode,HttpServletResponse response) throws IOException{
		int sucess = goodSer.deleteGood(goodCode);
		if(sucess!=0){
			response.setContentType("text/html; charset=UTF-8"); //转码
		    PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
		    out.println("alert('删除商品成功');");
		    out.println("</script>");
		}else{
			response.setContentType("text/html; charset=UTF-8"); //转码
		    PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
		    out.println("alert('删除商品失败');");
		    out.println("</script>");
		}
	}
	//删除小类
	@RequestMapping("deleteGoodKind.action")
	public ModelAndView deleteGoodKind(String id,HttpServletResponse response) throws IOException{
		ModelAndView mav = new ModelAndView();
		int sucess = goodSer.deleteSkind(id);
		if(sucess!=0){
			response.setContentType("text/html; charset=UTF-8"); //转码
		    PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
		    out.println("alert('删除小类成功');");
		    out.println("</script>");
		}else{
			response.setContentType("text/html; charset=UTF-8"); //转码
		    PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
		    out.println("alert('删除小类失败');");
		    out.println("</script>");
		}
		mav.setViewName("addGood/add_skind.jsp");
		return mav;
	}
}
