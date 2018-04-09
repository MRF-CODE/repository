package com.hzyc.ccs.mapper;

import java.util.List;

import com.hzyc.ccs.model.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(String goodCode);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodCode);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
    // 查询所有未同步到客户端的商品
    List<Goods> selGoodByState();
    
    List<Goods> selGood(Goods g);
    
    List<Goods> selGoodFenYe(Goods g);
    
    Goods selOneGood(String code);
}