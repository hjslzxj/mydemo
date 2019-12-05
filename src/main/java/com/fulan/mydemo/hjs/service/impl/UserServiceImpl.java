package com.fulan.mydemo.hjs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fulan.mydemo.hjs.dao.UserDao;
import com.fulan.mydemo.hjs.entity.UserEntity;
import com.fulan.mydemo.hjs.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public int addUser(UserEntity user) {
		return userDao.addUser(user);
	}

	@Override
	public List<UserEntity> selectUser() {
		return userDao.selectUser();
	}

}
