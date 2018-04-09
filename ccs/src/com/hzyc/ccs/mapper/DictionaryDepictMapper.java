package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.DictionaryDepict;

public interface DictionaryDepictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictionaryDepict record);

    int insertSelective(DictionaryDepict record);

    DictionaryDepict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictionaryDepict record);

    int updateByPrimaryKey(DictionaryDepict record);
}