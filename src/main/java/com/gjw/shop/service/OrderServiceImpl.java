package com.gjw.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gjw.shop.mapper.OrderMapper;
import com.gjw.shop.pojo.Order;
import com.gjw.shop.pojo.OrderItem;
@Controller
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	public void save_order(Order order) {
		orderMapper.insert_order(order);
	}

	@Override
	public void save_orderItem(OrderItem orderItem) {
		orderMapper.insert_orderItem(orderItem);
	}

	@Override
	public List<Order> find_order_by_uid(int uid) {
		
		return orderMapper.select_order_by_uid(uid);
	}

	@Override
	public Order find_order_by_oid(int oid) {
		// TODO Auto-generated method stub
		return orderMapper.select_order_by_oid(oid);
	}

	@Override
	public void update_order(Order order) {
		orderMapper.update_order(order);
	}

}
