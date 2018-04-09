package com.hzyc.ccs.client.dao;


import com.hzyc.ccs.client.tools.JDBCTools;
import com.hzyc.ccs.client.model.Orders;

/**
 * 插入订单操作
 * 
 * @author Administrator
 *
 */
public class OrdersDao {
	//DataSer d = new DataSer();
	JDBCTools jt = new JDBCTools();

	public int insertSelection(Orders o){
		//insert into orders(dd_code,dd_time) value('','','','') 
		
		String sql1 = "insert into orders(";
		String sql2 = " value('";
		//sql1�����Ҫ��ȡ���һ��,������,����ȥ��,Ȼ����� ) ����
		//sql2�����Ҫ��Ҫȥ�� ,' �ټ���)
		
		if(o.getDdCode() != null){
			sql1 = sql1 + "dd_code" + ",";			
			sql2 = sql2 + o.getDdCode() + "','";			
		}
		if(o.getDdTime() !=null){
			sql1 = sql1 + "dd_time" + ",";
			sql2 = sql2 + o.getDdTime() + "','";
		}
		if(o.getCustomerName()!=null){
			sql1 = sql1 + "customer_name" + ",";			
			sql2 = sql2 + o.getCustomerName() + "','";		
		}
		if(o.getCustomerTel()!=null){
			sql1 = sql1 + "customer_tel" + ",";			
			sql2 = sql2 + o.getCustomerTel() + "','";		
		}
		if(o.getCustomerAddress()!=null){
			sql1 = sql1 + "customer_address" + ",";			
			sql2 = sql2 + o.getCustomerAddress() + "','";		
		}
		if(o.getFork()!=null){
			sql1 = sql1 + "fork" + ",";			
			sql2 = sql2 + o.getFork() + "','";		
		}
		if(o.getHat()!=null){
			sql1 = sql1 + "hat" + ",";			
			sql2 = sql2 + o.getHat() + "','";		
		}
		if(o.getSonghuoDate()!=null){
			sql1 = sql1 + "songhuo_date" + ",";			
			sql2 = sql2 + o.getSonghuoDate() + "','";		
		}
		if(o.getSonghuoTime()!=null){
			sql1 = sql1 + "songhuo_time" + ",";			
			sql2 = sql2 + o.getSonghuoTime() + "','";		
		}
		if(o.getCustomerBz()!=null){
			sql1 = sql1 + "customer_bz" + ",";			
			sql2 = sql2 + o.getCustomerBz() + "','";		
		}
		if(o.getPayState()!=null){
			sql1 = sql1 + "pay_state" + ",";			
			sql2 = sql2 + o.getPayState() + "','";		
		}
		if(o.getSonghuoState()!=null){
			sql1 = sql1 + "songhuo_state" + ",";			
			sql2 = sql2 + o.getSonghuoState() + "','";		
		}
		if(o.getZuhezhifuState()!=null){
			sql1 = sql1 + "zuhezhifu_state" + ",";			
			sql2 = sql2 + o.getZuhezhifuState() + "','";		
		}
		if(o.getYingshouMoney()!=null){
			sql1 = sql1 + "yingshou_money" + ",";			
			sql2 = sql2 + o.getYingshouMoney() + "','";		
		}
		if(o.getPayWay1()!=null){
			sql1 = sql1 + "pay_way1" + ",";			
			sql2 = sql2 + o.getPayWay1() + "','";		
		}
		if(o.getPayWay1ShishouMoney()!=null){
			sql1 = sql1 + "pay_way1_shishou_money" + ",";			
			sql2 = sql2 + o.getPayWay1ShishouMoney() + "','";		
		}
		if(o.getPayJifenMoney()!=null){
			sql1 = sql1 + "pay_jifen_money" + ",";			
			sql2 = sql2 + o.getPayJifenMoney() + "','";		
		}
		if(o.getPayWay2()!=null){
			sql1 = sql1 + "pay_way2" + ",";			
			sql2 = sql2 + o.getPayWay2() + "','";		
		}
		if(o.getPayWay2ShishouMoney()!=null){
			sql1 = sql1 + "pay_way2_shishou_money" + ",";			
			sql2 = sql2 + o.getPayWay2ShishouMoney() + "','";		
		}
		if(o.getZhaolingMoney()!=null){
			sql1 = sql1 + "zhaoling_money" + ",";			
			sql2 = sql2 + o.getZhaolingMoney() + "','";		
		}
		if(o.getSellStore()!=null){
			sql1 = sql1 + "sell_store" + ",";			
			sql2 = sql2 + o.getSellStore() + "','";		
		}
		if(o.getCashier()!=null){
			sql1 = sql1 + "cashier" + ",";			
			sql2 = sql2 + o.getCashier() + "','";		
		}
		if(o.getSonghuoPeople()!=null){
			sql1 = sql1 + "songhuo_people" + ",";			
			sql2 = sql2 + o.getSonghuoPeople() + "','";		
		}
		if(o.getPeisongfei()!=null){
			sql1 = sql1 + "peisongfei" + ",";			
			sql2 = sql2 + o.getPeisongfei() + "','";		
		}
		if(o.getVipCode()!=null){
			sql1 = sql1 + "vip_code" + ",";			
			sql2 = sql2 + o.getVipCode() + "','";		
		}
		if(o.getChanPinBz()!=null){
			sql1 = sql1 + "chan_pin_bz" + ",";			
			sql2 = sql2 + o.getChanPinBz() + "','";		
		}
		if(o.getJiFenToMoney()!=null){
			sql1 = sql1 + "jifen_to_money" + ",";			
			sql2 = sql2 + o.getJiFenToMoney() + "','";		
		}
		if(o.getZhuoHao()!=null){
			sql1 = sql1 + "zhuo_hao" + ",";			
			sql2 = sql2 + o.getZhuoHao() + "','";		
		}
		//System.out.println(sql1);
		//System.out.println(sql2);
		sql1 = sql1.substring(0, sql1.lastIndexOf(",")) + ")";
		
		sql2 = sql2.substring(0, sql2.lastIndexOf(",'")) + ")";
		
		int i = jt.update(sql1 + sql2);
		if( i > 0){
		//	d.insertSynData(sql1 + sql2);

		}
		return i;
	}
	
	/**
	 * ר�������޸�����״̬��
	 * 
	 * @author ��˧
	 * @param state �ͻ�״̬
	 * @param ddCode �����ţ�Ψһ������
	 * @return Boolean �޸��Ƿ�ɹ�
	 */
	public boolean updSonghuoState(String state,String ddCode){
		String sql = "update orders set songhuo_state = '"+state+"' where dd_code='"+ddCode+"'";
		int row = jt.update(sql);
		return row >= 1 ? true : false;
	}

}
