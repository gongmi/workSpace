package com.gm.hrsystem.dao;

import com.gm.common.dao.BaseDao;
import com.gm.hrsystem.domain.*;
public interface EmployeeDao extends BaseDao<Employee>
{
Employee findByNameAndPass(Employee emp);
Employee findByName(String name);
}
