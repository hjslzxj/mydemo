package com.fulan.mydemo.hjs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.mydemo.hjs.entity.UserEntity;
import com.fulan.mydemo.hjs.service.UserService;
import com.fulan.mydemo.hjs.utils.BaseRespose;
import com.fulan.mydemo.hjs.utils.RedisUtil;

/** 
 *@version:（版本，具体版本信息自己来定） 
 * @Description: （对类进行功能描述） 
 * @author: hjs 
 * @date: 2019年11月7日 
 */ 
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
    RedisUtil redisUtil;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String helloSpringBoot() {
		redisUtil.set("hjs", "zxj");
		Object object = redisUtil.get("hjs");
		Object object1 = redisUtil.get("hjs1");
		System.out.println(object);
		System.out.println(object1);
		return "Hello";
	}
	
	@RequestMapping(value="/selectUser",method=RequestMethod.GET)
	@ResponseBody
	public BaseRespose selectUser(){
		BaseRespose respose = new BaseRespose();
		respose.setCode(0);
		respose.setMsg("获得数据成功");
		respose.setCount(500);
		respose.setData(userService.selectUser());

//		List<UserEntity> user = userService.selectUser();
		return respose;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public String addUserTest(UserEntity userTest,Model model){
            int i = userService.addUser(userTest);
            model.addAttribute("flag", i);
	    return "index";
	}
}
