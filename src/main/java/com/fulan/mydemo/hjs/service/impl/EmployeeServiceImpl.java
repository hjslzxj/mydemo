package com.fulan.mydemo.hjs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fulan.mydemo.hjs.dao.EmployeeDao;
import com.fulan.mydemo.hjs.entity.Employee;
import com.fulan.mydemo.hjs.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;
	@Override
	public Employee selectByUsername(String username) {
		return employeeDao.selectByUsername(username);
	}
	@Override
	public void updateSelectiveByPrimaryKey(Employee employee) {
		employeeDao.updateSelectiveByPrimaryKey(employee);
	}

}
