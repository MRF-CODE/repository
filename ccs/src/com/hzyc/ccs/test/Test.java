package com.hzyc.ccs.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.hzyc.ccs.tools.JDBCTools;

public class Test {
	public static void main(String[] args) {
		JDBCTools jt = new JDBCTools();
		ArrayList<String> sqls = new ArrayList<String>();
		
		String sql1 = "insert into orders(dd_code,dd_time,pay_state,zuhezhifu_state,yingshou_money,pay_way1,pay_way1_shishou_money,pay_jifen_money,pay_way2,pay_way2_shishou_money,zhaoling_money,sell_store,cashier,zhuo_hao) values('103TSDD1515848082919','2018-01-13 20:55','已支付','否','32.0','现金支付','32.0','0.0','不使用组合方式','0.0','0.00','力旺店','王子?','堂食1')";
		String sql2 = "insert into orderdetail(dd_code,good_code,number,yingshou_money) values('103TSDD1515848082919','1509844572841','1','32.00')";
		String sql3 = "update goods set good_number = '2' where good_code = '1509844572841'";
		sqls.add(sql1);
		sqls.add(sql2);
		sqls.add(sql3);
		boolean flag = jt.batchWithRollback(sqls);
		System.out.println(flag);
		Date date  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = sdf.format(date);
		System.out.println(currentDate);
	}
}
