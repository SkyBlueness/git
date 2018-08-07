package com.gejinwei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gejinwei.mapper.DepartmentMapper;
import com.gejinwei.pojo.Department;
@Service
public class DeptService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	public List<Department> findDept() {
		List<Department> list = departmentMapper.selectByExample(null);
		return list;
	}
	
}
