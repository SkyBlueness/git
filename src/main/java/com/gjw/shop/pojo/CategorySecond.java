package com.gjw.shop.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



/**
 * 二级分类的实体
 * @author 
 *
 */
public class CategorySecond implements Serializable{
	private Integer csid;
	private String csname;
	// 所属一级分类.存的是一级分类的对象.
	private Integer cid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	// 配置商品集合
	private Set<Product> products = new HashSet<Product>();
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
