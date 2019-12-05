package com.fulan.mydemo.hjs.entity;

import java.io.Serializable;

/** 
 *@version:（版本，具体版本信息自己来定） 
 * @Description: （对类进行功能描述） 
 * @author: hjs 
 * @date: 2019年11月7日 
 */ 
public class Module implements Serializable{

    private static final long serialVersionUID = 480526068522768459L;

    private Integer id; //编号
    private String modulename; //名称
    private String modulevalue; //模块值
    private String moduleurl; //连接地址
    private Integer isshow; //
    private Integer parentid; //父级栏目Id
    private Integer projectid; //项目Id
    private Integer navilevel; //栏目级别
    private String description; //简介
    private Integer sort; //排序
    private java.util.Date updatetime; //修改时间

	
	// set get
	
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
	
    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
    
    public String getModulename() {
        return this.modulename;
    }
	
    public void setModulevalue(String modulevalue) {
        this.modulevalue = modulevalue;
    }
    
    public String getModulevalue() {
        return this.modulevalue;
    }
	
    public void setModuleurl(String moduleurl) {
        this.moduleurl = moduleurl;
    }
    
    public String getModuleurl() {
        return this.moduleurl;
    }
	
    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }
    
    public Integer getIsshow() {
        return this.isshow;
    }
	
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    
    public Integer getParentid() {
        return this.parentid;
    }
	
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }
    
    public Integer getProjectid() {
        return this.projectid;
    }
	
    public void setNavilevel(Integer navilevel) {
        this.navilevel = navilevel;
    }
    
    public Integer getNavilevel() {
        return this.navilevel;
    }
	
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
	
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    public Integer getSort() {
        return this.sort;
    }
	
    public void setUpdatetime(java.util.Date updatetime) {
        this.updatetime = updatetime;
    }
    
    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }
}