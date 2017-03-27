package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;
//����gong��husband ��Ȼwife�����ݿ�����û��husband��һ�� ����ȴ�ܴ��ȥ
//����֪�������������� ���һ���������yuan��wife��gong ����gong��husband��eddie �������ݿ���û��eddie���husband
//���ϲ��ԣ���������

//��û�д�eddie��ȥ ���������Ҳ����ô�gong��husbandʱ �Ҵ����ȻҲ�ܰ�gong��husband�õ�
//֮ǰ���ò�������Ϊ��û�а�HibernateUtil.closeSession();�������� 
//��������ں������� ��ô get��set����ͬһ��session ��ʱonetoone��û�������� ���ҵĲ²⣩
//���Ե���w.getName()ʱ�õ�����session�����е����� ����onetoone��û������ ������null
// ���ǵ��� Husband h2= new Husband();
//h2.setName("Eddie"); 
//w.setHusband(h2);
//session.save(w);
//��
//��ʵ��д����ȥ�� ��Ϊone to one ������ �����ҵ��������� ��������Ϲ��Ǳ���
//���Ե��Ұ�HibernateUtil.closeSession();�������� Ȼ��get ʱ ��yuan�� 
//Ҳ����˵ ����set��set wife ��husband  ��û���� ��Ϊwife��husband ��husband��wife�����ˣ�����mapped

public class one2one {
	public static void main(String[] args)
	{
		one2one o2o = new one2one();
		o2o.set_bi_fk();
		o2o.get_bi_fk();
		HibernateUtil.closeSession();
		HibernateUtil.sessionFactory.close();
	}

	private void set_bi_fk()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		 Husband h= new Husband();
		 Wife w= new Wife();
		 h.setName("yuan");
		 w.setName("gong");
		 h.setWife(w);
		 session.save(h);
		 Husband h2= new Husband();
         h2.setName("Eddie");
         w.setHusband(h2);
		 session.save(w);
		tx.commit();
		HibernateUtil.closeSession();
	} 
	private void get_bi_fk()
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		
	 
		 
		 Wife w=(Wife)session.load(Wife.class, 1);
		 System.out.println(w.getName()+"���Ϲ��ǣ�"+w.getHusband().getId()+w.getHusband().getName()+w.getHusband().getWife().getName());
		tx.commit();
		HibernateUtil.closeSession();
		
	}
}
