package com.gjw.shop.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gjw.shop.pojo.Msg;
import com.gjw.shop.pojo.User;
import com.gjw.shop.service.UserService;
import com.gjw.shop.utils.MyMailUitls;
import com.gjw.shop.utils.VerifyCodeUtil;

/**
 * 用户模块的类
 * @author Administrator
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("goto_regist")
	public String goto_regist(){
		return "regist";
	}
	/**
	 * ajax异步验证用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping("find_by_username")
	@ResponseBody
	public Map find_by_username(String username,Model model){
		User user = userService.find_by_username(username);
		Map<Object,Object>map = new HashMap<>();
		if(user!=null){
			map.put("msg", "用户名已经存在");
		}else{
			map.put("msg", "用户名可用");
		}
		return map;
	}
	/**
	 * 添加用户方法
	 * 使用JSR303校验
	 *1.先导入hibernate-validator-5.4.1.Final.jar包
	 *2.在要校验对象的实体类中需要校验的属性上面加入注解，定义规范
	 *3.在需要交验的对象钱添加@Valid注解
	 *4.在方法参数中加入BindingResult对象，BindingResult对象中封装校验信息
	 */
	@RequestMapping("insert_user")
	public String insert_user(@Valid User user,BindingResult bindingResult,Model model,HttpSession session,String vCode,
			RedirectAttributes redirectAttributes){
		//判断是否有字段不符合验证
		if(bindingResult.hasErrors()){
			//获取验证信息
			List<FieldError> allErrors = bindingResult.getFieldErrors();
			//遍历错误信息
			for (FieldError fieldError : allErrors) {
				//获取错误字段名和错误提示信息
				redirectAttributes.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
			}
			//重定向到注册页面
			return "redirect:/goto_regist.do";
		}
		//获取验证码
		String code = (String) session.getAttribute("code");
		//判断验证码是否正确
		if(!vCode.equalsIgnoreCase(code)){
			redirectAttributes.addAttribute("yzm", "验证码输入错误!");
			return "redirect:/goto_regist.do";
		}
		//设置user对象为设置的字段
		//0代表为激活，1表示已激活
		user.setState(0);
		//随机生成字符串
		String uuid = UUID.randomUUID().toString();
		//获取邮箱地址
		String email = user.getEmail();
		//发送激活邮件
		MyMailUitls.sendMail(email, uuid);
		user.setCode(uuid);
		userService.insert_user(user);
		model.addAttribute("msg","注册成功,请去邮箱激活账号");
		return "msg";
	}
	/**
	 * 验证邮箱激活
	 */
	@RequestMapping("active")
	public String active(String code,Model model){
		//根据激活账号查询用户
		User user = userService.find_by_code(code);
		if(user==null){
			//为空激活失败
			model.addAttribute("msg", "激活失败,激活码错误！");
		}else{
			//去除激活码
			user.setCode(null);
			//将用户状态改为激活状态
			user.setState(1);
			userService.update_user(user);
			model.addAttribute("msg", "账号激活成功");
		}
		
		return "msg";
	}
	/**
	 * 跳转登录页面
	 */
	@RequestMapping("goto_login")
	public String goto_login (){
		return "login";
	}
	/**
	 * 登录校验方法
	 */
	@RequestMapping("login")
	public String login(User user,Model model,HttpSession session,String vCode){
		//获取验证码
		String code = (String) session.getAttribute("code");
		//判断是否正确
		if(!vCode.equalsIgnoreCase(code)){
			model.addAttribute("msg", "验证码输入错误!");
			return "login";
		}
		//查询用户是否存在
		User exUser = userService.login(user);
		if(exUser==null){
			model.addAttribute("msg", "用户不存在或者密码不正确!");
			return "login";
		}else {
			if(exUser.getState()==0){
				model.addAttribute("msg", "你的账号未激活,请查看邮箱!");
				return "login";
			}else{
				session.setAttribute("user", exUser);
				return "redirect:/index.do";
			}
		}			
	}
	/**
	 * 用户退出方法
	 */
	@RequestMapping("quit")
	public String quit(HttpSession session){
		session.invalidate();
		return "index";
	}
	/**
	 * 生成验证码
	 * @throws Exception 
	 */
	@RequestMapping("get_vCode")
	public String get_vCode(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		//获取验证码的文本值
		String code = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		session.setAttribute("code", code);
		/**
		 * 随机生成验证码图片
		 * width 图片宽度(注意此宽度若过小,容易造成验证码文本显示不全,如4个字符的文本可使用85到90的宽度)
		 * height 图片高度
		 * interLine 图片中干扰线的条数
		 * randomLocation 每个字符的高低位置是否随机
		 * backColor 图片颜色,若为null则表示采用随机颜色
		 * foreColor 字体颜色,若为null则表示采用随机颜色
		 * lineColor 干扰线颜色,若为null则表示采用随机颜色
		 */
		BufferedImage image = VerifyCodeUtil.generateImageCode(code, 90, 30, 4, false, Color.white, Color.BLACK, null);
		response.setContentType("image/jpep");
		/**
		 * 1.图片流对象
		 * 2.图片格式
		 * 3.输出流
		 */
		ImageIO.write(image, "jpeg", response.getOutputStream());
		return null;
	}
}
