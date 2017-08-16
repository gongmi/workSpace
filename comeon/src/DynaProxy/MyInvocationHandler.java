package DynaProxy;

import java.lang.reflect.*;

public class MyInvocationHandler implements InvocationHandler {
	// ��Ҫ������Ķ���
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
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
}
