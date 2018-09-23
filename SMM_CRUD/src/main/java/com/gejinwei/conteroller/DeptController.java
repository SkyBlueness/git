package com.gejinwei.conteroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gejinwei.pojo.Department;
import com.gejinwei.pojo.Msg;
import com.gejinwei.service.DeptService;

@Controller
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@ResponseBody
	@RequestMapping("/depts")
	public Msg findDept(){
		List<Department> list = deptService.findDept();
		return Msg.success().add("dept", list);
	}
}
