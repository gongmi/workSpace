package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;

public class SessionTest {
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				conf.getProperties()).build();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session sess = sf.getCurrentSession();
		Transaction tx = sess.beginTransaction();

		Teacher t = new Teacher();
		t.setName("大米");
		t.setTitle("教授");
		t.setBirthDate(new Date());
		t.setGender(Gender.MALE);
		System.out.println("Transient:" + t.getId());
		sess.save(t);
		System.out.println("persistant:" + t.getId());
		t.setTitle("老师");
		tx.commit();
		System.out.println("detached:" + t.getId());

		// Session sess2 = sf.getCurrentSession();
		// Transaction tx2 = sess2.beginTransaction();
		// Teacher t2 =(Teacher)sess2.load(Teacher.class, 22);
		//
		// // sess2.delete(t2);
		// System.out.println("Transient:"+t2.getClass());
		//
		// tx2.commit();

		sf.close();
	}
}
