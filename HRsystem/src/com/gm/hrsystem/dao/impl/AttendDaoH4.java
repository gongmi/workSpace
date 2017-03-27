package com.gm.hrsystem.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gm.common.dao.impl.BaseDaoHibernate4;
import com.gm.hrsystem.dao.*;
import com.gm.hrsystem.domain.*;

@Component(value = "attendDao")
public class AttendDaoH4 extends BaseDaoHibernate4<Attend> implements AttendDao {

	@Override
	public List<Attend> findByEmpAndMonth(Employee emp, String month) {
		return find("from Attend as a where a.employee=?0 " +
				"and substring(a.dutyDay , 0 , 7)=?1" , emp , month);

	}

	@Override
	public List<Attend> findByEmpAndDutyDay(Employee emp, String dutyday) {
		return find("from Attend a where a.employee=?0 and a.dutyDay=?1", emp,
				dutyday);
	}

	@Override
	public Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyday,
			boolean isCome) {
		List<Attend> attend = find(
				"from Attend a where a.employee=?0 and a.dutyDay=?1 and a.isCome=?2",
				emp, dutyday, isCome);
		if (attend == null || attend.size() == 0)
			return null;
		else
			return attend.get(0);

	}

	@Override
	public List<Attend> findByEmpUnAttend(Employee emp, AttendType type) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c = Calendar.getInstance();
//		String end = sdf.format(c.getTime());
//		c.add(Calendar.DAY_OF_MONTH, -3);
//		String start = sdf.format(c.getTime());
//
//		return find("from Attend a where a.employee = ?0 and a.type != ?1"
//				+ " and a.dutyDay between ?2 and ?3", emp, type, start, end);
		 

		return find("from Attend a where a.employee = ?0", emp);
	}

}
