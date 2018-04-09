package com.hzyc.ccs.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hzyc.ccs.model.Users;
import com.hzyc.ccs.tools.InsertSyn;
import com.hzyc.ccs.tools.JDBCTools;
import com.hzyc.ccs.tools.UsersManager;

public class UserDao {
	JDBCTools jt = new JDBCTools();
	public boolean deleteByuname(String uname) throws SQLException{
		ArrayList<String> sqls = new ArrayList<String>();
		String sql1 = "delete from users where uname='"+uname+"'";
		String sql2 = "delete from user_role where userid='"+uname+"'";
		sqls.add(sql1);
		sqls.add(sql2);
		boolean flag = jt.batch(sqls);
		if(flag){
			InsertSyn.insertSql(sql1);
			InsertSyn.insertSql(sql2);
		}
		return flag;
	}
	public ArrayList<HashMap<String,String>> selStore(){
		String sql = "SELECT id FROM store_sign";
		return jt.find(sql);
	}
	public ArrayList<HashMap<String,String>> selStoreById(String id){
		String sql = "SELECT sell_store FROM store_sign WHERE id = '"+id+"'";
		return jt.find(sql);
	}
	public List<Users> managerLogin(Users u){
		String sql = "SELECT store_name FROM users,user_role,roles WHERE users.uname=user_role.userid " +
				"AND roles.rid = user_role.rid AND rname='店长' and uname='"+u.getUname()+"' and userpw='"+u.getUserpw()+"'";
		
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		System.out.println(aList);
			UsersManager u1 = new UsersManager();
			List<Users> uList = u1.transformToGList(aList);
			System.out.println(uList.size());
			return uList;
	}
	
	/**
	 * @param storeNum	商店的代码
	 * @param permission 权限
	 * @return List<Users>
	 */
	public List<Users> selUsers(String storeNum,String permission){
		String sql = "";
		
		String sql1="SELECT * FROM users,user_role,roles WHERE 1 = 1 ";
		if(storeNum!=null &&  !storeNum.equals("") && !storeNum.equals("null")){
			sql1 += " and store_name='"+storeNum+"'";
		}
		if(permission!=null && !permission.equals("")&& !permission.equals("null")){
			sql1 += " and roles.rid="+permission+"";
		}
		sql1 += " AND users.uname = user_role.userid AND roles.rid = user_role.rid";
		
		/*if((storeNum==null ||storeNum.equals(""))&&(permission==null || permission.equals(""))){
			sql = "SELECT * FROM users";
		}else{
			sql = "SELECT * FROM users,user_role,roles WHERE 1 = 1 ";
		}
		if(storeNum!=null && !storeNum.equals("") && permission!=null && !permission.equals("")){
			sql+="AND store_name='"+storeNum+"'";
		}else if(storeNum!=null && !storeNum.equals("")){
			sql+="AND store_name='"+storeNum+"' AND users.uname = user_role.userid AND roles.rid = user_role.rid";
		}
		if(permission!=null && !permission.equals("")){
			sql+="AND users.uname = user_role.userid AND '"+Integer.parseInt(permission)+"' = roles.rid AND roles.rid = user_role.rid";
			System.out.println(sql);
		}*/
		ArrayList<HashMap<String,String>> aList = jt.find(sql1);
		UsersManager u = new UsersManager();
		List<Users> uList = u.transformToGList(aList);
		return uList;
	}
}
