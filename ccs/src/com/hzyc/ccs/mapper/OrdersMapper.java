package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.Orders;

public interface OrdersMapper {
    int insert(Orders record);

    int insertSelective(Orders record);
}