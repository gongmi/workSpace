package com.gm.hrsystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gm.common.dao.impl.BaseDaoHibernate4;
import com.gm.hrsystem.dao.*;
import com.gm.hrsystem.domain.*;

@Component(value="empDao")
public class EmployeeDaoH4 extends BaseDaoHibernate4<Employee> implements EmployeeDao {

 
	//������������Manager���͵� empҲ���Բ鵽 ��������ת�� ��̬
	@Override
	public Employee findByNameAndPass(Employee emp) {
		List<Employee> emps=find(
				"from Employee where name =?0 and pass =?1",
				emp.getName(), emp.getPass());
		if (emps!=null&&emps.size()>=1)
			 return emps.get(0);
			else return null;	
	}

	@Override
	public Employee findByName(String name) {
		List<Employee> emps=find("from Employee e where e.name =?0",name);
		if (emps!=null&&emps.size()>=1)
			 return emps.get(0);
			else return null;
	}

}
