package com.gjw.shop.service;

import java.util.List;

import com.gjw.shop.pojo.Category;
import com.gjw.shop.pojo.CategorySecond;
import com.gjw.shop.pojo.Product;

public interface ProductService {

	List<Product> find_hot();

	List<Product> find_new();

	Product find_product_by_pid(int pid);

	List<CategorySecond> find_csList_by_cid(int cid);

	List<Category> find_cList();

	List<Product> find_product_by_cid(int cid);

	List<Product> find_product_by_csid(int csid);

}
