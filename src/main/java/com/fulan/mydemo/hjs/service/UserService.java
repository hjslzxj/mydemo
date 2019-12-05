package com.fulan.mydemo.hjs.service;

import java.util.List;

import com.fulan.mydemo.hjs.entity.UserEntity;

public interface UserService {
	
	public int addUser(UserEntity user);

	public List<UserEntity> selectUser();
}
