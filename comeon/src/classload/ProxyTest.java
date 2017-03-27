package classload;

import java.lang.reflect.*;

interface Person2 {
	void walk();
	void sayHello(String name);
}

class MyInvokationHandler implements InvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) {
		System.out.println("----正在执行的方法:" + method);
		if (args != null) {
			System.out.println("下面是执行该方法时传入的实参为：");
			for (Object val : args) {
				System.out.println(val);
			}
		} else {
			System.out.println("调用该方法没有实参！");
		}
		return null;
	}
}

public class ProxyTest {
	public static void main(String[] args) throws Exception {
		InvocationHandler handler = new MyInvokationHandler();
		Person2 p = (Person2) Proxy.newProxyInstance(Person2.class.getClassLoader(),
				new Class[] { Person2.class }, handler);
		// 调用动态代理对象的walk()和sayHello()方法
		p.walk();
		p.sayHello("孙悟空");
	}
}
