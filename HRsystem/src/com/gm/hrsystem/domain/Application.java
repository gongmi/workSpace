package com.gm.hrsystem.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "application_inf")
public class Application implements Serializable {
	private static final long serialVersionUID = 48L;
	// �����ʶ����
	@Id
	@Column(name = "app_id")
	@GeneratedValue
	private Integer id;
	// ��������
	@Column(name = "app_reason", length = 50)
	private String reason;
	// �Ƿ���
	@Column(name = "app_result")
	private boolean result;
	// �����ĳ��ڼ�¼
	@ManyToOne
	@JoinColumn(name = "attend_id", nullable = false)
	private Attend attend;
	// ϣ����ָ�����ڸ�Ϊ��type����
	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private AttendType type;
	// ����Ľ��
	@OneToOne(mappedBy = "app")
	private CheckBack check;

	// �޲����Ĺ�����
	public Application() {
	}

	// ��ʼ��ȫ����Ա�����Ĺ�����
	public Application(Integer id, String reason, boolean result,
			Attend attend, AttendType type, CheckBack check) {
		this.id = id;
		this.reason = reason;
		this.result = result;
		this.attend = attend;
		this.type = type;
		this.check = check;
	}

	// id��setter��getter����
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	// reason��setter��getter����
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	// result��setter��getter����
	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean getResult() {
		return this.result;
	}

	// attend��setter��getter����
	public void setAttend(Attend attend) {
		this.attend = attend;
	}

	public Attend getAttend() {
		return this.attend;
	}

	// type��setter��getter����
	public void setType(AttendType type) {
		this.type = type;
	}

	public AttendType getType() {
		return this.type;
	}

	// check��setter��getter����
	public void setCheck(CheckBack check) {
		this.check = check;
	}

	public CheckBack getCheck() {
		return this.check;
	}

}