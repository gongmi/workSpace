package org.crazyit.app.domain;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "seu_teacher")
 
public class Teacher {
	@Id
	@GeneratedValue
 
	private int id;
	private String name;
	private String title;
	@Transient
	private String yourWifeName;
    @Column(updatable=false)
	private Date birthDate;
	private boolean good;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public String getYourWifeName() {
		return yourWifeName;
	}

	public void setYourWifeName(String yourWifeName) {
		this.yourWifeName = yourWifeName;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

 
}
