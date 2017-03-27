package org.crazyit.app.domain;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@ManyToOne(cascade=CascadeType.ALL)
	private Group group;
    
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

 

}
