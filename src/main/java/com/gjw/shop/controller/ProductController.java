package com.gjw.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjw.shop.pojo.Category;
import com.gjw.shop.pojo.Product;
import com.gjw.shop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	/**
	 * 跳转商品详情页面
	 * @param model
	 * @param pid
	 * @return
	 */
	@RequestMapping("find_product_by_pid")
	public String find_product_by_pid(Model model,@RequestParam int pid){
		//根据pid查询商品
		Product product = productService.find_product_by_pid(pid);
		List<Category> cList = productService.find_cList();
		model.addAttribute("cList", cList);
		model.addAttribute("product", product);
		return "product";
	}
	/**
	 * 根据cid查询商品
	 * @param model
	 * @param cid
	 * @return
	 */
	@RequestMapping("find_product_by_cid")
	public String find_product_by_cid(HttpSession session,Model model,@RequestParam int cid,@RequestParam(value="page",defaultValue="1")int page){
		//查询所有一级分类和二级分类，级联查询
		List<Category> cList = productService.find_cList();
		int pageSize = 20;		//每页20条数据
		//用户pageHelper插件分页查询
		PageHelper.startPage(page, pageSize);
		//根据cid分页查询商品
		List<Product> pList = productService.find_product_by_cid(cid);
		PageInfo pageList = new PageInfo<>(pList,5);
		model.addAttribute("pageList", pageList);
		model.addAttribute("cid", cid);
		session.setAttribute("cList", cList);
		return "productList";
	}
	/**
	 * 根据cs查询商品
	 * @param model
	 * @param cid
	 * @return
	 */
	@RequestMapping("find_product_by_csid")
	public String find_product_by_csid(Model model,@RequestParam int csid,@RequestParam(value="page",defaultValue="1")int page){
		//查询所有一级分类和耳机分类，级联查询
		int pageSize = 20;		//每页20条数据
		//用户pageHelper插件分页查询
		PageHelper.startPage(page, pageSize);
		//根据csid分页查询商品
		List<Product> pList = productService.find_product_by_csid(csid);
		PageInfo pageList = new PageInfo<>(pList,5);
		model.addAttribute("pageList", pageList);
		model.addAttribute("csid", csid);
		return "productList";
	}
}
