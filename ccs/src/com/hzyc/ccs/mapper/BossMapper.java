package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.Boss;

public interface BossMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Boss record);

    int insertSelective(Boss record);

    Boss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Boss record);

    int updateByPrimaryKey(Boss record);
    
    Boss login(Boss u);
    String selPass(int value);
    boolean updPw(Boss u);
}