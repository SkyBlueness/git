package com.gjw.shop.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 订单类
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjw.shop.pojo.Cart;
import com.gjw.shop.pojo.CartItem;
import com.gjw.shop.pojo.Order;
import com.gjw.shop.pojo.OrderItem;
import com.gjw.shop.pojo.User;
import com.gjw.shop.service.OrderService;
import com.gjw.shop.utils.PaymentUtil;
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 跳转订单页面
	 * @param session
	 * @return
	 */
	@RequestMapping("goto_order")
	public String goto_order(HttpSession session,HttpServletRequest request){
		//获取用户信息
		User user = (User) session.getAttribute("user");
		if(user==null){
			//用户未登录，返回登录页面
			request.setAttribute("msg","亲！请您先登录");
			return "login";
		}
		//获取购物车信息
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null||cart.getCartItems()==null||cart.getCartItems().isEmpty()){
			//用户没购物
			request.setAttribute("msg", "亲！请您先购物");
			return "msg";
		}
		Order order = new Order();
		//设置订单状态
		order.setState(1);  //订单状态1为未付款
		//设置用户id
		order.setUser(user);
		//保存到请求域中
		order.setTotal(cart.getTotal());
		//保存订单,并设置主键返回
		orderService.save_order(order);
		//遍历购物选项
		Collection<CartItem>CartItems = cart.getCartItems();
		//创建一个OrderItem集合
		List<OrderItem>list = new ArrayList<>();
		for (CartItem cartItem : CartItems) {
			//设置订单选项属性
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setOid(order.getOid());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			list.add(orderItem);
			orderService.save_orderItem(orderItem);
		}
		order.setOrderItems(list);
		session.setAttribute("order", order);
		//清空购物车
		cart.clearCart();
		return "order";
	}
	/**
	 * 跳转订单页面，并显示订单信息
	 */
	@RequestMapping("goto_orderList")
	public String goto_orderList(@RequestParam(name="page",defaultValue="1")int page,Model model,HttpSession session){
		User user = (User)session.getAttribute("user");
		//现实几条数据
		int pageSize = 5;
		//用pageHelper插件实现分页查询
		PageHelper.startPage(page, pageSize);
		//根据uid查询订单
		List<Order>orderList = orderService.find_order_by_uid(user.getUid());
		//列表查询后紧跟PageInfo
		PageInfo pageInfo = new PageInfo<>(orderList, 5);
		model.addAttribute("pageInfo",pageInfo);
		return "orderList";
	}
	/**
	 * 跳转付款页面，传送oid
	 * 
	 */
	@RequestMapping("find_order_by_oid")
	public String find_order_by_oid(@RequestParam int oid,HttpSession session){
		//根据oid查询订单
		Order order = orderService.find_order_by_oid(oid);
		session.setAttribute("order", order);
		return "order";
	}
	/**
	 * 订单支付方法
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("pay_order")
	public String pay_order(Order order,@RequestParam(name="pd_FrpId")String frpId,HttpServletResponse response) throws Exception{
		//修改订单信息
		orderService.update_order(order);
		//到第三方支付
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.1.254:8088/ssm_shop/order_callBack.do?oid="+order.getOid(); // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = frpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// 重定向:向易宝出发:
		response.sendRedirect(sb.toString());
		return null;
	}
	/**
	 * 支付成功后跳转回页面
	 */
	@RequestMapping("order_callBack")
	public String order_callBack(Order order,Model moder){
		//支付成功将订单状态改为2，已经付款
		order.setState(2);
		orderService.update_order(order);
		moder.addAttribute("msg", "支付成功");
		return "msg";
	}
	/**
	 * 	确认收货方法
	 */
	@RequestMapping("update_state")
	public String update_state(Order order,Model model) {
		//将订单状态修改为4，确认收货
		order.setState(4);
		//将数据库中的数据保存
		orderService.update_order(order);
		model.addAttribute("msg", "确认收货");
		return "msg";
	}
}
