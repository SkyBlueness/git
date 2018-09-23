package com.gjw.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gjw.shop.pojo.Category;
import com.gjw.shop.pojo.Product;
import com.gjw.shop.service.CategoryService;
import com.gjw.shop.service.ProductService;

@Controller
public class IndexController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpSession session){
		//查询所有一级分类
		List<Category>clist = categoryService.find();
		session.setAttribute("clist", clist);
		//查询热门商品
		List<Product>hList = productService.find_hot();
		session.setAttribute("hList", hList);
		//查询最新商品
		List<Product>nList = productService.find_new();
		session.setAttribute("nList", nList);
		return "index";
	}

}
