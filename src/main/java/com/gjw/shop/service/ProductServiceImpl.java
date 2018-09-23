package com.gjw.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.gjw.shop.mapper.ProductMapper;
import com.gjw.shop.pojo.Category;
import com.gjw.shop.pojo.CategorySecond;
import com.gjw.shop.pojo.Product;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	public List<Product> find_hot() {
		
		return productMapper.select_hot();
	}

	@Override
	public List<Product> find_new() {
		
		return productMapper.select_new();
	}

	@Override
	public Product find_product_by_pid(int pid) {
		
		return productMapper.select_product_by_pid(pid);
	}

	@Override
	public List<CategorySecond> find_csList_by_cid(int cid) {
		// TODO Auto-generated method stub
		return productMapper.select_csList_by_cid(cid);
	}

	@Override
	public List<Category> find_cList() {
		
		return productMapper.select_cList();
	}

	//查询商品
	public List<Product> find_product_by_cid(int cid) {
		return productMapper.select_by_cid(cid);
	}

	@Override
	public List<Product> find_product_by_csid(int csid) {
		return productMapper.select_by_csid(csid);
	}

}
