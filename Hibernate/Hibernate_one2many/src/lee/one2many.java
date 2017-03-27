package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;
 
public class   one2many {
	public static void main(String[] args)
	{
		one2many  o2m = new one2many();
		o2m.uni_bi();
	 
		HibernateUtil.sessionFactory.close();
	}

	private void uni_bi()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		 Group g=new Group();
		
		 g.setName("³¬¼¶Ð¡×é");
		 session.save(g);
		 User u= new User();
		 User u2= new User();
		 u.setName("gongmi");
		 u2.setName("yuan");
		 u.setGroup(g);
		 u2.setGroup(g);
	 session.save(u);
	 session.save(u2);
		tx.commit();
		HibernateUtil.closeSession();
	} 
	 
}
