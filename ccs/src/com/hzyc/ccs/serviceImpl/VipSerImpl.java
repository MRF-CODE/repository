package com.hzyc.ccs.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.ccs.dao.VipDao;
import com.hzyc.ccs.mapper.VipKindMapper;
import com.hzyc.ccs.mapper.VipMapper;
import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.model.Vip;
import com.hzyc.ccs.model.VipKind;
import com.hzyc.ccs.model.VipRecord;
import com.hzyc.ccs.service.VipSer;
import com.hzyc.ccs.tools.GetNowTime;

@Service
public class VipSerImpl implements VipSer {
	
	@Autowired
	VipMapper vipMapper;
	@Autowired
	VipKindMapper vipKindMapper;
	VipDao vd = new VipDao();
	public List<Vip> selAllVip() {
		return vipMapper.sellAllVip();
	}
	public List<Vip> selVip(String tel){
		return vd.selVip(tel);
	}
	//查询所有会员的消费金额
	public String selVipTotalExpense(Vip vip){
		return vipMapper.selVipTotalExpense(vip);
	}
	public List<VipKind> selZhekou(){
		return vd.selVipKind();
	}
	public VipKind selOneVipKind(int id){
		return vipKindMapper.selectByPrimaryKey(id);
	}
	public int updateVipKind(VipKind v){
		return vd.updateVipKind(v);
	}
	public boolean insertAllVip(List<Vip> vList){
		boolean flag = true;
		boolean newFlag = true;
		int max = 0;
		for(Vip v: vList){
			Vip v1 = vd.selMaxCodeVip();
			String hyCode ="";
		
			
			//如果会员表里没有数据
			if((v1 == null || v1.getHyCode() == null || v1.getHyCode().equals("")) && flag){
				flag = false;
				hyCode = "10010001";
				max = 10010001;
				
			}else{
				
				if(flag && newFlag){
					newFlag = false;
					max = Integer.parseInt(v1.getHyCode());
					
				}
				++max ;
			//	max = Integer.parseInt(v1.getHyCode());
				//hyCode = (String.valueOf(Integer.parseInt(v1.getHyCode())+1));
			}
			v.setHyCode(max+"");
		}
		boolean b = vd.insertAllVip(vList);
		return b;
	}
	public boolean insertVip(Vip vip,VipRecord vipRecord) throws SQLException{
		Vip v1 = vd.selMaxCodeVip();
		System.out.println(v1);
		String hyCode="";
		//如果会员表里没有数据
		if(v1 == null || v1.getHyCode() == null || v1.getHyCode().equals("")){
			hyCode = "10010001";
		}else{
			hyCode = (String.valueOf(Integer.parseInt(v1.getHyCode())+1));
		}
		String hyTotal = Float.parseFloat(vipRecord.getMoney())+Float.parseFloat(vipRecord.getGiveMoney())+"";
		vip.setHyTotal(hyTotal);
		String hyKindCode = vd.selhyKindCode(vip.getHyTotal());
		vip.setHyKindCode(hyKindCode);
		vip.setHyRemainMoney(hyTotal);
		vip.setHyJf(vipRecord.getMoney());
		GetNowTime g = new GetNowTime();
		vip.setHyDate(g.time());
		vip.setHyCode(hyCode);
		boolean sucess = vd.insertVip(vip, vipRecord);
		return sucess;
	}
	
	public String selVipBuyGood(String code,String date){
		return vd.selVipBuyGood(code, date);
	}
	public int delVip(String code){
		return vd.delVip(code);
	}
	public Vip selOneVip(String code){
		return vd.selOneVip(code);
	}
	public List<Vip> selVipByToday(){
		return vd.selVipByToday();
	}	
	public List<Vip> selVipByXuFei(){
		return vd.selVipByXuFei();
	}

}
