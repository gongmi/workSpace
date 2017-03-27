package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;


public class TeacherManager {
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
//		Session sess = sf.openSession();
				Session sess = sf.getCurrentSession();
				Session sess2 = sf.getCurrentSession();
		Transaction tx = sess.beginTransaction();
//		Session sess2 = sf.openSession();
		
	
		Teacher t = new Teacher();
		t.setName("chenming");
		t.setTitle("½ÌÊÚ");
	
		t.setBirthDate(new Date());
		t.setGender(Gender.MALE);
		
		sess.save(t);
		tx.commit();

//		sess.close();
		System.out.println(sess==sess2);
		sf.close();
	}
}
