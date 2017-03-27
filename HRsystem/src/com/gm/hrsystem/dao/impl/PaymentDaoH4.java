package com.gm.hrsystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gm.common.dao.impl.BaseDaoHibernate4;
import com.gm.hrsystem.dao.*;
import com.gm.hrsystem.domain.*;
@Component(value="payDao")
public class PaymentDaoH4 extends BaseDaoHibernate4<Payment> implements
		PaymentDao {

	@Override
	public Payment findByEmpAndMonth(Employee emp, String month) {
		List<Payment> payment = find(
				"from Payment p where p.employee=?0 and p.payMonth=?1", emp,
				month);
		if (payment == null)
			return null;
		else
			return payment.get(0);
	}

	@Override
	public List<Payment> findByEmp(Employee emp) {
		return find("from Payment p where p.employee=?0", emp);
	}

}
