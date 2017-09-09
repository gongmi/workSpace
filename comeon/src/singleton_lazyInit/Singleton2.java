package singleton_lazyInit;

//线程安全的 就是在1的基础上加上了synchronized 但是这样不够好
public class Singleton2 {
	private static Singleton2 instance;

	private Singleton2() {

	}

	public synchronized static Singleton2 getInstance() {
		if (instance == null)
			instance = new Singleton2();
		return instance;
	}
}