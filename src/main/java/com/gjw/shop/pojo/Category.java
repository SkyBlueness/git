package com.gjw.shop.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类
 * @author Administrator
 *
 */
public class Category implements Serializable{
	
	private Integer cid;
	private String cname;
	private List<CategorySecond>csList = new ArrayList<>();
	public List<CategorySecond> getCsList() {
		return csList;
	}
	public void setCsList(List<CategorySecond> csList) {
		this.csList = csList;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
