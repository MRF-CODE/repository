package com.hzyc.ccs.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzyc.ccs.model.Temp;
import com.hzyc.ccs.model.Vip;
import com.hzyc.ccs.model.VipKind;
import com.hzyc.ccs.model.VipRecord;
import com.hzyc.ccs.tools.Cl;
import com.hzyc.ccs.tools.GetNowTime;
import com.hzyc.ccs.tools.InsertSyn;
import com.hzyc.ccs.tools.JDBCTools;
import com.hzyc.ccs.tools.TranTime;
import com.hzyc.ccs.tools.VipManager;
import com.hzyc.ccs.tools.sendInfo;

public class VipDao {
	JDBCTools jt = new JDBCTools();
	VipManager g = new VipManager();
	public List<Vip> selAllVip(Vip vip){
		JDBCTools jt = new JDBCTools();
		String sql = "SELECT vip.*,SUM(pay_way1_shishou_money) FROM orders,vip WHERE pay_way1='会员支付'";
		if(vip.getHyDateL()!=null && vip.getHyDateL() != ""){
			sql += "AND SUBSTR(dd_time,1,10) >= '"+vip.getHyDateL()+"'"; 
		}
		if(vip.getHyDateR()!=null && vip.getHyDateR()!=""){
			sql += "AND SUBSTR(dd_time,1,10) <= '"+vip.getHyDateR()+"'"; 
		}
		if(vip.getHyExpenseL()!=null &&vip.getHyExpenseL()!=""){
			sql += "AND CAST(pay_way1_shishou_money AS SIGNED) >= '"+vip.getHyExpenseL()+"'"; 
		}
		if(vip.getHyExpenseR()!=null&&vip.getHyExpenseR()!=""){
			sql += "AND CAST(pay_way1_shishou_money AS SIGNED) <= '"+vip.getHyExpenseR()+"'"; 
		}
		if(vip.getHyCuntL()!=null&&vip.getHyCuntL()!=""){
			sql+="AND vip_code IN(SELECT a.vipcode FROM (SELECT COUNT(vip_code) AS num,vip_code AS vipcode FROM orders WHERE pay_way1='会员支付'GROUP BY vip_code) AS a WHERE a.num>='"+vip.getHyCuntL()+"')";
		}
		if(vip.getHyCuntR()!=null&&vip.getHyCuntR()!=""){
			sql+="AND vip_code IN(SELECT a.vipcode FROM (SELECT COUNT(vip_code) AS num,vip_code AS vipcode FROM orders WHERE pay_way1='会员支付'GROUP BY vip_code) AS a WHERE a.num<='"+vip.getHyCuntR()+"')";
		}
		sql+="AND orders.vip_code = vip.hy_code GROUP BY vip_code";
		ArrayList<HashMap<String,String>> vList = jt.find(sql);
		List<Vip> gList = new ArrayList<Vip>();
		for(int i = 0;i < vList.size();i++){
			Map<String,String> rsMap = vList.get(i);
			Vip temp = new Vip();
			temp.setHyCode(rsMap.get("hy_code"));
			
			String sql1 = "SELECT kind FROM vip_kind WHERE hy_kind_code = '"+rsMap.get("hy_kind_code")+"'";	
			ArrayList<HashMap<String,String>> aList = jt.find(sql1);
			temp.setHyKindCode(aList.get(0).get("kind"));
			temp.setHyTel(rsMap.get("hy_tel"));
			temp.setHyName(rsMap.get("hy_name"));
			temp.setHyBz(rsMap.get("hy_bz"));
			temp.setHyExpense(rsMap.get("SUM(pay_way1_shishou_money)"));
			gList.add(temp);
		}
		return gList;
	}
	
