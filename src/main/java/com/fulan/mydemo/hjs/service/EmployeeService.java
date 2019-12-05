package com.fulan.mydemo.hjs.service;

import com.fulan.mydemo.hjs.entity.Employee;

public interface EmployeeService {

	Employee selectByUsername(String username);

	void updateSelectiveByPrimaryKey(Employee employee);

}
