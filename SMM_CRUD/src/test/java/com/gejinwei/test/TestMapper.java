package com.gejinwei.test;



import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gejinwei.mapper.EmployeeMapper;
import com.gejinwei.pojo.Employee;
/*
 * 
 * 测试mapper接口
 */
public class TestMapper {
	@Test
	public void testMapper(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
		List<Employee>list = employeeMapper.selectByExampleWithDept(null);
		System.out.println(list.size());
	}
}
