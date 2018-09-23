package com.gjw.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gjw.shop.mapper.CategoryMapper;
import com.gjw.shop.pojo.Category;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryMapper categoryMapper;
	public List<Category> find() {	
		return categoryMapper.select();
	}

}
