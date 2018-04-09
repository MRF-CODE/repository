package com.hzyc.ccs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.ccs.dao.CountDao;
import com.hzyc.ccs.mapper.CancelOrdersMapper;
import com.hzyc.ccs.mapper.OrdersMapper;
import com.hzyc.ccs.model.CancelOrders;
import com.hzyc.ccs.model.Orders;
import com.hzyc.ccs.service.OrderSer;


@Service	
public class OrderSerImpl implements OrderSer{
	
	CountDao cd = new CountDao();
	/*@Autowired
	CancelOrdersMapper CancelMapper;
	public List<Orders> selAllOrder(Orders order){
		return ordersMapper.selAllOrder(order);
	}
	
	public List<Orders> selAllOrderFenYe(Orders order){
		return ordersMapper.selAllOrderFenYe(order);
	}*/
	public List<CancelOrders> selcancelOrder(String hyDateL,String hyDateR){
		return cd.selcancelOrder(hyDateL,hyDateR);
	}
	public String selBuyGood(String code){
		return cd.selBuyGood(code);
	}
}
