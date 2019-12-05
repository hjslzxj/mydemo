package com.fulan.mydemo.hjs.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.mydemo.hjs.entity.Employee;
import com.fulan.mydemo.hjs.service.EmployeeService;
import com.fulan.mydemo.hjs.utils.UtilMD5;
import com.fulan.mydemo.hjs.utils.Utils;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping(value="/loginDo",method=RequestMethod.POST)
	@ResponseBody
	public String loginDo(Model model,String username,String password,HttpServletRequest request) throws Exception{
		String msg = "请输入用户名和密码.";
		if(username != "" && password != ""){
			Employee employee = employeeService.selectByUsername(username);
			if(employee == null){
				msg = "用户不存在";
			}else {
				if(employee.getPassword().equals(UtilMD5.employeePwd(password))){
					if(employee.getStatus() == 1){
						employee.setLastloginip(Utils.getIpAddr(request));
						employee.setLastlogintime(new Date());
						employee.setToken(Utils.UUID());
    					
    					Employee newEmployee = new Employee();
    					newEmployee.setId(employee.getId());
    					newEmployee.setToken(employee.getToken());
    					newEmployee.setLogincount(employee.getLogincount()+1);
    					newEmployee.setLastloginip(employee.getLastloginip());
    					newEmployee.setLastlogintime(employee.getLastlogintime());
    					//更新登录时间和ip
    					employeeService.updateSelectiveByPrimaryKey(employee);
    					
        				HttpSession session = request.getSession();
        				session.setAttribute("user", employee);
        				return "redirect:fulan/static/html/index.html";
					}else {
						msg = "员工状态异常";
					}
				}else{
					msg = "密码错误·";
				}
			}
		}
		model.addAttribute("username", username);
    	model.addAttribute("password", password);
    	model.addAttribute("msg", msg);
		return "login";
	}
}
