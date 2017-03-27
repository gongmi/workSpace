package lee;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.crazyit.app.domain.*;

public class HQL {
	public static void main(String[] args) {
		HQL hql = new HQL();
//		hql.create_table();
//		hql.testSave();
		hql.testQueryIterate();
		HibernateUtil.sessionFactory.close();
	}

	public void create_table() {// ����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();

		session.getTransaction().commit();
		session.close();
	}

	public void testSave() {// �ȴ�����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();

		for(int i=0; i<10; i++) {
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
//��1 
	public void testQuery1(){ //��topic��@ManyToOne(fetch=FetchType.LAZY) 
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		List<Topic> topics = (List<Topic>)session.createQuery("from Topic").list();
		for(Topic t : topics) {
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	 
	public void testQuery2() {//��topic��@ManyToOne(fetch=FetchType.LAZY) 
		                      //����Ҫcategoryʱ�ŷ�select���
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
	 
		List<Topic> topics = (List<Topic>)session.createQuery("from Topic").list();
					 
		
		for(Topic t : topics) {
			System.out.println(t.getId() + "-" + t.getTitle());
			System.out.println(t.getCategory().getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//��2
	public void testQuery3() {//��Category��@BatchSize
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		List<Topic> topics = (List<Topic>)session.createQuery("from Topic").list();
		for(Topic t : topics) {
			System.out.println(t.getId() + "-" + t.getTitle());
			System.out.println(t.getCategory().getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	 

	//��3
	public void testQuery4() {	//join fetch
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		List<Topic> topics = (List<Topic>)session.createQuery("from Topic t left join fetch t.category c").list();
		
		for(Topic t : topics) {
			System.out.println(t.getId() + "-" + t.getTitle());
			System.out.println(t.getCategory().getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	//��4
	
	public void testQuery5(){ //ʹ��QBC ʵ�ʻ���join fetch
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		List<Topic> topics = (List<Topic>)session.createCriteria(Topic.class).list();
		for(Topic t : topics) {
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void testQueryList() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		
		List<Category> categories = (List<Category>)session.createQuery("from Category").list();
//		for(Category c : categories) {
//			System.out.println(c.getName());
//		}
		
		List<Category> categories2 = (List<Category>)session.createQuery("from Category").list();
//		for(Category c : categories2) {
//			System.out.println(c.getName());
//		}
		
		session.getTransaction().commit();
		session.close();
		
	}
	
 
 
	public void testQueryIterate() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		
		Iterator<Category> categories = (Iterator<Category>)session.createQuery("from Category").iterate();
		while(categories.hasNext()) {
			Category c = categories.next();
			System.out.println(c.getName());
		}
		
		
		Iterator<Category> categories2 = (Iterator<Category>)session.createQuery("from Category").iterate();
		while(categories2.hasNext()) {
			Category c = categories2.next();
			System.out.println(c.getName());
		}
		
		session.getTransaction().commit();
		session.close();
		
	}	 
		 
		
}
