package com.hzyc.ccs.service;

import java.util.List;

import com.hzyc.ccs.model.CancelOrders;
import com.hzyc.ccs.model.Orders;

public interface OrderSer {
	/*public List<Orders> selAllOrder(Orders order);
	
	public List<Orders> selAllOrderFenYe(Orders order);*/
	public List<CancelOrders> selcancelOrder(String hyDateL,String hyDateR);
	public String selBuyGood(String code);
}
