package DynaProxy.JDK;

public class Test {
	public static void main(String[] args) throws Exception {
		// ����һ��ԭʼ��GunDog������Ϊtarget
		UserService us = new UserServiceImpl();
		MyInvocationHandler handler = new MyInvocationHandler(us);
		// ��ָ����target��������̬����
		UserService usProxy = (UserService) handler.getProxy();
		usProxy.add();
		usProxy.delete(1);
		usProxy.equals(us);
		usProxy.toString();
		System.out.println(us.getClass());
		System.out.println(usProxy.getClass());
	}
}
