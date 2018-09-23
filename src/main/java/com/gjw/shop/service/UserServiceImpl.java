package com.gjw.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw.shop.mapper.UserMapper;
import com.gjw.shop.pojo.User;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	/**
	 * 根据用户名查询用户
	 */
	@Override
	public User find_by_username(String username) {
		
		return userMapper.select_by_username(username);
	}
	/**
	 * 添加用户
	 */
	@Override
	public void insert_user(User user) {
		userMapper.insert_user(user);
	}
	/**
	 * 根据激活码查询用户
	 */
	public User find_by_code(String code) {
		// TODO Auto-generated method stub
		return userMapper.select_by_code(code);
	}
	/**
	 * 修改用户信息
	 */
	public void update_user(User user) {
		userMapper.update_user(user);
	}
	/**
	 * 用户登录校验
	 */
	public User login(User user) {
		// TODO Auto-generated method stub
		return userMapper.select_by_user(user);
	}

}
