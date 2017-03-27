package lee;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;

public class NewsManager {
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				conf.getProperties()).build();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		News n = new News();
		n.setTitle("吴世芳");
		n.setContent("公务员");
		System.out.println(sess.save(n));
		tx.commit();
		sess.close();
		sf.close();
	}
}
