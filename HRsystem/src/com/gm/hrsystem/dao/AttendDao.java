package com.gm.hrsystem.dao;

import java.util.List;
import com.gm.common.dao.BaseDao;
import com.gm.hrsystem.domain.*;
public interface AttendDao extends BaseDao<Attend>
{
List<Attend> findByEmpAndMonth(Employee emp,String month);
List<Attend> findByEmpAndDutyDay(Employee emp,String dutyday);
Attend findByEmpAndDutyDayAndCome(Employee emp,String dutyday,boolean isCome);
List<Attend> findByEmpUnAttend(Employee emp,AttendType type);
}
