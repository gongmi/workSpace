package org.crazyit.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="mygroup")
public class Group {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="group")
//	@JoinColumn(name="groupid")  // uni 时!!!!这里如果不加 则会自动生成连接表 并且这个column是属于myuser表的 不是属于group这个表
	private Set<User> users=new HashSet<User>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

 

 
}
