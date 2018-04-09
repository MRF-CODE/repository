package com.hzyc.ccs.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.ccs.dao.UserDao;
import com.hzyc.ccs.mapper.BossMapper;
import com.hzyc.ccs.mapper.StoreSignMapper;
import com.hzyc.ccs.mapper.UsersMapper;
import com.hzyc.ccs.model.Boss;
import com.hzyc.ccs.model.StoreSign;
import com.hzyc.ccs.model.Users;
import com.hzyc.ccs.service.UserSer;
@Service
public class UserSerImpl implements UserSer{
	
	@Autowired
	UsersMapper usersMapper;
	
	@Autowired
	StoreSignMapper storeSignMapper;
	
	@Autowired
	BossMapper bossMapper;
	UserDao ud = new UserDao();
	public Boss Login(Boss b) {
		return bossMapper.login(b);
	}
	//店长登录
	public List<Users> managerLogin(Users u){
		return ud.managerLogin(u);
	}
	public String selPass(int value){
		return bossMapper.selPass(value);
	}
	
	public boolean updPw(Boss b){
		return bossMapper.updPw(b);
	}
	
	public int addStore(StoreSign storeSign){
		return storeSignMapper.insert(storeSign);
	}
	public List<Users> selUsers(String storeNum,String permission){
		return ud.selUsers(storeNum,permission);
	}
	public Users selOneUser(String userid){
		return usersMapper.selOneUser(userid);
	}
	public List<Users> selAllVipFenYe(Users u){
		return usersMapper.selUsersFenYe(u);
	}
	public boolean deleteEmployee(String uname) throws SQLException{
		return ud.deleteByuname(uname);
	}
	public String[] selStore(){
		ArrayList<HashMap<String,String>> aList = ud.selStore();
		String[] s = new String[aList.size()];
		for(int i =0;i<aList.size();i++){
			s[i] = aList.get(i).get("id");
		}
		return s;
	}
	public String selStoreById(String id){
		ArrayList<HashMap<String,String>> aList = ud.selStoreById(id);
		String storeName = aList.get(0).get("sell_store");
		return storeName;
	}
}
