package com.hzyc.ccs.tools;

public class InsertSyn {
	public static int insertSql(String sql){
		JDBCTools jt = new JDBCTools();
		sql = sql.replace("'", "\\'");
		System.out.println(sql);
		long time = System.currentTimeMillis();
		String sql1 = "insert into syn_data(code,syn_sql,time) values('"+time+"','"+sql+"',now())";
		int s = jt.update(sql1);
		return s;
	}
	public static int insertSql1(String sql,String time){
		JDBCTools jt = new JDBCTools();
		sql = sql.replace("'", "\\'");
		System.out.println(sql);
		String sql1 = "insert into syn_data(code,syn_sql,time) values('"+time+"','"+sql+"',now())";
		int s = jt.update(sql1);
		return s;
	}
}
