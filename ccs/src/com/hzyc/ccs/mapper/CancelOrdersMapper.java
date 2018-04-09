package com.hzyc.ccs.mapper;

import com.hzyc.ccs.model.CancelOrders;

public interface CancelOrdersMapper {
    int insert(CancelOrders record);

    int insertSelective(CancelOrders record);
}