package DynaProxy.CGLIB;

import java.lang.reflect.Method;

import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//有些代码会报错 就注释掉了
public class UserServiceImpl$$EnhancerByCGLIB$$394dddeb extends UserServiceImpl {
	private boolean CGLIB$BOUND;
	private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
	// private static final Callback[] CGLIB$STATIC_CALLBACKS;
	private MethodInterceptor CGLIB$CALLBACK_0;
	private static final Method CGLIB$add$0$Method;
	private static final MethodProxy CGLIB$add$0$Proxy;
	private static final Object[] CGLIB$emptyArgs;
	static {
		CGLIB$THREAD_CALLBACKS = new ThreadLocal();
		CGLIB$emptyArgs = new Object[0];
		Class<?> forName = null;
		Class<?> forName3 = null;
		try {
			forName = Class.forName("UserServiceImpl$$EnhancerByCGLIB$$394dddeb");
			forName3 = Class.forName("UserServiceImpl");
		} catch (ClassNotFoundException e) {
		}

		CGLIB$add$0$Method = ReflectUtils.findMethods(new String[] { "add", "()V" },
				forName3.getDeclaredMethods())[0];
		CGLIB$add$0$Proxy = MethodProxy.create((Class) forName3, (Class) forName, "()V", "add",
				"CGLIB$add$0");
	}

	final void CGLIB$add$0() {
		super.add();
	}

	public final void add() {
		MethodInterceptor cglib$CALLBACK_2;
		MethodInterceptor cglib$CALLBACK_0;
		if ((cglib$CALLBACK_0 = (cglib$CALLBACK_2 = this.CGLIB$CALLBACK_0)) == null) {
			// CGLIB$BIND_CALLBACKS(this);
			cglib$CALLBACK_2 = (cglib$CALLBACK_0 = this.CGLIB$CALLBACK_0);
		}
		if (cglib$CALLBACK_0 != null) {
			try {
				cglib$CALLBACK_2.intercept((Object) this, CGLIB$add$0$Method, CGLIB$emptyArgs,
						CGLIB$add$0$Proxy);
			} catch (Throwable e) {
			}
			return;
		}
		super.add();
	}
}
