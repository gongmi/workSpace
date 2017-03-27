package com.gm.hrsystem.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
@Entity
@Table(name = "employee_inf")
@DiscriminatorColumn(name = "emp_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Employee implements Serializable {

	private static final long serialVersionUID = 48L;
	@Id
	@GeneratedValue
	@Column(name = "emp_id")
	private int id;
	@Column(name = "emp_name", nullable = false, unique = true)
	private String name;
	@Column(name = "emp_pass", nullable = false)
	private String pass;
	@Column(name = "emp_salary", nullable = false)
	private double salary;
	
	//mgr是emp的子类 他们之间的关联实际是一种自关联。
	//所有的自关联中的外键列都不可能有非空约束P774
	@ManyToOne
	@JoinColumn(name = "mgr_id")
  
	private Manager manager;
	@OneToMany(mappedBy = "employee")
	private Set<Attend> attends = new HashSet<Attend>();
	@OneToMany(mappedBy = "employee")
	private Set<Payment> payments = new HashSet<Payment>();

	public Employee(int id, String name, String pass, double salary,
			Manager manager, Set<Attend> attends, Set<Payment> payments) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
	}

	public Employee() {

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<Attend> getAttends() {
		return attends;
	}

	public void setAttends(Set<Attend> attends) {
		this.attends = attends;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

}
