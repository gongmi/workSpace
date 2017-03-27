package org.crazyit.app.domain;

import javax.persistence.*;

@Entity
@Table(name="mygroup")
public class Group {
	@Id
	@GeneratedValue
	private int id;
	private String name;
    
	 

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

 
}
