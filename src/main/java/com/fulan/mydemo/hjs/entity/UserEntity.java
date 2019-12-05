package com.fulan.mydemo.hjs.entity;

public class UserEntity {
    private Integer id;
    private Integer age;
    private String name;
    private String sex;
    private String userInfo;
    
    public String getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "UserTestEntity [id=" + id + ", age=" + age + ", name=" + name + ", sex=" + sex
                + ", userInfo=" + userInfo + "]";
    }

}
