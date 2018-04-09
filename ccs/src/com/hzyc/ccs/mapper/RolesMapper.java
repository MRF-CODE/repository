package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.Roles;

public interface RolesMapper {
    int insert(Roles record);

    int insertSelective(Roles record);
}