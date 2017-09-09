package singleton_lazyInit;

//这也是延迟初始化  
public class Singleton4 {
	private static class InstanceHolder {
		public static Singleton4 instance = new Singleton4();
	}

	private Singleton4() {

	}

	public static Singleton4 getInstance() {
		return InstanceHolder.instance; // 这里将导致InstanceHolder类被初始化 虚拟机会加锁进行同步
										// 因为调用了InstanceHolder的static字段
	}
}
