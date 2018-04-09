package com.hzyc.ccs.mapper;

import java.util.List;

import com.hzyc.ccs.model.Vip;

public interface VipMapper {
    int insert(Vip record);

    int insertSelective(Vip record);
    //查询总数
    List<Vip> sellAllVip();
    //分页查询
    List<Vip> sellAllVipFenye(Vip record);
    //查询所有会员充值的总金额
    String selAllVipTotal();
    //查询所有会员消费的总金额
    String selVipTotalExpense(Vip vip);
}