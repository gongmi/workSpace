package com.gm.hrsystem.dao;
//为每个Dao组件提供接口 
import java.util.List;
import com.gm.common.dao.BaseDao;
import com.gm.hrsystem.domain.*;
public interface ApplicationDao extends BaseDao<Application>
{
List<Application> findByEmp(Employee emp);
}
