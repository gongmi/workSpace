package com.gm.hrsystem.dao;

import java.util.List;
import com.gm.common.dao.BaseDao;
import com.gm.hrsystem.domain.*;

public interface PaymentDao extends BaseDao<Payment> {
	
	Payment findByEmpAndMonth(Employee emp, String month);
	List<Payment> findByEmp(Employee emp);
}
