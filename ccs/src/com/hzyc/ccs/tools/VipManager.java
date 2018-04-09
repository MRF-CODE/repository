package com.hzyc.ccs.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzyc.ccs.model.CancelOrders;
import com.hzyc.ccs.model.Vip;
import com.hzyc.ccs.model.VipRecord;

public class VipManager {
	
	
	//将会员的ArrayList<HashMap<String,String> 转换为List<Vip>
	public List<Vip> transformToGList(ArrayList<HashMap<String,String>> rsList){
		List<Vip> gList = new ArrayList<Vip>();
		try {
			for(int i = 0;i < rsList.size();i++){
				Map<String,String> rsMap = rsList.get(i);
				Vip temp = new Vip();
				temp.setHyCode(rsMap.get("hy_code"));
				temp.setHyKindCode((rsMap.get("hy_kind_code")));
				temp.setHyTel(rsMap.get("hy_tel"));
				temp.setHyName(rsMap.get("hy_name"));
				temp.setHyCard(rsMap.get("hy_card"));
				temp.setHyPhoto((rsMap.get("hy_photo")));
				temp.setHyTotal(rsMap.get("hy_total"));
				temp.setHyRemainMoney(rsMap.get("hy_remain_money"));
				temp.setHyBirthday(rsMap.get("hy_birthday"));
				temp.setHyCunt((rsMap.get("hy_cunt")));
				temp.setHyBz(rsMap.get("hy_bz"));
				temp.setHyJf(rsMap.get("hy_jf"));
				temp.setHyDate(rsMap.get("hy_date"));
				temp.setHyExpense((rsMap.get("hy_expense")));
				temp.setHyAddress(rsMap.get("hy_address"));
				gList.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return gList;
	}
	public List<VipRecord> transformToGList1(ArrayList<HashMap<String,String>> rsList){
		List<VipRecord> gList = new ArrayList<VipRecord>();
		try {
			for(int i = 0;i < rsList.size();i++){
				Map<String,String> rsMap = rsList.get(i);
				VipRecord temp = new VipRecord();
				temp.setId(rsMap.get("id").toString());
				temp.setCode(rsMap.get("code").toString());
				temp.setType(rsMap.get("type").toString());
				temp.setMoney(rsMap.get("money").toString());
			 	temp.setGiveMoney(rsMap.get("give_money"));
				temp.setOperateDate(rsMap.get("operate_date"));
				temp.setGiveGood(rsMap.get("give_good"));
				temp.setIsReceive(rsMap.get("is_receive"));
				temp.setLingquDate(rsMap.get("lingquDate"));
				gList.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gList;
	}
	public List<CancelOrders> transformToGList2(ArrayList<HashMap<String,String>> rsList){
		List<CancelOrders> gList = new ArrayList<CancelOrders>();
		try {
			for(int i = 0;i < rsList.size();i++){
				Map<String,String> rsMap = rsList.get(i);
				CancelOrders temp = new CancelOrders();
				temp.setDdCode(rsMap.get("dd_code"));
				temp.setDdTime(rsMap.get("dd_time"));
				if(rsMap.get("customer_name")!=null){
					temp.setCustomerName(rsMap.get("customer_name"));
				}else{
					temp.setCustomerName("");
				}
				if(rsMap.get("customer_tel")!=null){
					temp.setCustomerTel(rsMap.get("customer_tel"));
				}else{
					temp.setCustomerTel("");
				}
				if(rsMap.get("customer_address")!=null){
					temp.setCustomerAddress(rsMap.get("customer_address"));
				}else{
					temp.setCustomerAddress("");
				}
				temp.setFork(rsMap.get("fork"));
				temp.setHat(rsMap.get("hat"));
				temp.setSonghuoTime(rsMap.get("songhuo_time"));
				temp.setSonghuoDate(rsMap.get("songhuo_date"));
				temp.setCustomerBz(rsMap.get("customer_bz"));
				temp.setPayState(rsMap.get("pay_state"));
				temp.setSonghuoState(rsMap.get("songhuo_state"));
				temp.setZuhezhifuState(rsMap.get("zuhezhifu_state"));
				temp.setYingshouMoney(rsMap.get("yingshou_money"));
				temp.setPayWay1(rsMap.get("pay_way1"));
				temp.setPayWay1ShishouMoney(rsMap.get("pay_way1_shishou_money"));
				temp.setPayJifenMoney(rsMap.get("pay_jifen_money"));
				temp.setPayWay2(rsMap.get("pay_way2"));
				temp.setPayWay2ShishouMoney(rsMap.get("pay_way2_shishou_money"));
				temp.setZhaolingMoney(rsMap.get("zhaoling_money"));
				temp.setSellStore(rsMap.get("sell_store"));
				temp.setCashier(rsMap.get("cashier"));
				if(rsMap.get("songhuo_people")!=null){
					temp.setSonghuoPeople(rsMap.get("songhuo_people"));
				}else{
					temp.setSonghuoPeople("");
				}
				temp.setPeisongfei(rsMap.get("peisongfei"));
				temp.setVipCode(rsMap.get("vip_code"));
				temp.setChanPinBz(rsMap.get("chan_pin_bz"));
				temp.setJifenToMoney(rsMap.get("jifen_to_money"));
				temp.setZhuoHao(rsMap.get("zhuo_hao"));
				temp.setoPeople(rsMap.get("o_people"));
				temp.setoTime(rsMap.get("o_time"));
				
				gList.add(temp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gList;
	}
}
