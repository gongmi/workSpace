package lee;

import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import org.hibernate.boot.registry.*;
import org.crazyit.app.domain.*;
//设置gong的husband 虽然wife的数据库里面没有husband这一列 但是却能存进去
//并不知道他存在哪里了 而且还可以设置yuan的wife是gong 但是gong的husband是eddie 并且数据库里没有eddie这个husband
//以上不对！！！！！

//并没有存eddie进去 而且现在我不设置存gong的husband时 我待会居然也能把gong的husband拿到
//之前我拿不到是因为我没有把HibernateUtil.closeSession();放在里面 
//如果不放在函数里面 那么 get和set公用同一个session 这时onetoone还没有起作用 （我的猜测）
//所以当我w.getName()时拿到的是session缓存中的数据 由于onetoone还没起作用 所以是null
// 但是当我 Husband h2= new Husband();
//h2.setName("Eddie"); 
//w.setHusband(h2);
//session.save(w);
//后
//其实是写不进去的 因为one to one 会限制 不能我的老婆是你 但是你的老公是别人
//所以当我把HibernateUtil.closeSession();放在外面 然后get 时 是yuan、 
//也就是说 不管set或不set wife 的husband  都没有用 因为wife的husband 由husband的wife决定了！！！mapped

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
		 System.out.println(w.getName()+"的老公是："+w.getHusband().getId()+w.getHusband().getName()+w.getHusband().getWife().getName());
		tx.commit();
		HibernateUtil.closeSession();
		
	}
}
