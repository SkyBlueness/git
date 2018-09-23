package com.gejinwei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gejinwei.mapper.EmployeeMapper;
import com.gejinwei.pojo.Employee;
import com.gejinwei.pojo.EmployeeExample;
import com.gejinwei.pojo.EmployeeExample.Criteria;
import com.github.pagehelper.PageHelper;

@Service
public class EmpService {
	@Autowired
	EmployeeMapper employeeMapper;
	//查询所有客户
	public List<Employee> findAll() {
		
		List<Employee>list = employeeMapper.selectByExampleWithDept(null);
		return list;
	}
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}
	/**
	 * 按照名称查找是否有该名字的员工
	 * @param empName
	 * @return
	 */
	public boolean empName(String empName) {
		EmployeeExample employeeExample = new EmployeeExample();
		Criteria criteria = employeeExample.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(employeeExample);
		return count == 0;
	}
	/**
	 * 按照id查询
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByPrimaryKeyWithDept(id);
	}
	/**
	 * 更新员工信息
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		//调用根据empId更新
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}
	/**
	 * 根据id删除员工
	 * @param list
	 */
	public void deleteEmp(int id) {
		employeeMapper.deleteByPrimaryKey(id);
		
	}
	public void deleteAll(List<Integer> list) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from t_emp where emp_id in (1,2,3)
		criteria.andEmpIdIn(list);
		employeeMapper.deleteByExample(example);
	}
	
}
