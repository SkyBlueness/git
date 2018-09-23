package com.gjw.shop.mapper;

import java.util.List;

import com.gjw.shop.pojo.Category;
import com.gjw.shop.pojo.CategorySecond;
import com.gjw.shop.pojo.Product;

public interface ProductMapper {

	List<Product> select_hot();

	List<Product> select_new();

	Product select_product_by_pid(int pid);

	List<CategorySecond> select_csList_by_cid(int cid);

	List<Category> select_cList();
	
	List<Product> select_by_cid(int cid);

	List<Product> select_by_csid(int csid);

}
