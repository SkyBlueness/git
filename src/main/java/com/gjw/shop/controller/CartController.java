package com.gjw.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gjw.shop.pojo.Cart;
import com.gjw.shop.pojo.CartItem;
import com.gjw.shop.pojo.Product;
import com.gjw.shop.service.ProductService;

/**
 * 购物车类
 * @author Administrator
 *
 */


@Controller
public class CartController {
	@Autowired
	private ProductService productService;
	
	/**
	 * 跳转到购物车页面
	 */
	@RequestMapping("goto_cart")
	public String goto_cart(){
		return "cart";
	}
	/**
	 * 添加购物车
	 * @param count
	 * @param pid
	 * @param session
	 * @return
	 */
	@RequestMapping("add_cart")
	public String add_cart(int count,int pid,HttpSession session){
		//创建一个购物车项
		CartItem cartItem = new CartItem();
		//根据pid查询商品
		Product product = productService.find_product_by_pid(pid);
		//设置购物车项
		cartItem.setCount(count);
		cartItem.setProduct(product);
		Cart cart = get_cart(session);
		cart.addCart(cartItem);
		//将购物车放入session中
		session.setAttribute("cart", cart);
		return "cart";
	}
	//获取购物车
	private Cart get_cart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	/**
	 * 清空购物车
	 */
	@RequestMapping("clear_cart")
	public String clear_cart(HttpSession session){
		//获取购物车
		Cart cart = get_cart(session);
		//清空购物车
		cart.clearCart();
		session.setAttribute("cart", cart);
		return "cart";
	}
	/**
	 * 删除购物项
	 */
	@RequestMapping("remove_cart")
	public String remove_cart(int pid,HttpSession session){
		//获取购物车
		Cart cart = get_cart(session);
		//删除购物项
		cart.removeCart(pid);
		session.setAttribute("cart", cart);
		return "cart";
	}
}
