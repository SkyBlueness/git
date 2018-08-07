package com.gejinwei.conteroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gejinwei.pojo.Employee;
import com.gejinwei.pojo.Msg;
import com.gejinwei.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;

	// json数据返回员工列表
	@RequestMapping("/emps")
	@ResponseBody
	public Msg empList(@RequestParam(value = "page", defaultValue = "1") Integer page) {
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(page, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = empService.findAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page1 = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo", page1);
	}

	/**
	 * 查询员工数据（分页查询）
	 * 
	 * @return
	 */
	//@RequestMapping("/emp")
	public String getEmps(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(page, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = empService.findAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page1 = new PageInfo(emps, 5);
		model.addAttribute("pageInfo", page1);
		return "list";
	}
	/**
	 * 添加员工方法
	 * 使用JSR303校验
	 *1.先导入hibernate-validator-5.4.1.Final.jar包
	 *2.在要校验对象的实体类中需要校验的属性上面加入注解，定义规范
	 *3.在需要交验的对象钱添加@Valid注解
	 *4.在方法参数中加入BindingResult对象，BindingResult对象中封装校验信息
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult bindingResult){
		//判断校验是否通过
		if(bindingResult.hasErrors()){
			//定义一个map
			Map<String,String>map = new HashMap<>();
			List<FieldError> error =  bindingResult.getFieldErrors();
			//FieldError对象封装着错误信息
			for (FieldError fieldError : error) {
				//getField返回错误字段名，getDefaultMessage返回错误信息
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("fieldError", map);
		}
		empService.saveEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 校验名字是否可用
	 */
	@RequestMapping("/empName")
	@ResponseBody
	public Msg empName(@RequestParam(value="empName")String empName){
		//定义用户姓名的正则表达式
		String zz = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		//matches()用于该对象是否符合正则表达式
		if(!empName.matches(zz)){
			return Msg.fail().add("fail", "用户名可以是6-16位英文和数字的组合或者2-5位中文");
		}
		//查询是否有该名字的用户
		boolean b = empService.empName(empName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("fail", "用户名重复");
		}
		
	}
	/**
	 * 回显员工信息
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable(value="id")Integer id){
		Employee employee = empService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * 修改员工信息
	 */
	@RequestMapping(value="/update/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmp(Employee employee){
		empService.updateEmp(employee);
		return Msg.success();
	}
	/**
	 * 单个删除单个或多个员工
	 */
	//empId传入的是一个字符串
	@RequestMapping(value="/delete/{empId}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable(value="empId")String empId){
		//判断id里是否有-
		if(empId.contains("-")){
			List<Integer>list = new ArrayList<Integer>();
			//截取成字符串数组
			String [] s = empId.split("-");
			for (String string : s) {
				list.add(Integer.parseInt(string));
			}
			empService.deleteAll(list);
			
		}else{
			int id = Integer.parseInt(empId);
			empService.deleteEmp(id);
			
		}
		return Msg.success();
	}
}
