package com.hzyc.ccs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.hzyc.ccs.dao.CountDao;
import com.hzyc.ccs.dao.VipDao;
import com.hzyc.ccs.model.CancelOrders;
import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.model.Tu;
import com.hzyc.ccs.service.OrderSer;
import com.hzyc.ccs.tools.BingTu;
import com.hzyc.ccs.tools.Fenye;
import com.hzyc.ccs.tools.JDBCTools;
import com.hzyc.ccs.tools.Yeartu;
import com.hzyc.ccs.tools.Yuetu;
import com.hzyc.ccs.tools.Zhutu;
@Controller
public class OrderCon {
	
	@Autowired
	OrderSer orderSer;
	
	CountDao cd = new CountDao();
/*	HttpServletRequest req;
	String storeName = (String)req.getSession().getAttribute("storeName");*/
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
	@RequestMapping("/loadPayState.action")
	public void loadDataDicKind(HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		JDBCTools jt = new JDBCTools();
		Gson g = new Gson();
		String sql = "SELECT dict_name FROM dictionary WHERE dict_type='pay_way'";
		ArrayList<HashMap<String,String>> data = jt.find(sql);
		System.out.println(g.toJson(data));
		PrintWriter writer = response.getWriter();
		writer.print(g.toJson(data));
		writer.flush();
		writer.close();
	}
	/**
	 * 用来查询反结账的方法
	 * @param request
	 * @return
	 */
	@RequestMapping("selcancelOrder.action")
	public ModelAndView selcancelOrder(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String nowPage = request.getParameter("nowPage");
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		List<CancelOrders> coList = orderSer.selcancelOrder(hyDateL, hyDateR);
		
		List<CancelOrders> cList = new ArrayList<CancelOrders>();
		Fenye fenye = fenye(coList.size(),nowPage);
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			cList.add(coList.get(i));
		}
		mav.addObject("nowPage",fenye.getNowPage());
		mav.addObject("lastPage",fenye.getLastPage());
		mav.addObject("hyDateL",hyDateL);
		mav.addObject("hyDateR",hyDateR);
		mav.addObject("cList",cList);
		mav.setViewName("cancelOrder/cancel_order_table.jsp");
		return mav;
	}
	//按四种方式统计orders
	@RequestMapping("countAll.action")
	public ModelAndView countAll(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		//按小类查询
		HashMap<String,Double> cMap = cd.selOrderByClass(hyDateL, hyDateR,storeName);
		mav.addObject("cMap",cMap);
		//按支付方式查询
		//HashMap<String,Double> sMap = cd.selOrderByWay(hyDateL, hyDateR);
		HashMap<String,HashMap<String,Double>> sMap = cd.selOrderByWay(hyDateL, hyDateR,storeName);
		mav.addObject("sMap",sMap);
		//按岗位查询
		HashMap<String,Double> fMap = cd.selOrderByFenGong(hyDateL, hyDateR,storeName);
		mav.addObject("fMap",fMap);
		//按商店查询
		HashMap<String,Double> oMap = cd.selOrderByStore(hyDateL, hyDateR,storeName);
		mav.addObject("oMap",oMap);
		//按商店查询配送费
		HashMap<String,Double> pMap = cd.selOrderPeisonfeiByStore(hyDateL, hyDateR);
		mav.addObject("pMap",pMap);
		//查询实收和应收
		HashMap<String,Double> aMap = cd.selorderByShishou(hyDateL, hyDateR,storeName);
		mav.addObject("aMap",aMap);
		VipDao vd = new VipDao();
		//查询所有会员的总充值金额
		HashMap<String,String> tMap = vd.selChongzhiBydate(hyDateL, hyDateR,storeName);
		mav.addObject("tMap",tMap);
		if(request.getSession().getAttribute("storeName").equals("admin")){
			mav.setViewName("table.jsp");
		}else{
			mav.setViewName("manager_table.jsp");
		}
		
		return mav;
	}
	@RequestMapping("countAll1.action")
	public ModelAndView countAll1(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		//按小类查询
		HashMap<String,Double> cMap = cd.selOrderByClass(hyDateL, hyDateR,storeName);
		mav.addObject("cMap",cMap);
		//按岗位查询
		HashMap<String,Double> fMap = cd.selOrderByFenGong(hyDateL, hyDateR,storeName);
		mav.addObject("fMap",fMap);
		//按商店查询
		HashMap<String,Double> oMap = cd.selOrderByStore(hyDateL, hyDateR,storeName);
		mav.addObject("oMap",oMap);
		//按商店查询配送费
		HashMap<String,Double> pMap = cd.selOrderPeisonfeiByStore(hyDateL, hyDateR);
		mav.addObject("pMap",pMap);
		mav.setViewName("kind_table.jsp");
		return mav;
	}
	@RequestMapping("countAll2.action")
	public ModelAndView countAll2(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		//按小类查询
		HashMap<String,Double> cMap = cd.selOrderByClass(hyDateL, hyDateR,storeName);
		mav.addObject("cMap",cMap);
		//按岗位查询
		HashMap<String,Double> fMap = cd.selOrderByFenGong(hyDateL, hyDateR,storeName);
		mav.addObject("fMap",fMap);
		//按商店查询
		HashMap<String,Double> oMap = cd.selOrderByStore(hyDateL, hyDateR,storeName);
		mav.addObject("oMap",oMap);
		//按商店查询配送费
		HashMap<String,Double> pMap = cd.selOrderPeisonfeiByStore(hyDateL, hyDateR);
		mav.addObject("pMap",pMap);
		mav.setViewName("store_table.jsp");
		return mav;
	}
	@RequestMapping("selOrderBySonghuo.action")
	public ModelAndView selOrderBySonghuo(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		JDBCTools jt = new JDBCTools();
		String sql = "";
		//按照日期查询送货员
		if(storeName.equals("admin")){
			sql = "SELECT songhuo_people,COUNT(songhuo_people) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' GROUP BY songhuo_people";
		}else{
			sql = "SELECT songhuo_people,COUNT(songhuo_people) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"' GROUP BY songhuo_people";
		}
		ArrayList<HashMap<String,String>> cList = jt.find(sql);
		mav.addObject("cList",cList);
		mav.setViewName("songhuo.jsp");
		return mav;
	}
	@RequestMapping("selOrderBySonghuo1.action")
	public ModelAndView selOrderBySonghuo1(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		JDBCTools jt = new JDBCTools();
		ArrayList<HashMap<String,String>> cList = new ArrayList<HashMap<String,String>>();
		if(storeName.equals("admin")){
			//按照日期查询送货员
			String sql = "SELECT songhuo_people,COUNT(songhuo_people) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' GROUP BY songhuo_people";
			cList = jt.find(sql);
		}else{
			//按照对应的店铺查询
			//按照日期查询送货员
			String sql = "SELECT songhuo_people,COUNT(songhuo_people) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store = '"+storeName+"' GROUP BY songhuo_people";
			cList = jt.find(sql);
		}
		mav.addObject("cList",cList);
		mav.setViewName("songhuo_table.jsp");
		return mav;
	}
	@RequestMapping("selOrderBySell.action")
	public ModelAndView selOrderBySell(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		JDBCTools jt = new JDBCTools();
		cd.selCashBYEmployee(hyDateL, hyDateR);
		ArrayList<HashMap<String,String>> cList = new ArrayList<HashMap<String,String>>();
		if(request.getSession().getAttribute("storeName").equals("admin")){
			//按照日期查询销售客服
			String sql = "SELECT cashier,COUNT(cashier),SUM(pay_way1_shishou_money+pay_way2_shishou_money-zhaoling_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' GROUP BY cashier";
			//String sql = "SELECT cashier,COUNT(cashier),SUM(pay_way1_shishou_money+pay_way2_shishou_money-zhaoling_money) FROM orders WHERE SUBSTR(dd_time,1,10) <='2017-10-12' AND SUBSTR(dd_time,1,10) >= '2017-10-12' GROUP BY cashier";
			cList = jt.find(sql);
		}else{
			//如果不是管理员登录，那么只查询他对应的店铺。
			//按照日期查询销售客服
			String sql = "SELECT cashier,COUNT(cashier),SUM(pay_way1_shishou_money+pay_way2_shishou_money-zhaoling_money) FROM orders where SUBSTR(dd_time,1,10) <='"+hyDateR+"' AND SUBSTR(dd_time,1,10) >= '"+hyDateL+"' and sell_store='"+storeName+"' GROUP BY cashier";
			//String sql = "SELECT cashier,COUNT(cashier),SUM(pay_way1_shishou_money+pay_way2_shishou_money-zhaoling_money) FROM orders WHERE SUBSTR(dd_time,1,10) <='2017-10-12' AND SUBSTR(dd_time,1,10) >= '2017-10-12' GROUP BY cashier";
			cList = jt.find(sql);
		}
		
		mav.addObject("cList",cList);
		mav.addObject("hyDateL",hyDateL);
		mav.addObject("hyDateR",hyDateR);
		mav.setViewName("seller.jsp");
		return mav;
	}
	@RequestMapping("selOrderByYue.action")
	public ModelAndView selOrderByYue(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String storeName = (String)request.getSession().getAttribute("storeName");
		Yuetu t = new Yuetu();
		//按月统计并形成一个折线图
    	String[] value = cd.count(storeName);
    	Double[] ds=new Double[value.length];
    	for(int i=0;i<value.length;i++){
    	ds[i]=Double.valueOf(value[i]);
    	}
	    XYDataset dataset = t.createXYDataset(ds);      
	
	    //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置      
	
	    JFreeChart freeChart = t.createChart(dataset);      
	
	    //步骤3：将JFreeChart对象输出到文件，Servlet输出流等      
		String path = request.getSession().getServletContext().getRealPath("/");
		String finalPath = path +"image/"+"Yuetu.png";
	    t.saveAsFile(freeChart, finalPath, 1000, 500);  
	    System.out.println(finalPath);
	    mav.addObject("flag", "1");
	    mav.setViewName("yuetu.jsp");
		return mav;
	}
	
	@RequestMapping("selOrderByYear.action")
	public ModelAndView selOrderByYear(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String storeName = (String)request.getSession().getAttribute("storeName");
		Yeartu t = new Yeartu();
		//按年统计并且形成一个折线图
    	String[] value = cd.countByYear(storeName);
    	Double[] ds=new Double[value.length];
    	for(int i=0;i<value.length;i++){
    	ds[i]=Double.valueOf(value[i]);
    	}
	    XYDataset dataset = t.createXYDataset(ds);      
	
	    //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置      
	
	    JFreeChart freeChart = t.createChart(dataset);      
	
	    //步骤3：将JFreeChart对象输出到文件，Servlet输出流等      
		String path = request.getSession().getServletContext().getRealPath("/");
		String finalPath = path +"image/"+"Yeartu.png";
	    t.saveAsFile(freeChart, finalPath, 1000, 500);  
	    System.out.println(finalPath);
	    mav.addObject("flag", "1");
	    mav.setViewName("yeartu.jsp");
		return mav;
	}
	//把店铺的信息生成一个饼图
	@RequestMapping("selOrderByStore.action")
	public ModelAndView selOrderByStore(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		BingTu t = new BingTu();
		HashMap<String,Double> oneMap = cd.countByStore(hyDateL, hyDateR,storeName);
        DefaultPieDataset data = t.getDataSet(oneMap); 
        JFreeChart freeChart = t.createChart(data);    
	    //步骤3：将JFreeChart对象输出到文件，Servlet输出流等      
		String path = request.getSession().getServletContext().getRealPath("/");
		String finalPath = path +"image/"+"store.png";
	    t.saveAsFile(freeChart, finalPath, 1000, 400);  
	    System.out.println(finalPath);
	    mav.addObject("flag", "1");
	    mav.setViewName("store.jsp");
		return mav;
	}
	//把每天的信息做成一个柱形图
	@RequestMapping("selOrderByDate.action")
	public ModelAndView selOrderByDate(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String hyDateL = request.getParameter("hyDateL");
		String hyDateR  = request.getParameter("hyDateR");
		String storeName = (String)request.getSession().getAttribute("storeName");
		Zhutu t = new Zhutu();
		List<Tu> uList = new ArrayList<Tu>();
		uList = cd.countByDate(hyDateL, hyDateR,storeName);
		CategoryDataset data = t.getDataSet(uList); 
        JFreeChart freeChart = t.createChart(data);    
	    //步骤3：将JFreeChart对象输出到文件，Servlet输出流等      
		String path = request.getSession().getServletContext().getRealPath("/");
		String finalPath = path +"image/"+"date.png";
	    t.saveAsFile(freeChart, finalPath, 1000, 400);  
	    System.out.println(finalPath);
	    mav.addObject("flag", "1");
	    mav.setViewName("zhutu.jsp");
		return mav;
	}
}
