package com.gm.hrsystem.dao;
//Ϊÿ��Dao����ṩ�ӿ� 
import java.util.List;
import com.gm.common.dao.BaseDao;
import com.gm.hrsystem.domain.*;
public interface ApplicationDao extends BaseDao<Application>
{
List<Application> findByEmp(Employee emp);
}
