package com.fulan.mydemo.hjs.entity;

import java.io.Serializable;

/** 
 *@version:（版本，具体版本信息自己来定） 
 * @Description: （对类进行功能描述） 
 * @author: hjs 
 * @date: 2019年11月7日 
 */ 
public class Project implements Serializable{

    private static final long serialVersionUID = 323603388801865829L;

    private Integer id; //编号
    private String projectname; //项目名称
    private String description; //项目简介
    private Integer sort; //排序
    private Integer state; //状态：0=禁用，1=开发
    private Integer ico; //
    private java.util.Date updatetime; //最后更改时间

	
	// set get
	
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
	
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
    
    public String getProjectname() {
        return this.projectname;
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
	
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Integer getState() {
        return this.state;
    }
	
    public void setIco(Integer ico) {
        this.ico = ico;
    }
    
    public Integer getIco() {
        return this.ico;
    }
	
    public void setUpdatetime(java.util.Date updatetime) {
        this.updatetime = updatetime;
    }
    
    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }
}