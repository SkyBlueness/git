package com.gjw.shop.service;

import com.gjw.shop.pojo.User;

public interface UserService {

	User find_by_username(String username);

	void insert_user(User user);

	User find_by_code(String code);

	void update_user(User user);

	User login(User user);

}
