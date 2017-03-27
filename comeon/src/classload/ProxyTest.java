package classload;

import java.lang.reflect.*;

interface Person2 {
	void walk();
	void sayHello(String name);
}

class MyInvokationHandler implements InvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) {
		System.out.println("----����ִ�еķ���:" + method);
		if (args != null) {
			System.out.println("������ִ�и÷���ʱ�����ʵ��Ϊ��");
			for (Object val : args) {
				System.out.println(val);
			}
		} else {
			System.out.println("���ø÷���û��ʵ�Σ�");
		}
		return null;
	}
}

public class ProxyTest {
	public static void main(String[] args) throws Exception {
		InvocationHandler handler = new MyInvokationHandler();
		Person2 p = (Person2) Proxy.newProxyInstance(Person2.class.getClassLoader(),
				new Class[] { Person2.class }, handler);
		// ���ö�̬��������walk()��sayHello()����
		p.walk();
		p.sayHello("�����");
	}
}
