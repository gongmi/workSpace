package com.gm.hrsystem.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "2")
// manager继承了emp 所以manager也是一种emp 在用hql查找所有emp时也会找到mgr
public class Manager extends Employee implements Serializable {
	private static final long serialVersionUID = 48L;
	@Column(name = "dept_name", length = 50)
	private String dept;
	@OneToMany(mappedBy = "manager")
	private Set<Employee> employees = new HashSet<Employee>();
	@OneToMany(mappedBy = "manager")
	private Set<CheckBack> checks = new HashSet<CheckBack>();

	public Manager() {
	}

	public Manager(String dept, Set<Employee> employees, Set<CheckBack> checks) {
		this.dept = dept;
		this.employees = employees;
		this.checks = checks;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return this.dept;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setChecks(Set<CheckBack> checks) {
		this.checks = checks;
	}

	public Set<CheckBack> getChecks() {
		return this.checks;
	}
}