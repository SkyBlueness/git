package com.gejinwei.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 回显页面信息
 * @author Administrator
 *
 */
public class Msg {
	//状态码100表示成功，200表示失败
	private int zhuangtai;
	
	private Map<String,Object>map = new HashMap<>();;
	
	//状态信息
	private String xingxi;
	
	public Msg add(String string,Object object){
		this.getMap().put(string, object);
		return this;
		
	}
	
	public static Msg success(){
		Msg msg = new Msg();
		msg.setZhuangtai(100);
		msg.setXingxi("操作成功");
		return msg;
	}
	
	public static Msg fail(){
		Msg msg = new Msg();
		msg.setZhuangtai(200);
		msg.setXingxi("操作失败");
		return msg;
	}
	
	public int getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(int zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getXingxi() {
		return xingxi;
	}

	public void setXingxi(String xingxi) {
		this.xingxi = xingxi;
	}

	public void sesscus(){
		
	}
	
}
