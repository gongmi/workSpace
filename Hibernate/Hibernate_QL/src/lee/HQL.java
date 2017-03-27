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
	public void testHQL_05() {//冒号作占位符
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
	
	
	public void testHQL_06() {//？+索引 占位符
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
	
	
	public void testHQL_07() {//分页
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
	
	
	public void testHQL_08() {//查询属性
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
	
	//设定fetch type 为lazy后将不会有第二条sql语句 期不会再顺便查询category
	
	public void testHQL_09() {//隐式连接
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
	
	//设定fetch type 为lazy后将不会有第二条sql语句
	
	public void testHQL_10() {//一样的
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
	
	
	public void testHQL_11() {//三层隐式连接
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
	//了解即可
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
	
	//动手测试left right join
	//为什么不能写成join Category c
	//为什么不能直接写Category名，而必须写t.category
	//因为有可能存在多个成员变量（同一个类），需要指明用哪一个成员变量的连接条件来做连接
	
	public void testHQL_13() {//显示连接 join
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
	
	
	
	public void testHQL_14() {//学习使用uniqueResult
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m = :MsgToSearch "); //不重要
		Msg m = new Msg();
		m.setId(1);
		q.setParameter("MsgToSearch", m);
		
		Msg mResult = (Msg)q.uniqueResult();
		System.out.println(mResult.getCont());
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_15() { //聚集函数
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select count(*) from Msg m");
		
		long count = (Long)q.uniqueResult();
		System.out.println(count);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_16() {//聚集函数
		Session session = HibernateUtil.sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select max(m.id), min(m.id), avg(m.id), sum(m.id) from Msg m");
		
		Object[] o = (Object[])q.uniqueResult();
		System.out.println(o[0] + "-" + o[1] + "-" + o[2] + "-" + o[3]);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public void testHQL_17() {//表达式
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
	
	
	public void testHQL_18() {//表达式
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
	
	//is null 与 is not null
	
	public void testHQL_19() {//表达式
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
	
	//is empty and is not empty 测试集合
	//这个要把 topic中加一个onetomany msgs
		public void testHQL_20() {//表达式
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
		//不重要
		
		public void testHQL_23() {//表达式 函数
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
		
		
		public void testHQL_24() {//表达式 函数
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
		
		
		public void testHQL_25() {//数据库 的 日期时间
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
		
		
		public void testHQL_29() {//子查询
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
		
		
		public void testHQL_30() {//子查询
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
		
		//用in 可以实现exists的功能
		//但是exists执行效率高
		
		
		//这个要把 topic中加一个onetomany msgs
		//选出没有meg的topic
		public void testHQL_31() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();// from Topic t where t.id not in (1)
			Query q = session.createQuery("from Topic t where not exists (select m.id from Msg m where m.topic.id=t.id)") ;
			// 也可以写 from Topic t where t.id not in (1)
			//Query q = session.createQuery("from Topic t where exists (select m.id from Msg m where m.topic.id=t.id)") ;
			for(Object o : q.list()) {
				Topic t = (Topic)o;
				System.out.println(t.getTitle());
			}
			session.getTransaction().commit();
			session.close();
			
		}
		
		//update and delete
		//规范并没有说明是不是要更新persistent object，所以如果要使用，建议在单独的trasaction中执行
		
		
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
		
		//不重要
		//用properties  hql 字符串 存进去 更好
		public void testHQL_33() {//命名查询
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Query q = session.getNamedQuery("topic.selectCertainTopic");
			q.setParameter("id", 5);
			Topic t = (Topic)q.uniqueResult();
			System.out.println(t.getTitle());
			session.getTransaction().commit();
			session.close();
			
		}
		
		//Native（了解） 原生SQL查询
		
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
			//尚未实现//命名查询+原生SQL查询
			
		}
	 
		public void testQBC() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			//criterion 标准/准则/约束
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
		 
		//query by example 书上没有
// 产品表 类别 日期  我要搜索时 拼sql条件 select  product where 1==1 and ......
//		 new 一个 product对象 但是不能用QBE设范围哦
		public void testQBE() {
			Session session = HibernateUtil.sessionFactory.openSession();
			session.beginTransaction();
			Topic tExample = new Topic();
			tExample.setTitle("T_");
			
			Example e = Example.create(tExample)//静态工厂方法
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
