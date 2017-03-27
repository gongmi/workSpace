package com.gm.hrsystem.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "checkback_inf")
public class CheckBack implements Serializable {
	private static final long serialVersionUID = 48L;
	// ��ʶ����
	@Id
	@Column(name = "check_id")
	@GeneratedValue
	private Integer id;
	// �Ƿ�ͬ������
	@Column(name = "check_result", nullable = false)
	private boolean result;
	// ��������
	@Column(name = "check_reason", length = 255)
	private String reason;

	// ��������Ӧ������
	@OneToOne
	@JoinColumn(name = "app_id", nullable = false, unique = true)
	private Application app;
	// �����ľ���
	@ManyToOne
	@JoinColumn(name = "mgr_id", nullable = false)
	private Manager manager;

	// �޲����Ĺ�����
	public CheckBack() {
	}

	// ��ʼ��ȫ����Ա�����Ĺ�����
	public CheckBack(Integer id, boolean result, String reason,
			Application app, Manager manager) {
		this.id = id;
		this.result = result;
		this.reason = reason;
		this.app = app;
		this.manager = manager;
	}

	// id��setter��getter����
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// result��setter��getter����
	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
		return this.result;
	}

	// reason��setter��getter����
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	// app��setter��getter����
	public void setApp(Application app) {
		this.app = app;
	}

	public Application getApp() {
		return this.app;
	}

	// manager��setter��getter����
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Manager getManager() {
		return this.manager;
	}
}