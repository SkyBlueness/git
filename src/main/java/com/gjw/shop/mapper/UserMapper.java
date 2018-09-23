package com.gjw.shop.mapper;

import com.gjw.shop.pojo.User;

public interface UserMapper {

	User select_by_username(String username);

	void insert_user(User user);

	User select_by_code(String code);

	void update_user(User user);

	User select_by_user(User user);
	
}
