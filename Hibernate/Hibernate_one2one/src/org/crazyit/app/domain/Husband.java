package org.crazyit.app.domain;

import javax.persistence.*;

@Entity
public class Husband {
	@Id
	@GeneratedValue
	private int id;
	private String name;
    @OneToOne(cascade=CascadeType.ALL)  //这个必须要有 不然会报错object references an unsaved transient instance
	@JoinColumn(name = "wifeid", unique=true) 
	private Wife wife;

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

	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}

}
