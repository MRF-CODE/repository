package com.hzyc.ccs.client.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.hzyc.ccs.client.model.Orders;
import com.hzyc.ccs.serverinterface.OrderOperate;

public class JDBCTools {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public static String driver ,url ,username , password;
	
	public static void initDBProperties(){
		try {
			PropertiesConfiguration config = new PropertiesConfiguration("config/jdbc.properties");
			driver = config.getString("driver");
			url = config.getString("url");
			username = config.getString("username");
			password = config.getString("password");
			System.out.println(driver);
		} catch (Exception e) {
			System.out.println("hzyc : ��ȡ���ݿ������쳣:Read database connfigration failed");
			//Tishi.tishi(12);
			e.printStackTrace();
		} 
	}
	
	private void connect(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("hzyc : database connect failed");
			e.printStackTrace();
		//	Tishi.tishi(102);
			System.exit(0);
		}
	}
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			conn.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public int update(String sql) {
		int rows = 0;
		System.out.println("Update SQL : "+sql);
		try {
			connect();
			rows = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rows;
	}
	
	public ArrayList<HashMap<String, String>> find(String sql) {
		System.out.println("Query SQL : "+sql);
		ArrayList<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		try {
			connect();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, String> oneMap = new HashMap<String, String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					oneMap.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
				}
				rsList.add(oneMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rsList;
	}
	/**
	 * �޻ع�����ִ��
	 * */
	public void batch(ArrayList<String> sqls)  {
		try {
			connect();
			for (int i = 0; i < sqls.size(); i++) {
				System.out.println("Batch SQL : "+sqls.get(i));
				stmt.addBatch(sqls.get(i));
			}
			stmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	/**
	 * sql�쳣ֱ�ӻع�
	 * */
	public boolean batchWithRollback(ArrayList<String> sqls) throws SQLException  {
		try {
			connect();
			conn.setAutoCommit(false);
			for (int i = 0; i < sqls.size(); i++) {
				System.out.println("Batch SQL : "+sqls.get(i));
				stmt.addBatch(sqls.get(i));
			}
			stmt.executeBatch();
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sqlִ���쳣���ع�������...");
			conn.rollback();
			return false;
		} finally {
			close();
		}
	}
	
	
	public static void main(String[] args) throws SQLException {
		OrderOperate op = new OrderOperate();
		Orders order = new Orders();
		System.out.println(1);
		int o = op.insertOrder(order);
		System.out.println(o);
		System.out.println(2);
	}
} 




