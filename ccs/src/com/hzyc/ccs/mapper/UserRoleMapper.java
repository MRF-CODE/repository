package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}