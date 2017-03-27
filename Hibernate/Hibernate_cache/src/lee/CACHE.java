package lee;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.crazyit.app.domain.*;

public class CACHE {
	public static void main(String[] args) {
		CACHE cache = new CACHE();

		cache.testQueryCache();
		HibernateUtil.sessionFactory.close();
	}

	public void create_table() {// 建表
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();

		session.getTransaction().commit();
		session.close();
	}

	public void testSave() {// 先存数据
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setName("c" + i);
			Topic t = new Topic();
			t.setCategory(c);
			t.setTitle("t" + i);
			t.setCreateDate(new Date());
			session.save(c);
			session.save(t);
		}

		session.getTransaction().commit();
		session.close();
	}

	public void testCache1() {// 用了session级缓存
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Category c = (Category) session.load(Category.class, 1);
		System.out.println(c.getName());

		Category c2 = (Category) session.load(Category.class, 1);
		System.out.println(c2.getName());
		session.getTransaction().commit();
		session.close();

	}

	public void testCache2() {// 配置二级缓存 四个步骤后 session可以通用了
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Category c = (Category) session.load(Category.class, 1);
		System.out.println(c.getName());

		session.getTransaction().commit();
		session.close();

		Session session2 = HibernateUtil.sessionFactory.openSession();
		session2.beginTransaction();
		Category c2 = (Category) session2.load(Category.class, 1);
		System.out.println(c2.getName());

		session2.getTransaction().commit();
		session2.close();
	}

	public void testQueryCache() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		List<Category> categories = (List<Category>) session
				.createQuery("from Category").setCacheable(true).list();

		session.getTransaction().commit();
		session.close();

		Session session2 = HibernateUtil.sessionFactory.openSession();
		session2.beginTransaction();
		List<Category> categories2 = (List<Category>) session2
				.createQuery("from Category").setCacheable(true).list();

		session2.getTransaction().commit();
		session2.close();
	}
}
