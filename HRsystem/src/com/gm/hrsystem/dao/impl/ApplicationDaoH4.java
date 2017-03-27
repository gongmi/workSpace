package com.gm.hrsystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gm.common.dao.impl.BaseDaoHibernate4;
import com.gm.hrsystem.dao.ApplicationDao;
import com.gm.hrsystem.domain.*;
@Component(value="appDao")
public class ApplicationDaoH4 extends BaseDaoHibernate4<Application> implements ApplicationDao
{
	@Override
	public List<Application> findByEmp(Employee emp) {
return find("from Application a where a.attend.employee=?0",emp);
	}
 
}
