package com.fulan.mydemo.hjs.entity;

import java.io.Serializable;

/** 
 *@version:（版本，具体版本信息自己来定） 
 * @Description: （对类进行功能描述） 
 * @author: hjs 
 * @date: 2019年11月7日 
 */ 
public class Employee implements Serializable{

    private static final long serialVersionUID = 549225150463309810L;

    private Integer id; //主键，自增
    private Integer agentid; //
    private Integer shopid; //店铺ID
    private String username; //用户名唯一键
    private String password; //密码
    private String realname; //姓名
    private Integer status; //状态0，无效；1，有效
    private Integer cityid; //市
    private Integer districtid; //区
    private Integer provinceid; //省
    private String cellphone; //联系手机
    private String photo; //用户头像
    private Integer depid; //部门id
    private Integer positionid; //职位ID
    private java.util.Date lastlogintime; //最后登陆时间
    private String lastloginip; //最后登陆时间
    private String mobiletoken; //
    private String token; //登录令牌
    private String getuiid; //
    private java.util.Date tokendeadline; //过期时间
    private Integer logincount; //登录次数
    private String createUser; //创建人
    private java.util.Date createTime; //创建时间
    private Boolean deleteFlag; //删除标识
    private java.util.Date deleteTime; //删除时间
    private String deleteUser; //删除者

	
	// set get
	
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
	
    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }
    
    public Integer getAgentid() {
        return this.agentid;
    }
	
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }
    
    public Integer getShopid() {
        return this.shopid;
    }
	
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
	
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return this.password;
    }
	
    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    public String getRealname() {
        return this.realname;
    }
	
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getStatus() {
        return this.status;
    }
	
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }
    
    public Integer getCityid() {
        return this.cityid;
    }
	
    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }
    
    public Integer getDistrictid() {
        return this.districtid;
    }
	
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }
    
    public Integer getProvinceid() {
        return this.provinceid;
    }
	
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
    
    public String getCellphone() {
        return this.cellphone;
    }
	
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getPhoto() {
        return this.photo;
    }
	
    public void setDepid(Integer depid) {
        this.depid = depid;
    }
    
    public Integer getDepid() {
        return this.depid;
    }
	
    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }
    
    public Integer getPositionid() {
        return this.positionid;
    }
	
    public void setLastlogintime(java.util.Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }
    
    public java.util.Date getLastlogintime() {
        return this.lastlogintime;
    }
	
    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }
    
    public String getLastloginip() {
        return this.lastloginip;
    }
	
    public void setMobiletoken(String mobiletoken) {
        this.mobiletoken = mobiletoken;
    }
    
    public String getMobiletoken() {
        return this.mobiletoken;
    }
	
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return this.token;
    }
	
    public void setGetuiid(String getuiid) {
        this.getuiid = getuiid;
    }
    
    public String getGetuiid() {
        return this.getuiid;
    }
	
    public void setTokendeadline(java.util.Date tokendeadline) {
        this.tokendeadline = tokendeadline;
    }
    
    public java.util.Date getTokendeadline() {
        return this.tokendeadline;
    }
	
    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }
    
    public Integer getLogincount() {
        return this.logincount;
    }
	
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public String getCreateUser() {
        return this.createUser;
    }
	
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    
    public java.util.Date getCreateTime() {
        return this.createTime;
    }
	
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    public Boolean getDeleteFlag() {
        return this.deleteFlag;
    }
	
    public void setDeleteTime(java.util.Date deleteTime) {
        this.deleteTime = deleteTime;
    }
    
    public java.util.Date getDeleteTime() {
        return this.deleteTime;
    }
	
    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }
    
    public String getDeleteUser() {
        return this.deleteUser;
    }
}