package DynaProxy.JDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

//使用 反编译工具 jad 
//jad com.sun.proxy.$Proxy.1 
//看看代理类如何实现，反编译出来的java代码如下：
//1、生成的$proxy1继承自Proxy类，并实现了Service接口。
//2、执行代理对象的方法，其实就是执行InvocationHandle对象的invoke方法，传入的参数分别是当前代理对象，当前执行的方法和参数。
//super.h.invoke(this, m3, null);

public final class $proxy1 extends Proxy implements UserService {
	public $proxy1(InvocationHandler invocationhandler) {
		super(invocationhandler);
	}

	public final boolean equals(Object obj) {
		try {
			return ((Boolean) super.h.invoke(this, m1, new Object[] { obj })).booleanValue();
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return false;
	}

	public final String toString() {
		try {
			return (String) super.h.invoke(this, m2, null);
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return null;
	}

	public final void add() {
		try {
			super.h.invoke(this, m3, null);
			return;
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
	}

	public final void delete(int id) {
		try {
			super.h.invoke(this, m4, null);
			return;
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
	}

	public final int hashCode() {
		try {
			return ((Integer) super.h.invoke(this, m0, null)).intValue();
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return 0;
	}

	private static Method m1;
	private static Method m2;
	private static Method m3;
	private static Method m4;
	private static Method m0;
	static {
		try {
			m1 = Class.forName("java.lang.Object").getMethod("equals",
					new Class[] { Class.forName("java.lang.Object") });
			m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
			m3 = Class.forName("zzzzzz.UserService").getMethod("add", new Class[0]);
			m4 = Class.forName("zzzzzz.UserService").getMethod("delete", new Class[0]);
			m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
		} catch (NoSuchMethodException nosuchmethodexception) {
			throw new NoSuchMethodError(nosuchmethodexception.getMessage());
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
}
