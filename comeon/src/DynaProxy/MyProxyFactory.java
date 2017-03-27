package DynaProxy;

import java.lang.reflect.*;

public class MyProxyFactory {
	public static Object getProxy(Object target) throws Exception {
		MyInvokationHandler handler = new MyInvokationHandler();
		handler.setTarget(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass()
				.getInterfaces(), handler);
	}
}
