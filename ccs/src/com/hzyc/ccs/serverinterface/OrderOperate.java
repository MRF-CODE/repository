package com.hzyc.ccs.serverinterface;

import com.hzyc.ccs.client.model.Orders;
import com.hzyc.ccs.client.service.OrdersSer;

public class OrderOperate {
	
	OrdersSer os = new OrdersSer();
	
	/**
	 * 订单信息的插入
	 * 
	 * @param order 订单实体
	 * @return Integer 是否插入成功
	 */
	public int insertOrder(Orders order){
		
		int i = os.insert(order);
		return i;
	}
	
}
