package com.gm.hrsystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gm.common.dao.impl.BaseDaoHibernate4;
import com.gm.hrsystem.dao.*;
import com.gm.hrsystem.domain.*;

@Component(value="mgrDao")
public class ManagerDaoH4 extends BaseDaoHibernate4<Manager> implements ManagerDao {

 

	@Override
	public Manager findByNameAndPass(Manager mgr) {
		List<Manager> mgrs=find(
				"from Manager where name=?0 and pass=?1",mgr.getName(), mgr.getPass());
		if (mgrs!=null&&mgrs.size()>=1)
		 return mgrs.get(0);
		else return null;
	}

	@Override
	public Manager findByName(String name) {
		List<Manager> mgrs=find("from Manager e where e.name =?0",name);
		if (mgrs!=null&&mgrs.size()>=1)
			 return mgrs.get(0);
			else return null;
	}

}
