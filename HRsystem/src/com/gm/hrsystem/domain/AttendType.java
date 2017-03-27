package com.gm.hrsystem.domain;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name="attend_type_inf")
public class AttendType
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	// ��ʶ����
	@Id @Column(name="type_id")
	@GeneratedValue 
	private Integer id;
	// �������͵�����
	@Column(name="type_name", nullable=false , length=50)
	private String name;
	// ������ڶ�Ӧ�ķ���
	@Column(name="amerce_amount", nullable=false)
	private double amerce;

	// �޲����Ĺ�����
	public AttendType()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public AttendType(Integer id , String name , double amerce)
	{
		this.id = id;
		this.name = name;
		this.amerce = amerce;
	}

	// id��setter��getter����
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	// name��setter��getter����
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	// amerce��setter��getter����
	public void setAmerce(double amerce)
	{
		this.amerce = amerce;
	}
	public double getAmerce()
	{
		return this.amerce;
	}
}