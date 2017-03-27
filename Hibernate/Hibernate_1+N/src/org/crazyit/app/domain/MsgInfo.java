package org.crazyit.app.domain;

 

//报表时使用
public class MsgInfo { //VO=Value Object DTO=data transfer object
//	用户注册 需要username 两次密码p1 p2
//	临时对象 UserInfo 装 username  p1 p2 
//	当p1=p2时 才把UserInfo放进user放进db UserInfo->User->DB
	private int id;
	private String cont;
	private String topicName;
	private String categoryName;
	public MsgInfo(int id, String cont, String topicName, String categoryName) {
		super();
		this.id = id;
		this.cont = cont;
		this.topicName = topicName;
		this.categoryName = categoryName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	
}
