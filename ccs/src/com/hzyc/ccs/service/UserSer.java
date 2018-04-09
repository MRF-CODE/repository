package com.hzyc.ccs.service;

import java.sql.SQLException;
import java.util.List;

import com.hzyc.ccs.model.Boss;
import com.hzyc.ccs.model.StoreSign;
import com.hzyc.ccs.model.Users;

public interface UserSer {
	public Boss Login(Boss b);
	public String selPass(int value);
	public boolean updPw(Boss b);
	public int addStore(StoreSign storeSign);
	public List<Users> selUsers(String storeNum,String permission);
	public Users selOneUser(String userid);
	public List<Users> selAllVipFenYe(Users u);
	public boolean deleteEmployee(String uname)throws SQLException;
	public String[] selStore();
	public List<Users> managerLogin(Users u);
	public String selStoreById(String id);
}
