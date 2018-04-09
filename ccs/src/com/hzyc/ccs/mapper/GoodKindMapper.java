package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.GoodKind;

public interface GoodKindMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodKind record);

    int insertSelective(GoodKind record);

    GoodKind selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodKind record);

    int updateByPrimaryKey(GoodKind record);
}