package com.hzyc.ccs.service;

import java.sql.SQLException;
import java.util.List;
import com.hzyc.ccs.model.Vip;
import com.hzyc.ccs.model.VipKind;
import com.hzyc.ccs.model.VipRecord;

public interface VipSer {
	public List<Vip> selAllVip();
	public List<Vip> selVip(String tel);
	public String selVipTotalExpense(Vip vip);
	public boolean insertAllVip(List<Vip> vList);
	public List<VipKind> selZhekou();
	public VipKind selOneVipKind(int id);
	public int updateVipKind(VipKind vd);
	public boolean insertVip(Vip vip,VipRecord vipRecord)throws SQLException;
	public String selVipBuyGood(String code,String date);
	public int delVip(String code);
	public Vip selOneVip(String code);
	public List<Vip> selVipByToday();
	public List<Vip> selVipByXuFei();
}
