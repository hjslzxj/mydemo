package com.fulan.mydemo.hjs.dao;

import org.apache.ibatis.annotations.Mapper;

import com.fulan.mydemo.hjs.entity.Employee;

@Mapper
public interface EmployeeDao {

	Employee selectByUsername(String username);

	void updateSelectiveByPrimaryKey(Employee employee);

}
