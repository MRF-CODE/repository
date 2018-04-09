package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.StoreSign;

public interface StoreSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreSign record);

    int insertSelective(StoreSign record);

    StoreSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreSign record);

    int updateByPrimaryKey(StoreSign record);
}