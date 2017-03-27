package com.gm.hrsystem.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "payment_inf")
public class Payment implements Serializable {
	private static final long serialVersionUID = 48L;
	
	// 标识属性
	@Id
	@Column(name = "pay_id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "pay_month", nullable = false, length = 50)
	private String payMonth;
	
	// 薪水多少
	@Column(name = "pay_amount", nullable = false)
	private double amount;
	
	// 领薪的员工
	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false)
	private Employee employee;

	// 无参数的构造器
	public Payment() {
	}

	// 初始化全部成员变量的构造器
	public Payment(Integer id, String payMonth, double amount, Employee employee) {
		this.id = id;
		this.payMonth = payMonth;
		this.amount = amount;
		this.employee = employee;
	}

	// id的setter和getter方法
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// payMonth的setter和getter方法
	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}

	public String getPayMonth() {
		return this.payMonth;
	}

	// amount的setter和getter方法
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	// employee的setter和getter方法
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return this.employee;
	}
}