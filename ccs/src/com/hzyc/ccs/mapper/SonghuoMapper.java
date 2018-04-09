package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.Songhuo;

public interface SonghuoMapper {
    int insert(Songhuo record);

    int insertSelective(Songhuo record);
}