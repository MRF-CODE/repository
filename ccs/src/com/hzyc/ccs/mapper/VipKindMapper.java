package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.VipKind;

public interface VipKindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipKind record);

    int insertSelective(VipKind record);

    VipKind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipKind record);

    int updateByPrimaryKey(VipKind record);
    
}