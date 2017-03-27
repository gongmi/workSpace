package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;

public class crud {
	public static void main(String[] args) {
		crud test = new crud();
		// test.save();
		test.delete();
		// test.update();
		HibernateUtil.sessionFactory.close();
	}

	private void save() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Group g = new Group();

		g.setName("超级小组");

		User u = new User();
		User u2 = new User();
		u.setName("gongmi");
		u2.setName("yuan");
		g.getUsers().add(u);
		g.getUsers().add(u2);
		u.setGroup(g);
		u2.setGroup(g);
		// session.save(u);
		// session.save(u2);
		session.save(g);
		tx.commit();
		HibernateUtil.closeSession();
	}

	private void get() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		// Group g=(Group)session.get(Group.class, 1);
		User u = (User) session.get(User.class, 2);
		// System.out.println("取到了："+u.getName()+u.getGroup().getName());
		// System.out.println("取到了："+g.getName()+g.getUsers());
		tx.commit();
		HibernateUtil.closeSession();
	}

	private void update_transient()// 更新持久化实体
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		User user = (User) session.get(User.class, 2);
		// user对象属性改变 事务commit时自动判断与数据库原有数据不同 可自动update
		// 此时的update与@ManyToOne()中的cascade或fetch参数取值 无关
		user.setName("user221");
		user.getGroup().setName("group221");
		tx.commit();

		HibernateUtil.closeSession();
	}

	private void update_detached()// 更新脱管实体
	{
		// 如果user改变在commit()之后 且想要执行Update方法时 user与group表同时更新
		// 则User类的cascade={CascadeType.ALL}起作用了

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, 1);
		tx.commit();

		user.setName("user1");
		user.getGroup().setName("group1");

		Session session2 = HibernateUtil.currentSession();
		Transaction tx2 = session2.beginTransaction();
		session2.update(user);
		tx2.commit();

		HibernateUtil.closeSession();
	}

	private void delete() {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		// 如果User及Group类中均设为@ManyToOne(cascade={CascadeType.All})，
		// 那么在执行如下code 后 两个表都空了
		User u = (User) session.get(User.class, 2);
		session.delete(u);
		//怎么删呢 ？ 方法见doc
		tx.commit();
		HibernateUtil.closeSession();
	}
}
