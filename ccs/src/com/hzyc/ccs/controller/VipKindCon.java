package com.hzyc.ccs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.model.VipKind;
import com.hzyc.ccs.service.VipSer;

@Controller
public class VipKindCon {
	
	@Autowired
	VipSer vipSer;
	
	@RequestMapping("selZhekou.action")
	public ModelAndView selZhekou(){
		ModelAndView mav = new ModelAndView();
		List<VipKind> vkList = vipSer.selZhekou();
		mav.addObject("vkList",vkList);
		mav.setViewName("systemInstall/vipZhekou/selZhekou.jsp");
		return mav;
	}
	
	@RequestMapping("selOneVipKind.action")
	public ModelAndView selOneVipKind(int id){
		ModelAndView mav = new ModelAndView();
		VipKind vd = vipSer.selOneVipKind(id);
		System.out.println(vd.getNeedMoney()+"--------------------------------");
		mav.addObject("vd",vd);
		mav.setViewName("systemInstall/vipZhekou/update_zhekou.jsp");
		return mav;
	}
	
	@RequestMapping("updateVipKind.action")
	public void updateVipKind(HttpServletResponse response,VipKind v) throws IOException{
		
	
		int s = vipSer.updateVipKind(v);
		if(s !=0){
			PrintWriter out = response.getWriter();
			out.print("<script>window.close();</script>");
		}
		
	}
}
