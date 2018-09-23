package com.gjw.shop.pojo;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

/**
 * 用户实体类
 * @author Administrator
 *
 */
public class User implements Serializable{
	private Integer uid;
	 @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)",
	    		message="用户名需要6-16位英文和数字的组合")
	private String username;
	private String password;
	private String name;
	 @Pattern(regexp="^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$",
	    		message="邮箱格式不正确")
	private String email;
	private String phone;
	private String addr;
	private Integer state;
	private String code;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
