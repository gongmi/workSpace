package DynaProxy.CGLIB;

import net.sf.cglib.proxy.Enhancer;

public class Test {
	public static void main(String[] args) throws Exception {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(new MyMethodInterceptor());
		UserServiceImpl usProxy = (UserServiceImpl) enhancer.create();

		usProxy.add();
		usProxy.delete(1);
		usProxy.toString();
		System.out.println(usProxy.getClass());
	}
}
