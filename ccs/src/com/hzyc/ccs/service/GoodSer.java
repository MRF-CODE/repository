package com.hzyc.ccs.service;

import java.util.List;

import com.hzyc.ccs.model.GoodKind;
import com.hzyc.ccs.model.Goods;

public interface GoodSer {
	public int insertGood(Goods g);
	public List<Goods> selGoodByState();
	public int insertGoodKind(GoodKind goodKind);
 	public List<Goods> selGood(String bkind,String skind,String goodName);
 	public Goods selOneGood(String code);
 	public List<Goods> selGoodFenYe(Goods g);
 	public int updateGood(Goods g);
 	public int deleteGood(String goodCode);
 	public List<GoodKind> selSkind();
 	public int deleteSkind(String id);
}
