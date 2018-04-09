package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.Kucun;

public interface KucunMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Kucun record);

    int insertSelective(Kucun record);

    Kucun selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Kucun record);

    int updateByPrimaryKey(Kucun record);
}