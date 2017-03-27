package com.gm.hrsystem.dao;

import com.gm.common.dao.BaseDao;
import com.gm.hrsystem.domain.*;
public interface ManagerDao extends BaseDao<Manager>
{
Manager findByNameAndPass(Manager mgr);
Manager findByName(String name);
}
