package DynaProxy.CGLIB;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {

	// 观察此方法与MyInvocationHandler 的invoke方法的区别 这里多了一个MethodProxy
	// invoke方法是 method.invoke(target, args);
	// 这里根本没有用到method 而是用的MethodProxy
	public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy)
			throws Throwable {
		System.out.println("Before:" + method);
		Object object = proxy.invokeSuper(obj, arg);
		System.out.println("After:" + method);
		return object;
	}
}
