package com.gm.hrsystem.service;

import com.gm.hrsystem.domain.*;

import java.util.*;
public interface MgrManager
{
	/**
	 * ����Ա��
	 * @param emp ������Ա��
	 * @param mgr Ա�������ľ���
	 */
	void addEmp(Employee emp , String mgrName);
	


	/**
	 * ���ݾ����ز������е�Ա���ϸ��¹���
	 * @param mgr ������
	 * @return �����ϸ��¹���
	 */
	List<Payment> getSalaryByMgr(String mgrName);

	/**
	 * ���ݾ����ظò��ŵ�ȫ��Ա��
	 * @param mgr ������
	 * @return �����ȫ������
	 */
	List<Employee> getEmpsByMgr(String mgrName);

	/**
	 * ���ݾ����ظò��ŵ�û������������
	 * @param mgr ������
	 * @return �ò��ŵ�ȫ������
	 */
	List<Application> getAppsByMgr(String mgrName);

	/**
	 * ��������
	 * @param appid ����ID
	 * @param mgrName ��������
	 * @param result �Ƿ�ͨ��
	 */
	void check(int appid, String mgrName, boolean result);
}