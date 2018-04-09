package com.hzyc.ccs.client.service;
import com.hzyc.ccs.client.dao.OrdersDao;
import com.hzyc.ccs.client.model.Orders;

public class OrdersSer {
	
	OrdersDao od = new OrdersDao();
	
	public int insert(Orders o){		
		return od.insertSelection(o);
	}
	


}
