package com.gm;

import java.sql.*;
 
import java.util.Date;

public class Article {
private int id;
private int pid;
private int rootid;
private String title;
private String cont;
private Date pdate;
private boolean isleaf;
private int grade;//这个grade数据库里面没有 它表示的是这篇文章处于哪一层回复
public int getGrade() {
	return grade;
}
public void setGrade(int grade) {
	this.grade = grade;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public int getRootid() {
	return rootid;
}
public void setRootid(int rootid) {
	this.rootid = rootid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getCont() {
	return cont;
}
public void setCont(String cont) {
	this.cont = cont;
}
public Date getPdate() {
	return pdate;
}
public void setPdate(Date pdate) {
	this.pdate = pdate;
}
public boolean isIsleaf() {
	return isleaf;
}
public void setIsleaf(boolean isleaf) {
	this.isleaf = isleaf;
}
public void initFromRS(ResultSet RS) {//初始化一个article的方法 
 

try {
	this.setTitle(RS.getString("title"));
	this.setId(RS.getInt("id"));
	this.setPid(RS.getInt("pid"));
	this.setRootid(RS.getInt("rootid"));
	this.setTitle(RS.getString("title"));
	this.setCont(RS.getString("Cont"));
	this.setPdate(RS.getDate("pdate"));
	this.setIsleaf(RS.getInt("isleaf") == 0 ? true : false);
	this.setGrade(0);       //这里先这样  如果要改grade在外面改
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
}
}
