package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.VipRecord;

public interface VipRecordMapper {
    int insert(VipRecord record);

    int insertSelective(VipRecord record);
}