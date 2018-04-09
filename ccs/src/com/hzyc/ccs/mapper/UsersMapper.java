package com.hzyc.ccs.mapper;

import java.util.List;

import com.hzyc.ccs.model.Users;
import com.hzyc.ccs.model.UsersWithBLOBs;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UsersWithBLOBs record);

    int insertSelective(UsersWithBLOBs record);

    UsersWithBLOBs selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UsersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UsersWithBLOBs record);

    int updateByPrimaryKey(Users record);
    List<Users> selUsers();
    
    Users selOneUser(String userid);
    
    List<Users> selUsersFenYe(Users u);
    
    int deleteByuname(String uname);
}