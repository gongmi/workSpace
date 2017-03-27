package com.gm.hrsystem.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "checkback_inf")
public class CheckBack implements Serializable {
	private static final long serialVersionUID = 48L;
	// 标识属性
	@Id
	@Column(name = "check_id")
	@GeneratedValue
	private Integer id;
	// 是否同意申请
	@Column(name = "check_result", nullable = false)
	private boolean result;
	// 批复理由
	@Column(name = "check_reason", length = 255)
	private String reason;

	// 该批复对应的申请
	@OneToOne
	@JoinColumn(name = "app_id", nullable = false, unique = true)
	private Application app;
	// 批复的经理
	@ManyToOne
	@JoinColumn(name = "mgr_id", nullable = false)
	private Manager manager;

	// 无参数的构造器
	public CheckBack() {
	}

	// 初始化全部成员变量的构造器
	public CheckBack(Integer id, boolean result, String reason,
			Application app, Manager manager) {
		this.id = id;
		this.result = result;
		this.reason = reason;
		this.app = app;
		this.manager = manager;
	}

	// id的setter和getter方法
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// result的setter和getter方法
	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
		return this.result;
	}

	// reason的setter和getter方法
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	// app的setter和getter方法
	public void setApp(Application app) {
		this.app = app;
	}

	public Application getApp() {
		return this.app;
	}

	// manager的setter和getter方法
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Manager getManager() {
		return this.manager;
	}
}