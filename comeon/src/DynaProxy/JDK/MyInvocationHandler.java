package DynaProxy.JDK;

import java.lang.reflect.*;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
		System.out.println("Before:" + method);
		Object result = method.invoke(target, args);
		System.out.println("After:" + method);
		return result;
	}

	public Object getProxy() throws Exception {
		ClassLoader cl1 = target.getClass().getClassLoader();
		ClassLoader cl2 = Thread.currentThread().getContextClassLoader();
		return Proxy.newProxyInstance(cl2, target.getClass().getInterfaces(), this);
	}
}
