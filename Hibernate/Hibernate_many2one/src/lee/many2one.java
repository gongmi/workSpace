package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;
 
public class many2one {
	public static void main(String[] args)
	{
		many2one m2o = new many2one();
		m2o.uni_fk();
		HibernateUtil.closeSession();
		HibernateUtil.sessionFactory.close();
	}

	private void uni_fk()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		 Group g=new Group();
		 User u= new User();
		 g.setName("超级小组");
		 u.setName("gongmi");
		 u.setGroup(g);
		 session.save(u);
		 Group g2=new Group();
		 g2.setName("联盟");
		 u.setGroup(g2);
		tx.commit();
		HibernateUtil.closeSession();
	} 
	 
}
