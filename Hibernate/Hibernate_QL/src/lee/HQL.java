package lee;

import java.util.Date;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.crazyit.app.domain.*;

 
 
 
 

public class HQL {
	public static void main(String[] args) {
		HQL hql = new HQL();
		// hql.create_table();
		hql.testQBC();
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

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setName("c" + i);
			session.save(c);
		}

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setId(1);
			Topic t = new Topic();
			t.setCategory(c);
			t.setTitle("t" + i);
			t.setCreateDate(new Date());
			session.save(t);

		}

		for (int i = 0; i < 10; i++) {

			Topic t = new Topic();
			t.setId(1);
			Msg m = new Msg();
			m.setCont("m" + i);
			m.setTopic(t);
			session.save(m);

		}

		session.getTransaction().commit();
		session.close();
	}

	public void testHQL_01() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category");
		List<Category> categories = (List<Category>) q.list();
		for (Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();

	}

	public void testHQL_02() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c where c.name > 'c5'");
		List<Category> categories = (List<Category>) q.list();
		for (Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();

	}

	public void testHQL_03() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c order by c.name desc");
		List<Category> categories = (List<Category>) q.list();
		for (Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();

	}

	public void testHQL_04() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session
				.createQuery("select distinct c from Category c order by c.name desc");
		List<Category> categories = (List<Category>) q.list();
		for (Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();

	}
	public void testHQL_05() {//ð����ռλ��
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		/*Query q = session.createQuery("from Category c where c.id > :min and c.id < :max");
		//q.setParameter("min", 2);
		//q.setParameter("max", 8);
		q.setInteger("min", 2);
		q.setInteger("max", 8);*/
		
		Query q = session.createQuery("from Category c where c.id > :min and c.id < :max")
			.setInteger("min", 2)
			.setInteger("max", 8);
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getId() + "-" + c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_06() {//��+���� ռλ��
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c where c.id > ? and c.id < ?");
		q.setParameter(0, 2)
			.setParameter(1, 8);
//		q.setParameter(1, 8);
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getId() + "-" + c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_07() {//��ҳ
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c order by c.name desc");
		q.setMaxResults(4);
		q.setFirstResult(2);
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getId() + "-" + c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_08() {//��ѯ����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select c.id,  c.name from Category c order by c.name desc");
		List<Object[]> categories = (List<Object[]>)q.list();
		for(Object[] o : categories) {
			System.out.println(o[0] + "-" + o[1]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//�趨fetch type Ϊlazy�󽫲����еڶ���sql��� �ڲ�����˳���ѯcategory
	
	public void testHQL_09() {//��ʽ����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.category.id = 1");
		List<Topic> topics = (List<Topic>)q.list();
		for(Topic t : topics) {
			System.out.println(t.getTitle());
			//System.out.println(t.getCategory().getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//�趨fetch type Ϊlazy�󽫲����еڶ���sql���
	
	public void testHQL_10() {//һ����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.category.id = 1");
		List<Topic> topics = (List<Topic>)q.list();
		for(Topic t : topics) {
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_11() {//������ʽ����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m.topic.category.id = 1");
		
		for(Object o : q.list()) {
			Msg m = (Msg)o;
			System.out.println(m.getCont());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	//�˽⼴��
	//VO Value Object
	//DTO data transfer object
	
	public void testHQL_12() {
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select new org.crazyit.app.domain.MsgInfo(m.id, m.cont, m.topic.title, m.topic.category.name) from Msg");
		
		for(Object o : q.list()) {
			MsgInfo m = (MsgInfo)o;
			System.out.println(m.getCont());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//���ֲ���left right join
	//Ϊʲô����д��join Category c
	//Ϊʲô����ֱ��дCategory����������дt.category
	//��Ϊ�п��ܴ��ڶ����Ա������ͬһ���ࣩ����Ҫָ������һ����Ա����������������������
	
	public void testHQL_13() {//��ʾ���� join
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select t.title, c.name from Topic t join t.category c "); 
		for(Object o : q.list()) {
			Object[] m = (Object[])o;
			System.out.println(m[0] + "-" + m[1]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	
	public void testHQL_14() {//ѧϰʹ��uniqueResult
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m = :MsgToSearch "); //����Ҫ
		Msg m = new Msg();
		m.setId(1);
		q.setParameter("MsgToSearch", m);
		
		Msg mResult = (Msg)q.uniqueResult();
		System.out.println(mResult.getCont());
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_15() { //�ۼ�����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select count(*) from Msg m");
		
		long count = (Long)q.uniqueResult();
		System.out.println(count);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_16() {//�ۼ�����
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select max(m.id), min(m.id), avg(m.id), sum(m.id) from Msg m");
		
		Object[] o = (Object[])q.uniqueResult();
		System.out.println(o[0] + "-" + o[1] + "-" + o[2] + "-" + o[3]);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_17() {//���ʽ
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m.id between 3 and 5");
		
		for(Object o : q.list()) {
			Msg m = (Msg)o;
			System.out.println(m.getId() + "-" + m.getCont());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_18() {//���ʽ
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m.id in (3,4, 5)");
		
		for(Object o : q.list()) {
			Msg m = (Msg)o;
			System.out.println(m.getId() + "-" + m.getCont());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//is null �� is not null
	
	public void testHQL_19() {//���ʽ
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m.cont is not null");
		
		for(Object o : q.list()) {
			Msg m = (Msg)o;
			System.out.println(m.getId() + "-" + m.getCont());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//is empty and is not empty ���Լ���
	//���Ҫ�� topic�м�һ��onetomany msgs
		public void testHQL_20() {//���ʽ
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Topic t where t.msgs is empty");
			
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getId() + "-" + t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_21() {//like
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Topic t where t.title like '%5'");
			
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getId() + "-" + t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_22() {//like
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Topic t where t.title like '_5'");
			
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getId() + "-" + t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		//����Ҫ
		
		public void testHQL_23() {//���ʽ ����
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("select lower(t.title)," +
												 "upper(t.title)," +
												 "trim(t.title)," +
												 "concat(t.title, '***')," +
												 "length(t.title)" +
												 " from Topic t ");
			
			for(Object o : q.list()) {
				Object[] arr = (Object[])o;
				System.out.println(arr[0] + "-" + arr[1] + "-" + arr[2] + "-" + arr[3] + "-" + arr[4] + "-");
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_24() {//���ʽ ����
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("select abs(t.id)," +
												 "sqrt(t.id)," +
												 "mod(t.id, 2)" +
												 " from Topic t ");
			
			for(Object o : q.list()) {
				Object[] arr = (Object[])o;
				System.out.println(arr[0] + "-" + arr[1] + "-" + arr[2] );
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_25() {//���ݿ� �� ����ʱ��
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("select current_date, current_time, current_timestamp, t.id from Topic t");
			
			for(Object o : q.list()) {
				Object[] arr = (Object[])o;
				System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2] + " | " + arr[3]);
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_26() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Topic t where t.createDate < :date");
			q.setParameter("date", new Date());
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_27() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title") ;
			for(Object o : q.list()) {
				Object[] arr = (Object[])o;
				System.out.println(arr[0] + "|" + arr[1]);
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_28() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title having count(*) >= 1") ;
			for(Object o : q.list()) {
				Object[] arr = (Object[])o;
				System.out.println(arr[0] + "|" + arr[1]);
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_29() {//�Ӳ�ѯ
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Topic t where t.id < (select avg(t.id) from Topic t)") ;
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_30() {//�Ӳ�ѯ
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Topic t where t.id < ALL (select t.id from Topic t where mod(t.id, 2)= 0) ") ;
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		//��in ����ʵ��exists�Ĺ���
		//����existsִ��Ч�ʸ�
		
		
		//���Ҫ�� topic�м�һ��onetomany msgs
		//ѡ��û��meg��topic
		public void testHQL_31() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();// from Topic t where t.id not in (1)
			Query q = session.createQuery("from Topic t where not exists (select m.id from Msg m where m.topic.id=t.id)") ;
			// Ҳ����д from Topic t where t.id not in (1)
			//Query q = session.createQuery("from Topic t where exists (select m.id from Msg m where m.topic.id=t.id)") ;
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		//update and delete
		//�淶��û��˵���ǲ���Ҫ����persistent object���������Ҫʹ�ã������ڵ�����trasaction��ִ��
		
		
		public void testHQL_32() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.createQuery("update Topic t set t.title = upper(t.title)") ;
			
			q.executeUpdate();
			q = session.createQuery("from Topic");
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getTitle());
			}
			session.createQuery("update Topic t set t.title = lower(t.title)")
				.executeUpdate();
			session.getTransaction().commit();
			session.close();
			
		}
		
		//����Ҫ
		//��properties  hql �ַ��� ���ȥ ����
		public void testHQL_33() {//������ѯ
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.getNamedQuery("topic.selectCertainTopic");
			q.setParameter("id", 5);
			Topic t = (Topic)q.uniqueResult();
			System.out.println(t.getTitle());
			session.getTransaction().commit();
			session.close();
			
		}
		
		//Native���˽⣩ ԭ��SQL��ѯ
		
		public void testHQL_34() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery q = session.createSQLQuery("select * from category limit 2,4").addEntity(Category.class);
			List<Category> categories = (List<Category>)q.list();
			for(Category c : categories) {
				System.out.println(c.getName());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		
		public void testHQL_35() {
			//��δʵ��//������ѯ+ԭ��SQL��ѯ
			
		}
	 
		public void testQBC() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			//criterion ��׼/׼��/Լ��
			Criteria c = session.createCriteria(Topic.class) //from Topic
						 
						 .add(Restrictions.gt("id", 2)) //greater than = id > 2
						 .add(Restrictions.lt("id", 8)) //little than = id < 8
						 .add(Restrictions.like("title", "t_"))
//						 .createCriteria("category")
//						 .add(Restrictions.between("id", 3, 5)) //category.id >= 3 and category.id <=5
						 ;
			 
			for(Object o : c.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getId() + "-" + t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		 
		//query by example ����û��
// ��Ʒ�� ��� ����  ��Ҫ����ʱ ƴsql���� select  product where 1==1 and ......
//		 new һ�� product���� ���ǲ�����QBE�跶ΧŶ
		public void testQBE() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Topic tExample = new Topic();
			tExample.setTitle("T_");
			
			Example e = Example.create(tExample)//��̬��������
						.ignoreCase().enableLike();
			Criteria c = session.createCriteria(Topic.class)
						 .add(Restrictions.gt("id", 2))
						 .add(Restrictions.lt("id", 8))
						 .add(e)
						 ;
			
			for(Object o : c.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getId() + "-" + t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
}
