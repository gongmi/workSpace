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

		g.setName("����С��");

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
		// System.out.println("ȡ���ˣ�"+u.getName()+u.getGroup().getName());
		// System.out.println("ȡ���ˣ�"+g.getName()+g.getUsers());
		tx.commit();
		HibernateUtil.closeSession();
	}

	private void update_transient()// ���³־û�ʵ��
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		User user = (User) session.get(User.class, 2);
		// user�������Ըı� ����commitʱ�Զ��ж������ݿ�ԭ�����ݲ�ͬ ���Զ�update
		// ��ʱ��update��@ManyToOne()�е�cascade��fetch����ȡֵ �޹�
		user.setName("user221");
		user.getGroup().setName("group221");
		tx.commit();

		HibernateUtil.closeSession();
	}

	private void update_detached()// �����ѹ�ʵ��
	{
		// ���user�ı���commit()֮�� ����Ҫִ��Update����ʱ user��group��ͬʱ����
		// ��User���cascade={CascadeType.ALL}��������

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
		// ���User��Group���о���Ϊ@ManyToOne(cascade={CascadeType.All})��
		// ��ô��ִ������code �� ����������
		User u = (User) session.get(User.class, 2);
		session.delete(u);
		//��ôɾ�� �� ������doc
		tx.commit();
		HibernateUtil.closeSession();
	}
}
