package com.bjsxt.shopping.order;

import java.util.Date;
import java.util.List;

import com.bjsxt.shopping.user.User;

public class SalesOrder {//与数据库中的SalesOrder对应
	private int id;

	private User user;

	private String addr;

	private Date oDate;

	private int status;

	private List<SalesItem> items;//这个数据库里面没有

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<SalesItem> getItems() {
		return items;
	}

	public void setItems(List<SalesItem> items) {
		this.items = items;
	}

	public Date getODate() {
		return oDate;
	}

	public void setODate(Date date) {
		oDate = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void updateStatus() {
		OrderMgr.getInstance().updateStatus(this);//这样很好
	}

}
