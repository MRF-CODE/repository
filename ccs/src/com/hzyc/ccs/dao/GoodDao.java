package com.hzyc.ccs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hzyc.ccs.model.GoodKind;
import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.tools.GoodManager;
import com.hzyc.ccs.tools.InsertSyn;
import com.hzyc.ccs.tools.JDBCTools;

public class GoodDao {
	//增加商品
	public int insertGood(Goods g){
		JDBCTools jt = new JDBCTools();
		long time = System.currentTimeMillis();
		String sql = "insert into goods(good_code,good_name,good_price,good_bkind,good_skind,good_bz) values('"+time+"','"+g.getGoodName()+"'" +
				",'"+g.getGoodPrice()+"','"+g.getGoodBkind()+"','"+g.getGoodSkind()+"','"+g.getGoodBz()+"')";
		InsertSyn.insertSql(sql);
		return jt.update(sql);
	}
	//修改商品
	public int updateGood(Goods g){
		JDBCTools jt = new JDBCTools();
		String sql = "UPDATE goods SET good_name = '"+g.getGoodName()+"'," +
		"good_price = '"+g.getGoodPrice()+"' ,good_bkind='"+g.getGoodBkind()+"', " +
		" good_skind='"+g.getGoodSkind()+"' , good_bz='"+g.getGoodBz()+"' " +
		"WHERE good_code = '"+g.getGoodCode()+"'";
		InsertSyn.insertSql(sql);
		return jt.update(sql);
	}
	//增加商品类型
	public int insertGoodKind(GoodKind gk){
		JDBCTools jt = new JDBCTools();
		long time = System.currentTimeMillis();
		String sql = "INSERT INTO good_kind(id,LEVEL,pid,bz,NAME) VALUES('"+time+"','"+gk.getLevel()+"','"+gk.getPid()+"','"+gk.getBz()+"','"+gk.getName()+"')";
		InsertSyn.insertSql(sql);
		return jt.update(sql);
	}
	//按条件查询商品
	public List<Goods> selGood(String bkind,String skind,String goodName){
		JDBCTools jt = new JDBCTools();
		String sql="";
		if(goodName!=null&&!goodName.equals("")){
			sql="SELECT * FROM goods where good_name LIKE '%"+goodName+"%'";
			
		}else if(bkind.equals("全部")&&skind.equals("全部")&&goodName==null){
			sql="SELECT * FROM goods";
		}else if(!skind.equals("全部")){
			sql="SELECT * FROM goods WHERE good_skind = '"+skind+"'";
		}else if(!bkind.equals("全部")){
			sql="SELECT * FROM goods WHERE good_bkind = '"+bkind+"'";	
		}else{
			sql="SELECT * FROM goods";
		}
		
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		GoodManager g = new GoodManager();
		List<Goods> gList = g.transformToGList(aList);
		return gList;
	}
	public int deleteByPrimaryKey(String goodCode){
		String sql = "delete from goods where good_code='"+goodCode+"'";
		JDBCTools jt = new JDBCTools();
		int sucess = jt.update(sql);
		if(sucess!=0){
			InsertSyn.insertSql(sql);
		}
		return sucess;
	}
	public int deleteSkind(String id){
		String sql = "delete from good_kind where id='"+id+"'";
		JDBCTools jt = new JDBCTools();
		int sucess = jt.update(sql);
		if(sucess!=0){
			InsertSyn.insertSql(sql);
		}
		return sucess;
	}
	public List<GoodKind> selSkind(){
		String sql = "SELECT * FROM good_kind WHERE LEVEL = 2";
		JDBCTools jt = new JDBCTools();
		ArrayList<HashMap<String,String>> aList = jt.find(sql);
		GoodManager gd = new GoodManager();
		List<GoodKind> gkList = gd.transformToGList1(aList);
		return gkList;
	}
}
