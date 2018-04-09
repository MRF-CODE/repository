package com.hzyc.ccs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.ccs.dao.GoodDao;
import com.hzyc.ccs.mapper.GoodKindMapper;
import com.hzyc.ccs.mapper.GoodsMapper;
import com.hzyc.ccs.model.GoodKind;
import com.hzyc.ccs.model.Goods;
import com.hzyc.ccs.service.GoodSer;

@Service
public class GoodSerImpl implements GoodSer{
	
	@Autowired
	GoodsMapper goodsMapper;
	GoodDao gd = new GoodDao();
	@Autowired
	GoodKindMapper goodKindMapper;
	
	
	public int insertGood(Goods g){
		return gd.insertGood(g);
	}
	public int updateGood(Goods g){
		return gd.updateGood(g);
	}
	public List<Goods> selGoodByState(){
		return goodsMapper.selGoodByState();
	}
	//添加类别的方法
	public int insertGoodKind(GoodKind goodKind){
		return gd.insertGoodKind(goodKind);
	}
	
	//根据用户输入的条件查询商品
	public List<Goods> selGood(String bkind,String skind,String goodName){
		return gd.selGood(bkind,skind,goodName);
	}
	//商品分页查询	
	public List<Goods> selGoodFenYe(Goods g){
		return goodsMapper.selGoodFenYe(g);
	}
	//查询一个商品
	public Goods selOneGood(String code){
		return goodsMapper.selOneGood(code);
	}
	//删除商品
	public int deleteGood(String goodCode){
		return gd.deleteByPrimaryKey(goodCode);
	}
	public List<GoodKind> selSkind(){
		return gd.selSkind();
	}
	public int deleteSkind(String id){
		return gd.deleteSkind(id);
	}
}