	public List<VipKind> selVipKind(){
		JDBCTools jt = new JDBCTools();
		String sql = "SELECT * FROM vip_kind";
		ArrayList<HashMap<String,String>> vkList = jt.find(sql);
		List<VipKind> gList = new ArrayList<VipKind>();
		for(int i = 0;i < vkList.size();i++){
			Map<String,String> rsMap = vkList.get(i);
			VipKind temp = new VipKind();
			temp.setId(Integer.parseInt(rsMap.get("id")));
			temp.setHyKindCode(rsMap.get("hy_kind_code"));
			temp.setKind(rsMap.get("kind"));
			temp.setZhekou(rsMap.get("zhekou"));
			temp.setNeedMoney(Integer.parseInt(rsMap.get("need_Money")));
			gList.add(temp);
		}
		return gList;
	}
	//修改会员级别
	public int updateVipKind(VipKind vd){
		JDBCTools jt = new JDBCTools();
		String sql = "UPDATE vip_kind SET kind ='"+vd.getKind()+"',zhekou='"+vd.getZhekou()+"',need_Money='"+vd.getNeedMoney()+"' WHERE id='"+vd.getId()+"'";
		InsertSyn.insertSql(sql);
		return jt.update(sql);
	}
	//添加会员
	public boolean insertVip(Vip vip,VipRecord vipRecord) throws SQLException{
		JDBCTools jt = new JDBCTools();
		GetNowTime gnt = new GetNowTime();
		long time = System.currentTimeMillis();
		String data = gnt.getTime();
		String giveGood = vipRecord.getGiveGood();
		System.out.println(giveGood);
		if(giveGood==null || giveGood.equals("")){
			vipRecord.setGiveGood("无");
			vipRecord.setIsReceive("无");
		}else{
			vipRecord.setIsReceive("否");
		}
		ArrayList<String> sqls = new ArrayList<String>();
		//插入到会员记录表里
		String sql1 = "insert into vip_record(id,code,type,money,give_money,operate_date,give_good,is_receive) " +
		"values('"+time+"','"+vip.getHyCode()+"','充值','"+vipRecord.getMoney()+"','"+vipRecord.getGiveMoney()+"','"+data+"','"+vipRecord.getGiveGood()+"','"+vipRecord.getIsReceive()+"')";
		//插入数据到会员表里
		String sql = "INSERT INTO vip (hy_code, hy_name,hy_kind_code,hy_tel,hy_total,hy_remain_money,hy_bz,hy_jf,hy_date,hy_address) " +
				"values('"+vip.getHyCode()+"','"+vip.getHyName()+"','"+vip.getHyKindCode()+"','"+vip.getHyTel()+"','"+vip.getHyTotal()+"','"+vip.getHyRemainMoney()+"','"+vip.getHyBz()+"','"+vip.getHyJf()+"','"+vip.getHyDate()+"','"+vip.getHyAddress()+"')";
		sqls.add(sql);
		sqls.add(sql1);
		boolean flag = jt.batch(sqls);
		if(flag){
			InsertSyn.insertSql(sql);
			InsertSyn.insertSql(sql1);
		}
		//插入同步表中
		if(flag){
			//在这发送短信提示会员
			try {
				String give_good = "";
				if(!vipRecord.getGiveGood().equals("无")){
					give_good  = ",赠品:"+vipRecord.getGiveGood()+",记得早点领取哦 ^ o ^";
				}
				VipDao vd = new VipDao();
				List<Vip> viplist = vd.selVipByCode(vip.getHyCode());
				if(viplist != null && viplist.size() > 0){
					Vip vip1 = viplist.get(0);
					sendInfo si = new sendInfo();
					si.send(vip.getHyTel(), "注册通知: 级别:" +vip1.getHyKindCode()+
							",折扣:" + vip1.getZhekou()+
				     		"折,会员号:" + vip1.getHyCode()+
				     		",余额:" + vip1.getHyRemainMoney()+
				     		give_good);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	public List<Vip> selVip(String tel){
		JDBCTools jt = new JDBCTools();
		boolean isNum = tel.matches("[0-9]+"); 
		String sql = "";
		if(isNum){
			sql = "select * from vip where hy_tel = '"+tel+"'";
		}else{
			sql = "select * from vip where hy_name = '"+tel+"'";
		}
		if(tel.equals("全部")){
			sql = "select * from vip";
		}
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		List<Vip> vList = g.transformToGList(aList);
		return vList;
	}
	public List<VipRecord> selVipDetail(String code){
		String sql = "select * from vip_record where code = '"+code+"'";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		List<VipRecord> vList = g.transformToGList1(aList);
		return vList;
	}
	public List<VipRecord> selVipDetailByDate(String code,String hyDateL,String hyDateR){
		String sql = "select * from vip_record where code = '"+code+"' AND SUBSTR(operate_date,1,10)>='"+hyDateL+"' and SUBSTR(operate_date,1,10)<='"+hyDateR+"'";
		System.out.println(sql);
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		List<VipRecord> vList = g.transformToGList1(aList);
		return vList;
	}
	public boolean insertAllVip(List<Vip> vList){
		ArrayList<String> sqls = new ArrayList<String>();
		for(Vip v:vList){
			String sql = "INSERT INTO vip (hy_code, hy_name,hy_kind_code,hy_tel,hy_total,hy_remain_money,hy_bz,hy_jf,hy_date,hy_address) " +
					"values('"+v.getHyCode()+"','"+v.getHyName()+"','"+v.getHyKindCode()+"','"+v.getHyTel()+"','"+v.getHyTotal()+"','"+v.getHyRemainMoney()+"','"+v.getHyBz()+"','"+v.getHyJf()+"','"+v.getHyDate()+"','"+v.getHyAddress()+"')";
			sqls.add(sql);
			long time = System.currentTimeMillis();
			int a = (int)(Math.random()*(9999-1000+1))+1000;
			String s = time+""+a+"";
			InsertSyn.insertSql1(sql, s);
		}
		boolean sucess =false;
		try {
			sucess = jt.batch(sqls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sucess;
	}
	public Vip selMaxCodeVip(){
		String sql = "SELECT hy_code hyCode,kind hyKindCode,hy_code hyCode,hy_tel hyTel,hy_name hyName,hy_jf hyJf,hy_expense hyExpense,hy_date hyDate,hy_bz hyBz,hy_total hyTotal,hy_remain_money hyRemainMoney,hy_address hyAddress  FROM vip_kind,vip WHERE vip_kind.hy_kind_code = vip.hy_kind_code and hy_code like '100%' ORDER BY hy_code DESC LIMIT 0,1";
		ArrayList<HashMap<String,String>> alhs = jt.find(sql);
		Vip v = new Vip();
		if(alhs != null && alhs.size() > 0){
			v.setHyCode(alhs.get(0).get("hyCode"));
		}
		return v;
		
	}
	public String selhyKindCode(String money){
		String sql = "SELECT hy_kind_code hyKindCode FROM vip_kind WHERE need_Money <='"+money+"' ORDER BY hy_kind_code DESC LIMIT 0,1";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		String hyKindCode = "";
		if(aList!=null&&aList.size()>0){
			hyKindCode = aList.get(0).get("hyKindCode");
		}else{
			hyKindCode = "1";
		}
		return hyKindCode;
	}
	
	public String selVipBuyGood(String code,String date){
		date = date.substring(0,16);
		TranTime tt = new TranTime();
		String updateDate = tt.trandate(date);
		//一次查询所有的记录的方法，但是太慢了，不行，先放着
		//String sql = "SELECT goods.good_name,orderdetail.number FROM goods,vip,orders,orderdetail,vip_record WHERE orders.vip_code = vip.hy_code AND orderdetail.dd_code = orders.dd_code AND orderdetail.good_code = goods.good_code AND vip_record.code=vip.hy_code AND orders.vip_code='"+code+"' AND orders.dd_time='"+date+"'  AND vip_record.type='消费' AND orders.dd_time=SUBSTR(vip_record.operate_date,1,16)";
		String sql = "SELECT goods.good_name,orderdetail.number FROM goods,orders,orderdetail WHERE orderdetail.dd_code = orders.dd_code AND orderdetail.good_code = goods.good_code AND orders.vip_code='"+code+"' AND (orders.dd_time='"+date+"' OR orders.dd_time='"+updateDate+"')";
		ArrayList<HashMap<String,String>> list = jt.find(sql);
		if(list != null && list.size() > 0){
			String goods = ""; 
			for(int i = 0; i < list.size(); i++){
				goods += list.get(i).get("good_name") +"*"+ list.get(i).get("number")+"+";
			}
			if(goods.length() > 2){
				goods = goods.substring(0,goods.length()-1);
			}
			return goods;
		}else{
			String sql1 = "SELECT goods.good_name,orderdetail.number,orderdetail.yingshou_money,vip_record.operate_date FROM goods,vip,cancel_orders,orderdetail,vip_record WHERE cancel_orders.vip_code = vip.hy_code AND orderdetail.dd_code = cancel_orders.dd_code AND orderdetail.good_code = goods.good_code AND cancel_orders.vip_code='"+code+"' AND cancel_orders.dd_time='"+date+"'  AND vip_record.type='消费' AND cancel_orders.dd_time=SUBSTR(vip_record.operate_date,1,16)";
			ArrayList<HashMap<String,String>> list1 = jt.find(sql1);
			if(list1 != null && list1.size() > 0){
				String goods = ""; 
				for(int i = 0; i < list1.size(); i++){
					goods += list1.get(i).get("good_name") +"*"+ list1.get(i).get("number")+"+";
				}
				if(goods.length() > 2){
					goods = goods.substring(0,goods.length()-1);
				}
				return goods;
			}else{
				return "";
			}
		}
	}
	public List<Vip> selVipByCode(String code){
		String sql = "SELECT zhekou,kind hyKindCode,hy_code hyCode,hy_tel hyTel,hy_name hyName,hy_jf hyJf,hy_expense hyExpense,hy_cunt hyCunt,hy_date hyDate,hy_bz hyBz,hy_total hyTotal,hy_remain_money hyRemainMoney,hy_address hyAddress  FROM vip_kind,vip  WHERE vip_kind.hy_kind_code = vip.hy_kind_code AND  hy_code='"+ code +"'";
		List<HashMap<String,String>> list = jt.find(sql);
		List<Vip> selList = new ArrayList<Vip>();
		for(int i = 0 ; i < list.size() ; i++){
			Vip u = new Vip();
			//注入属性值
			Cl.setFieldValue(u, list.get(i)); 
			selList.add(u);
		}
		return selList;
	}
	
	public int delVip(String code){
		String sql = "DELETE FROM vip WHERE hy_code ='"+code+"'";
		int sucess = jt.update(sql);
		if(sucess !=0){
			InsertSyn.insertSql(sql);
		}
		return sucess;
	}
	/**
	 * @param code
	 * @return
	 * 查询一个会员
	 */
	public Vip selOneVip(String code){
		String sql = "select * from vip where hy_code='"+code+"'";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		List<Vip> sList = g.transformToGList(aList);
		Vip v = sList.get(0);
		return v;
	}
	
	/**
	 * @return
	 * 查询当日注册会员
	 */
	public List<Vip> selVipByToday(){
		GetNowTime gnt = new GetNowTime();
		String sql = "SELECT * FROM vip WHERE hy_date ='"+gnt.time()+"'";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		List<Vip> sList = g.transformToGList(aList);
		return sList;
	}
	/**
	 * @return
	 * 查询当日续费会员
	 */
	public List<Vip> selVipByXuFei(){
		GetNowTime gnt = new GetNowTime();
		String sql = "SELECT * FROM vip,vip_record WHERE vip_record.code = vip.hy_code AND vip_record.type='充值' AND SUBSTR(operate_date,1,10)='"+gnt.time()+"' AND hy_code NOT IN (SELECT hy_code FROM vip WHERE hy_date ='"+gnt.time()+"')";
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		List<Vip> sList = g.transformToGList(aList);
		return sList;
	}
	public HashMap<String,String> selChongzhiBydate(String hyDateL,String hyDateR,String storeName){
		String sql = "";
		if(storeName.equals("admin")){
			sql = "SELECT IFNULL(SUM(money),0) FROM vip_record WHERE TYPE='充值' AND SUBSTR(operate_date,1,10) <='"+hyDateR+"' AND SUBSTR(operate_date,1,10)>='"+hyDateL+"'";
		}else{
			String sqlq = "select id from store_sign where sell_store = '"+storeName+"'";
			ArrayList<HashMap<String,String>> aList = jt.find(sqlq);
			String storeId = aList.get(0).get("id");
			sql = "SELECT IFNULL(SUM(money),0) FROM vip_record WHERE TYPE='充值' AND SUBSTR(operate_date,1,10) <='"+hyDateR+"' AND SUBSTR(operate_date,1,10)>='"+hyDateL+"' AND SUBSTR(CODE,1,3) = '"+storeId+"'";
		}
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		String money = aList.get(0).get("IFNULL(SUM(money),0)");
		HashMap<String,String> hMap = new HashMap<String,String>();
		hMap.put("会员充值金额", money);
		return hMap;
	}
	public Temp selMoney(String date){
		Temp t = new Temp();
		//查询所有会员总消费金额
		String sql1 = "SELECT IFNULL(SUM(pay_way1_shishou_money),0) FROM orders,vip WHERE pay_way1='会员支付' AND orders.vip_code = vip.hy_code";
		//查询所有会员，总充值金额，剩余金额
		String sql2 = "SELECT COUNT(hy_code),SUM(hy_total),SUM(hy_remain_money) FROM vip";
		//如果要查询今天的记录，那么拼接sql语句
		if(!date.equals("全部")&&!date.equals("续费")){
			sql1 +=" AND vip.hy_code IN (SELECT hy_code FROM vip WHERE hy_date='"+date+"')";
			sql2 +=" WHERE hy_date='"+date+"'";
		}
		GetNowTime gnt = new GetNowTime();
		if(date.equals("续费")){
			sql1 +=" AND orders.vip_code IN (SELECT vip.hy_code FROM vip,vip_record WHERE vip_record.code = vip.hy_code AND vip_record.type='充值' AND SUBSTR(operate_date,1,10)='"+gnt.time()+"' AND hy_code NOT IN (SELECT hy_code FROM vip WHERE hy_date ='"+gnt.time()+"'))";
			sql2 +=" WHERE hy_code IN (SELECT hy_code FROM vip,vip_record WHERE vip_record.code = vip.hy_code AND vip_record.type='充值' AND SUBSTR(operate_date,1,10)='"+gnt.time()+"' AND hy_code NOT IN (SELECT hy_code FROM vip WHERE hy_date ='"+gnt.time()+"'))";
		}
		ArrayList<HashMap<String,String>> qList= jt.find(sql1);
		ArrayList<HashMap<String,String>> wList = jt.find(sql2);
		String totalExpense = "0";
		String totalCunt = "0";
		String totaladd = "0";
		String totalRemain = "0";
		if(qList!=null && qList.size()>0){
			totalExpense = qList.get(0).get("IFNULL(SUM(pay_way1_shishou_money),0)");
		}
		if(wList!=null && wList.size()>0){
			totalCunt = wList.get(0).get("COUNT(hy_code)");
			totaladd = wList.get(0).get("SUM(hy_total)");
			totalRemain = wList.get(0).get("SUM(hy_remain_money)");
		}
		t.setTotalExpense(totalExpense);
		t.setTotalCunt(totalCunt);
		t.setTotaladd(totaladd);
		t.setTotalRemain(totalRemain);
		return t;
	}
}
