package DynaProxy.CGLIB;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {

	// �۲�˷�����MyInvocationHandler ��invoke���������� �������һ��MethodProxy
	// invoke������ method.invoke(target, args);
	// �������û���õ�method �����õ�MethodProxy
	public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy)
			throws Throwable {
		System.out.println("Before:" + method);
		Object object = proxy.invokeSuper(obj, arg);
		System.out.println("After:" + method);
		return object;
	}
}
