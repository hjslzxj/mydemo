package com.fulan.mydemo.hjs.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fulan.mydemo.hjs.entity.UserEntity;

@Mapper
public interface UserDao {
    public int addUser(UserEntity userTest);

	public List<UserEntity> selectUser();
}
