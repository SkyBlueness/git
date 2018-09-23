package com.gjw.shop.mapper;

import java.util.List;

import com.gjw.shop.pojo.Order;
import com.gjw.shop.pojo.OrderItem;

public interface OrderMapper {

	void insert_order(Order order);

	void insert_orderItem(OrderItem orderItem);

	List<Order> select_order_by_uid(int uid);

	Order select_order_by_oid(int oid);

	void update_order(Order order);

}
