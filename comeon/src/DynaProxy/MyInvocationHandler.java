package DynaProxy;

import java.lang.reflect.*;

public class MyInvocationHandler implements InvocationHandler {
	// 需要被代理的对象
	private Object target;

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
		DogUtil du = new DogUtil();
		du.method1();
		Object result = method.invoke(target, args);
		du.method2();
		return result;
	}

	public Object getProxy() throws Exception {
		ClassLoader cl1 = target.getClass().getClassLoader();
		ClassLoader cl2 = Thread.currentThread().getContextClassLoader();
		return Proxy.newProxyInstance(cl2, target.getClass().getInterfaces(), this);
	}
}
