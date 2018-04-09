package com.hzyc.ccs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hzyc.ccs.dao.VipDao;
import com.hzyc.ccs.model.Orders;
import com.hzyc.ccs.model.Temp;
import com.hzyc.ccs.model.Vip;
import com.hzyc.ccs.model.VipRecord;
import com.hzyc.ccs.service.VipSer;
import com.hzyc.ccs.tools.Daoru;
import com.hzyc.ccs.tools.Fenye;
import com.hzyc.ccs.tools.GetNowTime;
import com.hzyc.ccs.tools.JDBCTools;

@Controller
@RequestMapping(value="vip")
public class VipCon {
	JDBCTools jt = new JDBCTools();
	@Autowired
	VipSer vipSer;
	VipDao vipDao = new VipDao();
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
	//在订单里查询所有满足条件的vip
	@RequestMapping("selVipFromOrders.action")
	public ModelAndView selVipFromOrders(HttpServletRequest request,Orders orders,Vip vip){
		ModelAndView mav = new ModelAndView();
		String nowPage = request.getParameter("nowPage");
		List<Vip> oList  = vipDao.selAllVip(vip);
		List<Vip> rList = new ArrayList<Vip>();
		Fenye fenye = fenye(oList.size(),nowPage);
		
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			rList.add(oList.get(i));
		}
		mav.addObject("vip", vip);
		mav.addObject("nowPage", fenye.getNowPage());
		mav.addObject("lastPage", fenye.getLastPage());
		mav.addObject("vList",rList);
		mav.setViewName("bottom.jsp");
		return mav;
			
	}
	//查询所有会员的消费金额
	@RequestMapping("selVipTotalExpense.action")
	public ModelAndView selVipTotalExpense(Vip vip){
		
		ModelAndView mav = new ModelAndView();
		String totalExpense = vipSer.selVipTotalExpense(vip);
		mav.addObject("totalExpense",totalExpense);
		mav.setViewName("bottom.jsp");
		return mav;
	}
	@RequestMapping("addvip.action")
	public ModelAndView addVip(Vip vip,VipRecord vipRecord) throws SQLException{
		ModelAndView mav = new ModelAndView();
		boolean sucess = vipSer.insertVip(vip,vipRecord);
		if(sucess){
			mav.setViewName("../success.jsp");
		}else{
			mav.setViewName("../fail.jsp");
		}
		return mav;
	}
	@RequestMapping("selVip.action")
	public ModelAndView selVip(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		Temp t = vipDao.selMoney("全部");
		String tel = request.getParameter("tel");
		if(tel!=null && !tel.equals("")){
			tel = new String(request.getParameter("tel").getBytes("ISO-8859-1"),"utf-8"); 
		}
		if(tel==null||tel.equals("")){
			tel="全部";
		}
		String nowPage = request.getParameter("nowPage");
		List<Vip> rList = new ArrayList<Vip>();
		List<Vip> vList = vipSer.selVip(tel);
		Fenye fenye = fenye(vList.size(),nowPage);
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			rList.add(vList.get(i));
		}
		mav.addObject("vList",rList);
		mav.addObject("tel",tel);
		mav.addObject("t",t);
		mav.addObject("nowPage",fenye.getNowPage());
		mav.addObject("lastPage",fenye.getLastPage());
		mav.setViewName("sel_vip_table.jsp");
		return mav;
	}
	/**
	 * @return
	 * 查询当日新办会员
	 */
	@RequestMapping("selVipByToday.action")
	public ModelAndView selVipByToday(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String nowPage = request.getParameter("nowPage");
		List<Vip> rList = new ArrayList<Vip>();
		List<Vip> vList = vipSer.selVipByToday();
		Fenye fenye = fenye(vList.size(),nowPage);
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			rList.add(vList.get(i));
		}
		GetNowTime gnt = new GetNowTime();
		Temp t = vipDao.selMoney(gnt.time());
		mav.addObject("vList",rList);
		mav.addObject("t",t);
		mav.addObject("nowPage",fenye.getNowPage());
		mav.addObject("lastPage",fenye.getLastPage());
		mav.setViewName("sel_vip_table_today.jsp");
		return mav;
	}
	
	@RequestMapping("selVipByXuFei.action")
	public ModelAndView selVipByXuFei(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String nowPage = request.getParameter("nowPage");
		List<Vip> rList = new ArrayList<Vip>();
		List<Vip> vList = vipSer.selVipByXuFei();
		Fenye fenye = fenye(vList.size(),nowPage);
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			rList.add(vList.get(i));
		}
		Temp t = vipDao.selMoney("续费");
		mav.addObject("vList",rList);
		mav.addObject("t",t);
		mav.addObject("nowPage",fenye.getNowPage());
		mav.addObject("lastPage",fenye.getLastPage());
		mav.setViewName("sel_vip_table_xufei.jsp");
		return mav;
	}
	
	@RequestMapping("selVipLog.action")
	public ModelAndView selVipLog(HttpServletRequest request,String code) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		List<VipRecord> vrList = vipDao.selVipDetail(code);
		String sql = "select hy_name from vip where hy_code = '"+code+"'";
		//用来查询会员买的商品记录
		String sql1 = "SELECT goods.good_name,orderdetail.number,orderdetail.yingshou_money,vip_record.operate_date FROM goods,vip,orders,orderdetail,vip_record WHERE goods.good_code=orderdetail.good_code AND orderdetail.dd_code = orders.dd_code AND orders.vip_code = vip.hy_code AND vip.hy_code = vip_record.code AND orders.vip_code='"+code+"' AND orders.dd_time=SUBSTR(vip_record.operate_date,1,16)";
		//ArrayList<HashMap<String,String>> vList = jt.find(sql1);
		
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		String name = "";
		if(aList!=null &&aList.size()>0){
			name = aList.get(0).get("hy_name");
		}
		mav.addObject("vrList",vrList);
		mav.addObject("name",name);
		//mav.addObject("vList",vList);
		mav.setViewName("sel_vip_detail.jsp");
		return mav;
	}
	@RequestMapping("selGood.action")
	public ModelAndView selGood(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String code = request.getParameter("code");
		String date = request.getParameter("date");
		String data = vipSer.selVipBuyGood(code,date);
		mav.addObject("data",data);
		mav.setViewName("vip_good.jsp");
		return mav;
	}
	/**
	 * 查询会员的详情
	 * @param request
	 * @param code 会员的代码
	 * @return
	 */
	@RequestMapping("selVipDetail.action")
	public ModelAndView selVipDetail(HttpServletRequest request,String code){
		ModelAndView mav = new ModelAndView();
		Vip v = vipSer.selOneVip(code);
		mav.addObject("v",v);
		mav.setViewName("vip_detail.jsp");
		return mav;
	}
	
	@RequestMapping("uploadftp.action")
    public ModelAndView uploadcustomer(HttpServletRequest request,MultipartFile file) throws IOException, BiffException{
		InputStream input = file.getInputStream();
		ModelAndView mav = new ModelAndView();
		try{
			Daoru d = new Daoru(input);
			System.out.println(input);
			//java.io.FileInputStream@c0ea67
			//java.io.FileInputStream@1e04e00
			//java.io.ByteArrayInputStream@1c7c7c3
			List<Vip> vList = d.readExcel();
			System.out.println(vList.size());
			boolean sucess = vipSer.insertAllVip(vList);
			if(sucess){
				mav.setViewName("../success.jsp");
			}else{
				mav.setViewName("../fail.jsp");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input!=null){
				input.close();
				return mav;
			}
		}
		return mav;
	}
	@RequestMapping("delVip.action")
	public String delVip(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String code = request.getParameter("code");
		int sucess = vipSer.delVip(code);
		if(sucess!=0){
			response.setContentType("text/html; charset=UTF-8"); //转码
		    PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
		    out.println("alert('删除会员成功');");
		    out.println("</script>");
		}else{
			response.setContentType("text/html; charset=UTF-8"); //转码
		    PrintWriter out = response.getWriter();
		    out.flush();
		    out.println("<script>");
		    out.println("alert('删除会员失败');");
		    out.println("</script>");
		}
		return "selVip.action";
	}
	
	@RequestMapping("selVipRecord.action")
	public ModelAndView selVipRecord(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String code = request.getParameter("code");
		System.out.println(hyDateR);
		List<VipRecord> vrList = vipDao.selVipDetailByDate(code,hyDateL,hyDateR);
		String sql = "select hy_name from vip where hy_code = '"+code+"'";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		String name = "";
		if(aList!=null){
			name = aList.get(0).get("hy_name");
		}
		if(vrList.size()==0){
			return selVipLog(request,code);
		}else{
			mav.addObject("vrList",vrList);
			mav.addObject("name",name);
			mav.setViewName("sel_vip_detail.jsp");
			return mav;
		}
	}
}
