package com.gjw.shop.service;

import java.util.List;

import com.gjw.shop.pojo.Order;
import com.gjw.shop.pojo.OrderItem;

public interface OrderService {

	void save_order(Order order);

	void save_orderItem(OrderItem orderItem);

	List<Order> find_order_by_uid(int uid);

	Order find_order_by_oid(int oid);

	void update_order(Order order);

}
